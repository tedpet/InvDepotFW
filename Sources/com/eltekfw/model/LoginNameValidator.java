package com.eltekfw.model;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXGenericRecord;

/**
 * Login names are shared by Person and Vendor (either can log in), so a login
 * name must be unique across both entities.
 */
public final class LoginNameValidator {

	/** Both Person and Vendor name the attribute "loginName". */
	public static final String LOGIN_NAME_KEY = Person.LOGIN_NAME_KEY;

	private LoginNameValidator() {
	}

	/**
	 * Call from validateForSave() of a Person or Vendor. Throws if another Person
	 * or Vendor already uses the login name. Only checks when the object is new or
	 * its login name has changed, so ordinary saves do not cost extra fetches.
	 */
	public static void validateUnique(ERXGenericRecord eo, String loginName) throws NSValidation.ValidationException {
		if (loginName == null || loginName.isBlank()) {
			return; // a missing login name is reported by the model's own not-null validation
		}
		if (!eo.isNewObject() && !eo.changesFromCommittedSnapshot().containsKey(LOGIN_NAME_KEY)) {
			return;
		}

		EOEditingContext ec = eo.editingContext();

		// Already saved in the database
		for (Person p : Person.fetchPersons(ec, Person.LOGIN_NAME.eq(loginName), null)) {
			failIfOther(eo, p, loginName);
		}
		for (Vendor v : Vendor.fetchVendors(ec, Vendor.LOGIN_NAME.eq(loginName), null)) {
			failIfOther(eo, v, loginName);
		}

		// Created in this editing context but not saved yet
		for (Object o : ec.insertedObjects()) {
			if (o instanceof Person p && loginName.equals(p.loginName())) {
				failIfOther(eo, p, loginName);
			} else if (o instanceof Vendor v && loginName.equals(v.loginName())) {
				failIfOther(eo, v, loginName);
			}
		}
	}

	private static void failIfOther(ERXGenericRecord eo, Object other, String loginName) {
		if (other != eo) {
			throw new NSValidation.ValidationException(
					"The login name \"" + loginName + "\" is already used by another person or vendor. Please choose a different one.",
					eo, LOGIN_NAME_KEY);
		}
	}
}
