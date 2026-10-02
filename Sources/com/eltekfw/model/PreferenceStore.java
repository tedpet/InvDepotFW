package com.eltekfw.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXFetchSpecification;

/**
 * Read-only, cached access to Preference rows (name -> value).
 *
 * Every getter takes a default, so a missing, blank or malformed row never
 * breaks a caller. All rows are loaded in one fetch and kept in memory for
 * TTL_MILLIS; call invalidate() after saving a Preference to see the change
 * immediately in this instance.
 */
public final class PreferenceStore {

    private static final Logger log = LoggerFactory.getLogger(PreferenceStore.class);

    /** How long a loaded snapshot is used before it is fetched again. */
    private static final long TTL_MILLIS = 60_000L;

    /** CSS lengths accepted in style attributes, e.g. 300px, 50%, 20em, none. */
    private static final Pattern CSS_LENGTH =
        Pattern.compile("^(none|auto|0|\\d+(\\.\\d+)?(px|em|rem|%|vh|vw|pt))$");

    private static volatile Map<String, String> snapshot = Collections.emptyMap();
    private static volatile long loadedAt = 0L;

    private PreferenceStore() {
    }

    // ---------- typed getters ----------

    public static String stringValue(String name, String defaultValue) {
        String v = values().get(name);
        return (v == null || v.isBlank()) ? defaultValue : v.trim();
    }

    public static int intValue(String name, int defaultValue) {
        String v = values().get(name);
        if (v == null || v.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            log.warn("Preference {} is not an integer: '{}'; using {}", name, v, defaultValue);
            return defaultValue;
        }
    }

    /**
     * A CSS length for use inside a style attribute. Anything that isn't a
     * plain length (so nothing that could inject other CSS or markup) falls
     * back to the default.
     */
    public static String cssLength(String name, String defaultValue) {
        String v = stringValue(name, defaultValue);
        if (CSS_LENGTH.matcher(v).matches()) {
            return v;
        }
        log.warn("Preference {} is not a CSS length: '{}'; using {}", name, v, defaultValue);
        return defaultValue;
    }

    /** Forces the next lookup to re-fetch from the database. */
    public static void invalidate() {
        loadedAt = 0L;
    }

    // ---------- loading ----------

    private static Map<String, String> values() {
        if (System.currentTimeMillis() - loadedAt > TTL_MILLIS) {
            reload();
        }
        return snapshot;
    }

    private static synchronized void reload() {
        if (System.currentTimeMillis() - loadedAt <= TTL_MILLIS) {
            return; // another thread reloaded while we waited
        }
        EOEditingContext ec = ERXEC.newEditingContext();
        ec.lock();
        try {
            // Always read current database values. Without this, EOF hands back
            // its cached row snapshots (up to the editing context's fetch
            // timestamp lag, an hour by default), so edits made outside this
            // app instance would not show up.
            ERXFetchSpecification<Preference> fs = Preference.fetchSpec();
            fs.setRefreshesRefetchedObjects(true);
            NSArray<Preference> prefs = fs.fetchObjects(ec);
            Map<String, String> m = new HashMap<>();
            for (Preference p : prefs) {
                if (p.name() != null) {
                    m.put(p.name().trim(), p.value());
                }
            }
            snapshot = Collections.unmodifiableMap(m);
        } catch (RuntimeException e) {
            // Keep the previous values; defaults cover anything missing.
            log.error("Could not load preferences; keeping previous values", e);
        } finally {
            loadedAt = System.currentTimeMillis();
            ec.unlock();
            ec.dispose();
        }
    }
}
