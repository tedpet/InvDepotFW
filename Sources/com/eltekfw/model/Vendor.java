package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eltekfw.utilities.EltekUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSValidation;

public class Vendor extends com.eltekfw.model.eogen._Vendor {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(com.eltekfw.model.eogen._Vendor.class);

	
	public void init(EOEditingContext ec) {
		super.init(ec);
		log.debug("initializing a Vendor");
		setCurrent(true);
		
		
	}
	
	public static Vendor validateLogin(EOEditingContext editingContext, String username, String password) {
		log.debug("we have entered validate    Login in the Person Entity: {} : {}", password + "  :  ", username );
		
		EOQualifier qual = Vendor.CURRENT.eq(true).and(Vendor.LOGIN_NAME.eq(username)).and(Vendor.PASSWORD.eq(EltekUtilities.SHABase64String(password)));
		Vendor user = Vendor.fetchRequiredVendor(editingContext, qual);
		
		log.debug("user = {}", user);
		return user;
	}

	/** A login name must be unique across every Person and Vendor. */
	@Override
	public void validateForSave() throws NSValidation.ValidationException {
		super.validateForSave();
		LoginNameValidator.validateUnique(this, loginName());
	}

	// SHA-512 base64 output is always exactly 88 characters. Values already
	// at this length were pre-hashed (e.g. by the iOS API) and must not be
	// hashed a second time.
	private static final int HASHED_PASSWORD_LENGTH = 88;

	@Override
	public void willUpdate() {
		super.willUpdate();

		Object committed = committedSnapshotValueForKey(PASSWORD_KEY);
		Object current   = valueForKey(PASSWORD_KEY);

		if (current instanceof String) {
			String currentStr = (String) current;
			boolean changed    = !currentStr.equals(committed);
			boolean preHashed  = currentStr.length() == HASHED_PASSWORD_LENGTH;
			if (changed && !preHashed) {
				takeStoredValueForKey(EltekUtilities.SHABase64String(currentStr), PASSWORD_KEY);
			}
		}
	}

}
