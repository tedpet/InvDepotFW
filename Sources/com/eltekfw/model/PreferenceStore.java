package com.eltekfw.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOGlobalID;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCoding;

import er.corebusinesslogic.ERCoreBusinessLogic;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXFetchSpecification;
import er.extensions.foundation.ERXThreadStorage;

/**
 * Read-only, cached access to Preference rows, with per-user overrides.
 *
 * Lookup order for every getter:
 *   1. the user's own row  (Preference.user = that user)
 *   2. the global row      (Preference.user = null)
 *   3. the default passed in by the caller
 *
 * The getters without a user argument use the current actor
 * (ERCoreBusinessLogic.actor()), so existing calls such as
 * PreferenceStore.intValue("attachment.sheet.maxRows", 20) become
 * per-user automatically. When there is no actor, only global rows apply.
 *
 * All rows are loaded in one fetch and kept for TTL_MILLIS; call
 * invalidate() after saving a Preference to see the change at once.
 */
public final class PreferenceStore {

    private static final Logger log = LoggerFactory.getLogger(PreferenceStore.class);

    /** Name of the to-one relationship from Preference to the user entity. */
    private static final String USER_RELATIONSHIP = "user";

    /** How long a loaded snapshot is used before it is fetched again. */
    private static final long TTL_MILLIS = 60_000L;

    /** CSS lengths accepted in style attributes, e.g. 300px, 50%, 20em, none. */
    private static final Pattern CSS_LENGTH =
        Pattern.compile("^(none|auto|0|\\d+(\\.\\d+)?(px|em|rem|%|vh|vw|pt))$");

    /** Map key for the global (user = null) rows. */
    private static final Object GLOBAL = new Object();

    /** owner (GLOBAL or the user's EOGlobalID) -> (name -> value) */
    private static volatile Map<Object, Map<String, String>> snapshot = Collections.emptyMap();
    private static volatile long loadedAt = 0L;

    private PreferenceStore() {
    }

    // ---------- getters for the current user (actor) ----------

    public static String stringValue(String name, String defaultValue) {
        return stringValue(currentUser(), name, defaultValue);
    }

    public static int intValue(String name, int defaultValue) {
        return intValue(currentUser(), name, defaultValue);
    }

    public static String cssLength(String name, String defaultValue) {
        return cssLength(currentUser(), name, defaultValue);
    }

    // ---------- getters for an explicit user (null = global only) ----------

    public static String stringValue(EOEnterpriseObject user, String name, String defaultValue) {
        String v = rawValue(user, name);
        return (v == null) ? defaultValue : v;
    }

    public static int intValue(EOEnterpriseObject user, String name, int defaultValue) {
        String v = rawValue(user, name);
        if (v == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(v);
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
    public static String cssLength(EOEnterpriseObject user, String name, String defaultValue) {
        String v = stringValue(user, name, defaultValue);
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

    // ---------- lookup ----------

    /** User's value, else global value, else null. Blank values count as missing. */
    private static String rawValue(EOEnterpriseObject user, String name) {
        Map<Object, Map<String, String>> all = values();
        Object owner = ownerKey(user);
        if (owner != null) {
            String v = nonBlank(all.getOrDefault(owner, Collections.emptyMap()).get(name));
            if (v != null) {
                return v;
            }
        }
        return nonBlank(all.getOrDefault(GLOBAL, Collections.emptyMap()).get(name));
    }

    private static String nonBlank(String v) {
        return (v == null || v.isBlank()) ? null : v.trim();
    }

    private static EOEnterpriseObject currentUser() {
        Object p = ERXThreadStorage.valueForKey("currentPerson");
        return (p instanceof EOEnterpriseObject) ? (EOEnterpriseObject) p : null;
    }
    
    /** The user's global ID (the same in every editing context), or null. */
    private static Object ownerKey(EOEnterpriseObject user) {
        if (user == null || user.editingContext() == null) {
            return null;
        }
        EOGlobalID gid = user.editingContext().globalIDForObject(user);
        return (gid == null || gid.isTemporary()) ? null : gid;
    }

    // ---------- loading ----------

    private static Map<Object, Map<String, String>> values() {
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
            // Always read current database values, not EOF's cached snapshots.
            ERXFetchSpecification<Preference> fs = Preference.fetchSpec();
            fs.setRefreshesRefetchedObjects(true);
            NSArray<Preference> prefs = fs.fetchObjects(ec);

            Map<Object, Map<String, String>> m = new HashMap<>();
            boolean hasUserRelationship = true;
            for (Preference p : prefs) {
                if (p.name() == null) {
                    continue;
                }
                Object owner = GLOBAL;
                if (hasUserRelationship) {
                    try {
                        // Reads the foreign key's global ID; the user itself is not fetched.
                        Object u = p.valueForKey(USER_RELATIONSHIP);
                        if (u instanceof EOEnterpriseObject) {
                            owner = ec.globalIDForObject((EOEnterpriseObject) u);
                        }
                    } catch (NSKeyValueCoding.UnknownKeyException e) {
                        log.warn("Preference has no '{}' relationship yet; treating all rows as global",
                                 USER_RELATIONSHIP);
                        hasUserRelationship = false;
                    }
                }
                m.computeIfAbsent(owner, k -> new HashMap<>()).put(p.name().trim(), p.value());
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
