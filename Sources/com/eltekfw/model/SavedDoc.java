package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eocontrol.EOEditingContext;

import er.attachment.model.ERAttachment;
import er.attachment.processors.ERAttachmentProcessor;

public class SavedDoc extends com.eltekfw.model.eogen._SavedDoc {
	private static final Logger LOG = LoggerFactory.getLogger(SavedDoc.class);

	/**
	 * ERAttachment copies an uploaded file into permanent storage as soon as it is
	 * uploaded, before anything is saved to the database. If the edit is then
	 * cancelled, the editing context is reverted and the attachment row is never
	 * written, but the file stays behind with nothing pointing to it.
	 *
	 * This runs just before the editing context is reverted (the Cancel button on
	 * the invoice page, or on the embedded document form) and removes the file of
	 * an attachment that was uploaded during this edit and never saved.
	 *
	 * Only an attachment that is newly inserted in the editing context being
	 * reverted is touched. An attachment already saved to the database, or one
	 * that belongs to a parent editing context, is left alone.
	 */
	@Override
	public void willRevert() {
		try {
			EOEditingContext ec = editingContext();
			ERAttachment attachment = anImage();
			if (ec != null && attachment != null && ec.insertedObjects().containsObject(attachment)) {
				ERAttachmentProcessor.processorForType(attachment).deleteAttachment(attachment);
				LOG.debug("Removed the file of an unsaved attachment: {}", attachment.originalFileName());
			}
		} catch (Exception e) {
			// Never let file cleanup stop the revert itself
			LOG.warn("Could not remove the file of an unsaved attachment", e);
		}
		super.willRevert();
	}
}
