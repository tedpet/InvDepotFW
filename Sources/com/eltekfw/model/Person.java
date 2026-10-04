package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eltekfw.model.Security;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSValidation;
import com.webobjects.foundation.NSMutableSet;

import er.extensions.eof.ERXEC;

import com.eltekfw.model.eogen._Person;
import com.eltekfw.utilities.EltekUtilities;

public class Person extends _Person {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(Person.class);

	public void init(EOEditingContext ec) {
		super.init(ec);
		log.debug("initializing a Person");
		setCurrent(true);
		setAdministrator(false);
					// approveInvoice,    createClients,     createPerson,      editClients
		
		setSecurityRelationship(Security.createSecurity(ec, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));
		addMissingPreferences(ec);
	}
	
	public static Person validateLogin(EOEditingContext editingContext, String username, String password) {
		log.debug("we have entered validate    Login in the Person Entity: " + password + "  :  "  + username );
		
		EOQualifier qual = Person.CURRENT.eq(true).and(Person.LOGIN_NAME.eq(username)).and(Person.PASSWORD.eq(EltekUtilities.SHABase64String(password)));
		//System.out.println("qualifier = " + qual);
		
		
		Person user = Person.fetchRequiredPerson(editingContext, qual);
		
		log.debug("user = {}", user);
		//Person user = Person.fetchPerson(editingContext, qual);
		return user;
	}

	/** Copies every global preference this person doesn't have yet. Returns true if any were added. */
	public boolean addMissingPreferences(EOEditingContext ec) {
	    NSMutableSet<String> have = new NSMutableSet<>();
	    for (Preference p : preferences()) {
	        have.addObject(p.name());
	    }
	    boolean added = false;
	    for (Preference g : Preference.fetchPreferences(ec, Preference.USER.isNull(), null)) {
	        if (!have.containsObject(g.name())) {
	            Preference p = Preference.createPreference(ec, g.name(), g.value());
	            p.setUserRelationship(this);
	            added = true;
	        }
	    }
	    return added;
	}

	/** Called at login: tops up the person's preferences in a separate editing context and saves. */
	public static void ensurePreferences(Person person) {
	    EOEditingContext ec = ERXEC.newEditingContext();
	    ec.lock();
	    try {
	        Person p = person.localInstanceIn(ec);
	        if (p.addMissingPreferences(ec)) {
	            ec.saveChanges();
	            PreferenceStore.invalidate();
	        }
	    } catch (RuntimeException e) {
	        LoggerFactory.getLogger(Person.class).warn("Could not add preferences for {}", person, e);
	    } finally {
	        ec.unlock();
	        ec.dispose();
	    }
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

	/** A login name must be unique across every Person and Vendor. */
	@Override
	public void validateForSave() throws NSValidation.ValidationException {
		super.validateForSave();
		LoginNameValidator.validateUnique(this, loginName());
	}

}
