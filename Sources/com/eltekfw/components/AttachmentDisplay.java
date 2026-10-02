package com.eltekfw.components;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eltekfw.model.PreferenceStore;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

import er.attachment.model.ERAttachment;
import er.attachment.processors.ERAttachmentProcessor;
import er.directtoweb.components.ERDCustomComponent;

public class AttachmentDisplay extends ERDCustomComponent {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(AttachmentDisplay.class);

    // ---------- Preference names (code defaults are at each use) ----------

    private static final String PREF_WORD_MAX_WIDTH       = "attachment.word.maxWidth";      // 300px
    private static final String PREF_WORD_MAX_HEIGHT      = "attachment.word.maxHeight";     // 100px
    private static final String PREF_WORD_MAX_BLOCKS      = "attachment.word.maxBlocks";     // 30
    private static final String PREF_WORD_MAX_TABLE_ROWS  = "attachment.word.maxTableRows";  // 10
    private static final String PREF_SHEET_MAX_WIDTH      = "attachment.sheet.maxWidth";     // 100%
    private static final String PREF_SHEET_MAX_ROWS       = "attachment.sheet.maxRows";      // 20
    private static final String PREF_SHEET_MAX_COLS       = "attachment.sheet.maxCols";      // 10
    private static final String PREF_PDF_DPI              = "attachment.pdf.dpi";            // 100
    private static final String PREF_PDF_MAX_WIDTH        = "attachment.pdf.maxWidth";       // 100%
    private static final String PREF_PDF_MAX_HEIGHT      = "attachment.pdf.maxHeight";      // none
    private static final String PREF_IMAGE_MAX_WIDTH      = "attachment.image.maxWidth";     // 100%
    private static final String PREF_IMAGE_MAX_HEIGHT     = "attachment.image.maxHeight";    // 300px
    private static final String PREF_IMAGE_RENDER_MAX_PX  = "attachment.image.renderMaxPx";  // 800 (longest side embedded)

    /*
     * Rendered previews, kept in memory (up to 200) so each file is processed only once.
     * Only content is cached. Sizing (max-width / max-height) is applied around the cached
     * content on every render, so changing those preferences takes effect immediately.
     * Preferences that change the content itself (rows, columns, blocks, DPI) are part of
     * the cache key.
     */
    private static final Map<String, String> PREVIEW_CACHE = Collections.synchronizedMap(
        new LinkedHashMap<String, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> e) {
                return size() > 200;
            }
        });

    public AttachmentDisplay(WOContext context) {
        super(context);
    }

    @Override
    public boolean synchronizesVariablesWithBindings() {
        return false;
    }

    public ERAttachment attachment() {
        D2WContext c = (D2WContext) valueForBinding("localContext");
        if (c == null) {
            c = (D2WContext) valueForBinding("d2wContext");
        }
        EOEnterpriseObject eo = (EOEnterpriseObject) valueForBinding("object");
        if (eo == null && c != null) {
            eo = (EOEnterpriseObject) c.valueForKey("object");
        }
        String key = (String) valueForBinding("key");
        if (key == null && c != null) {
            key = c.propertyKey();
        }
        if (eo == null || key == null) {
            return null;
        }
        return (ERAttachment) eo.valueForKeyPath(key);
    }

    // ---------- type checks ----------

    private String mimeType() {
        ERAttachment a = attachment();
        return (a == null || a.mimeType() == null) ? "" : a.mimeType().toLowerCase();
    }

    private String fileName() {
        ERAttachment a = attachment();
        return (a == null || a.originalFileName() == null) ? "" : a.originalFileName().toLowerCase();
    }

    public boolean isPdf() {
        return attachment() != null
            && (mimeType().equals("application/pdf") || fileName().endsWith(".pdf"));
    }

    public boolean isSpreadsheet() {
        String mt = mimeType();
        String name = fileName();
        return attachment() != null
            && (mt.equals("application/vnd.ms-excel")
                || mt.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                || name.endsWith(".xls")
                || name.endsWith(".xlsx"));
    }

    public boolean isWord() {
        String mt = mimeType();
        String name = fileName();
        return attachment() != null
            && (mt.equals("application/msword")
                || mt.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                || name.endsWith(".doc")
                || name.endsWith(".docx"));
    }

    public boolean isImage() {
        String mt = mimeType();
        String name = fileName();
        return attachment() != null
            && (mt.equals("image/jpeg")
                || mt.equals("image/jpg")
                || mt.equals("image/pjpeg")
                || mt.equals("image/png")
                || mt.equals("image/gif")
                || mt.equals("image/webp")
                || name.endsWith(".jpg")
                || name.endsWith(".jpeg")
                || name.endsWith(".png")
                || name.endsWith(".gif")
                || name.endsWith(".webp"));
    }

    public boolean isOther() {
        return attachment() != null && !isPdf() && !isSpreadsheet() && !isWord() && !isImage();
    }

    // ---------- image preview ----------

    /**
     * The image as a data URL, embedded in the page like the PDF preview.
     * This avoids a separate request to ERAttachment's request handler, which
     * fails when that handler isn't registered or the attachment isn't saved yet.
     * Large images are scaled down so the longest side is at most renderMaxPx.
     */
    public String imageSrc() {
        ERAttachment a = attachment();
        if (a == null) {
            return null;
        }
        int maxPx = PreferenceStore.intValue(PREF_IMAGE_RENDER_MAX_PX, 800);
        Object pk = a.primaryKey();
        String cacheKey = (pk == null) ? null : "img-" + pk + "-" + maxPx;   // unsaved: don't cache
        if (cacheKey != null) {
            String cached = PREVIEW_CACHE.get(cacheKey);
            if (cached != null) {
                return cached;
            }
        }
        try (InputStream in = ERAttachmentProcessor.processorForType(a).attachmentInputStream(a)) {
            String src = imageDataUrl(in.readAllBytes(), imageMimeType(), maxPx);
            if (cacheKey != null) {
                PREVIEW_CACHE.put(cacheKey, src);
            }
            return src;
        } catch (Exception e) {
            log.warn("Could not render image preview for attachment {}", pk, e);
            return null;
        }
    }

    /** MIME type to use when embedding the original bytes. */
    private String imageMimeType() {
        String mt = mimeType();
        if (mt.equals("image/jpg") || mt.equals("image/pjpeg")) {
            return "image/jpeg";
        }
        if (mt.startsWith("image/")) {
            return mt;
        }
        String name = fileName();
        if (name.endsWith(".png"))  return "image/png";
        if (name.endsWith(".gif"))  return "image/gif";
        if (name.endsWith(".webp")) return "image/webp";
        return "image/jpeg";
    }

    private static String imageDataUrl(byte[] data, String mimeType, int maxPx) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));

        // Embed the original when it's already small, when ImageIO can't read it
        // (e.g. webp), or for GIFs (re-encoding would lose animation).
        if (img == null || mimeType.equals("image/gif")
                || (img.getWidth() <= maxPx && img.getHeight() <= maxPx)) {
            return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(data);
        }

        double scale = Math.min((double) maxPx / img.getWidth(), (double) maxPx / img.getHeight());
        int w = Math.max(1, (int) Math.round(img.getWidth() * scale));
        int h = Math.max(1, (int) Math.round(img.getHeight() * scale));
        boolean alpha = img.getColorModel().hasAlpha();

        BufferedImage out = new BufferedImage(w, h, alpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        Graphics2D g = out.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawImage(img, 0, 0, w, h, null);
        g.dispose();

        String format = alpha ? "png" : "jpeg";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(out, format, bos);
        return "data:image/" + format + ";base64," + Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    /** Style for the image preview <img>; bind it in the template: style = imageStyle; */
    public String imageStyle() {
        String maxWidth = PreferenceStore.cssLength(PREF_IMAGE_MAX_WIDTH, "100%");
        String maxHeight = PreferenceStore.cssLength(PREF_IMAGE_MAX_HEIGHT, "300px");
        return "max-width:" + maxWidth + ";max-height:" + maxHeight + ";border:1px solid #ccc;";
    }

    // ---------- Word preview ----------

    /** Start of the document (text and tables) as HTML. */
    public String wordPreviewHtml() {
        ERAttachment a = attachment();
        if (a == null) {
            return "";
        }
        int maxBlocks = PreferenceStore.intValue(PREF_WORD_MAX_BLOCKS, 30);
        int maxTableRows = PreferenceStore.intValue(PREF_WORD_MAX_TABLE_ROWS, 10);

        String cacheKey = "doc-" + a.primaryKey() + "-" + maxBlocks + "-" + maxTableRows;
        String body = PREVIEW_CACHE.get(cacheKey);
        if (body == null) {
            try (InputStream in = ERAttachmentProcessor.processorForType(a).attachmentInputStream(a)) {
                byte[] data = in.readAllBytes();
                StringBuilder sb = new StringBuilder();

                // Decide by file content, not name: .docx is a zip (OOXML), old .doc is OLE2
                boolean truncated = (FileMagic.valueOf(data) == FileMagic.OOXML)
                    ? renderDocx(data, sb, maxBlocks, maxTableRows)
                    : renderDoc(data, sb, maxBlocks);

                if (truncated) {
                    sb.append("<p style=\"color:#666;font-style:italic;margin-top:8px;\">")
                      .append("Preview shows the beginning of the document.</p>");
                }
                body = sb.toString();
                PREVIEW_CACHE.put(cacheKey, body);
            } catch (Exception e) {
                log.warn("Could not render Word preview for attachment {}", a.primaryKey(), e);
                return "<em>Preview not available</em>";
            }
        }

        String maxWidth = PreferenceStore.cssLength(PREF_WORD_MAX_WIDTH, "300px");
        String maxHeight = PreferenceStore.cssLength(PREF_WORD_MAX_HEIGHT, "100px");
        return "<div style=\"max-width:" + maxWidth + ";max-height:" + maxHeight + ";overflow:auto;"
             + "border:1px solid #ccc;padding:8px 12px;font-size:0.85em;background:#fff;\">"
             + body + "</div>";
    }

    /** .docx: paragraphs and tables in document order. Returns true if truncated. */
    private static boolean renderDocx(byte[] data, StringBuilder sb, int maxBlocks, int maxTableRows)
            throws IOException {
        try (XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(data))) {
            int blocks = 0;
            for (IBodyElement el : doc.getBodyElements()) {
                if (blocks >= maxBlocks) {
                    return true;
                }
                if (el instanceof XWPFParagraph) {
                    XWPFParagraph p = (XWPFParagraph) el;
                    String text = p.getText();
                    if (text == null || text.isBlank()) {
                        continue;
                    }
                    String style = p.getStyle() == null ? "" : p.getStyle().toLowerCase();
                    boolean heading = style.startsWith("heading") || style.equals("title");
                    sb.append(heading
                            ? "<p style=\"font-weight:bold;margin:10px 0 4px;\">"
                            : "<p style=\"margin:0 0 6px;\">")
                      .append(escape(text))
                      .append("</p>");
                    blocks++;
                } else if (el instanceof XWPFTable) {
                    XWPFTable t = (XWPFTable) el;
                    sb.append("<table style=\"border-collapse:collapse;margin:6px 0;\">");
                    int rows = 0;
                    for (XWPFTableRow row : t.getRows()) {
                        if (rows++ >= maxTableRows) {
                            break;
                        }
                        sb.append("<tr>");
                        for (XWPFTableCell cell : row.getTableCells()) {
                            sb.append("<td style=\"border:1px solid #ccc;padding:2px 6px;\">")
                              .append(escape(cell.getText()))
                              .append("</td>");
                        }
                        sb.append("</tr>");
                    }
                    sb.append("</table>");
                    blocks++;
                }
            }
        }
        return false;
    }

    /** Old .doc: plain paragraphs (tables come through as text). Returns true if truncated. */
    private static boolean renderDoc(byte[] data, StringBuilder sb, int maxBlocks) throws IOException {
        try (WordExtractor ex = new WordExtractor(new ByteArrayInputStream(data))) {
            int blocks = 0;
            for (String raw : ex.getParagraphText()) {
                // Remove Word field codes and control characters (e.g. table cell markers)
                String text = WordExtractor.stripFields(raw)
                        .replaceAll("[\\p{Cntrl}&&[^\\t]]", " ")
                        .trim();
                if (text.isEmpty()) {
                    continue;
                }
                if (blocks >= maxBlocks) {
                    return true;
                }
                sb.append("<p style=\"margin:0 0 6px;\">").append(escape(text)).append("</p>");
                blocks++;
            }
        }
        return false;
    }

    // ---------- PDF preview ----------

    /** First page of the PDF as a PNG data URL, or null if it can't be rendered. */
    public String pdfPreviewSrc() {
        ERAttachment a = attachment();
        if (a == null) {
            return null;
        }
        int dpi = PreferenceStore.intValue(PREF_PDF_DPI, 100);

        String cacheKey = "pdf-" + a.primaryKey() + "-" + dpi;
        String cached = PREVIEW_CACHE.get(cacheKey);
        if (cached != null) {
            return cached;
        }
        try (InputStream in = ERAttachmentProcessor.processorForType(a).attachmentInputStream(a);
             PDDocument doc = Loader.loadPDF(in.readAllBytes())) {
            BufferedImage page = new PDFRenderer(doc).renderImageWithDPI(0, dpi);
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            ImageIO.write(page, "png", png);
            String src = "data:image/png;base64," + Base64.getEncoder().encodeToString(png.toByteArray());
            PREVIEW_CACHE.put(cacheKey, src);
            return src;
        } catch (Exception e) {
            log.warn("Could not render PDF preview for attachment {}", a.primaryKey(), e);
            return null;
        }
    }

    /** Style for the PDF preview <img>; bind it in the template: style = pdfImageStyle; */
    public String pdfImageStyle() {
        String maxWidth = PreferenceStore.cssLength(PREF_PDF_MAX_WIDTH, "100%");
        String maxHeight = PreferenceStore.cssLength(PREF_PDF_MAX_HEIGHT, "none");
        return "max-width:" + maxWidth + ";max-height:" + maxHeight + ";";
    }

    // ---------- spreadsheet preview ----------

    /** First sheet as an HTML table (limited by the sheet maxRows / maxCols preferences). */
    public String spreadsheetPreviewHtml() {
        ERAttachment a = attachment();
        if (a == null) {
            return "";
        }
        int maxRows = PreferenceStore.intValue(PREF_SHEET_MAX_ROWS, 20);
        int maxCols = PreferenceStore.intValue(PREF_SHEET_MAX_COLS, 10);

        String cacheKey = "xls-" + a.primaryKey() + "-" + maxRows + "x" + maxCols;
        String body = PREVIEW_CACHE.get(cacheKey);
        if (body == null) {
            try (InputStream in = ERAttachmentProcessor.processorForType(a).attachmentInputStream(a);
                 Workbook wb = WorkbookFactory.create(in)) {

                Sheet sheet = wb.getSheetAt(0);
                DataFormatter fmt = new DataFormatter();
                FormulaEvaluator ev = wb.getCreationHelper().createFormulaEvaluator();

                int totalRows = sheet.getLastRowNum() + 1;
                int shownRows = Math.min(totalRows, maxRows);

                int totalCols = 0;
                for (int r = 0; r < shownRows; r++) {
                    Row row = sheet.getRow(r);
                    if (row != null) {
                        totalCols = Math.max(totalCols, row.getLastCellNum());
                    }
                }
                int shownCols = Math.min(totalCols, maxCols);

                StringBuilder sb = new StringBuilder();
                sb.append("<div style=\"font-size:0.85em;color:#666;margin-bottom:4px;\">Sheet: ")
                  .append(escape(sheet.getSheetName())).append("</div>");
                sb.append("<table style=\"border-collapse:collapse;font-size:0.85em;\">");
                for (int r = 0; r < shownRows; r++) {
                    Row row = sheet.getRow(r);
                    sb.append("<tr>");
                    for (int c = 0; c < shownCols; c++) {
                        Cell cell = (row == null) ? null : row.getCell(c);
                        sb.append("<td style=\"border:1px solid #ccc;padding:2px 6px;white-space:nowrap;\">")
                          .append(escape(cellText(cell, fmt, ev)))
                          .append("</td>");
                    }
                    sb.append("</tr>");
                }
                sb.append("</table>");
                if (totalRows > shownRows || totalCols > shownCols) {
                    sb.append("<div style=\"font-size:0.8em;color:#666;margin-top:4px;\">Showing ")
                      .append(shownRows).append(" of ").append(totalRows).append(" rows, ")
                      .append(shownCols).append(" of ").append(totalCols).append(" columns</div>");
                }
                body = sb.toString();
                PREVIEW_CACHE.put(cacheKey, body);
            } catch (Exception e) {
                log.warn("Could not render spreadsheet preview for attachment {}", a.primaryKey(), e);
                return "<em>Preview not available</em>";
            }
        }

        String maxWidth = PreferenceStore.cssLength(PREF_SHEET_MAX_WIDTH, "100%");
        return "<div style=\"max-width:" + maxWidth + ";overflow-x:auto;\">" + body + "</div>";
    }

    private static String cellText(Cell cell, DataFormatter fmt, FormulaEvaluator ev) {
        if (cell == null) {
            return "";
        }
        try {
            return fmt.formatCellValue(cell, ev);   // formulas show their calculated result
        } catch (RuntimeException e) {
            return fmt.formatCellValue(cell);       // formula POI can't evaluate: show it as written
        }
    }

    private static String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}