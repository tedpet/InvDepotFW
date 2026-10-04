// DO NOT EDIT.  Make changes to com.eltekfw.model.Person.java instead.
package com.eltekfw.model.eogen;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _Person extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Person";

  // Attribute Keys
  public static final ERXKey<Boolean> ADMINISTRATOR = new ERXKey<Boolean>("administrator");
  public static final ERXKey<Boolean> CURRENT = new ERXKey<Boolean>("current");
  public static final ERXKey<String> EMAIL_ADDRESS = new ERXKey<String>("emailAddress");
  public static final ERXKey<String> FIRST_NAME = new ERXKey<String>("firstName");
  public static final ERXKey<String> LAST_NAME = new ERXKey<String>("lastName");
  public static final ERXKey<String> LOGIN_NAME = new ERXKey<String>("loginName");
  public static final ERXKey<String> PASSWORD = new ERXKey<String>("password");
  public static final ERXKey<String> PHONE_NUMBER = new ERXKey<String>("phoneNumber");
  // Relationship Keys
  public static final ERXKey<com.eltekfw.model.Preference> PREFERENCES = new ERXKey<com.eltekfw.model.Preference>("preferences");
  public static final ERXKey<com.eltekfw.model.Security> SECURITY = new ERXKey<com.eltekfw.model.Security>("security");
  public static final ERXKey<com.eltekfw.model.Vendor> VENDORS = new ERXKey<com.eltekfw.model.Vendor>("vendors");

  // Attributes
  public static final String ADMINISTRATOR_KEY = ADMINISTRATOR.key();
  public static final String CURRENT_KEY = CURRENT.key();
  public static final String EMAIL_ADDRESS_KEY = EMAIL_ADDRESS.key();
  public static final String FIRST_NAME_KEY = FIRST_NAME.key();
  public static final String LAST_NAME_KEY = LAST_NAME.key();
  public static final String LOGIN_NAME_KEY = LOGIN_NAME.key();
  public static final String PASSWORD_KEY = PASSWORD.key();
  public static final String PHONE_NUMBER_KEY = PHONE_NUMBER.key();
  // Relationships
  public static final String PREFERENCES_KEY = PREFERENCES.key();
  public static final String SECURITY_KEY = SECURITY.key();
  public static final String VENDORS_KEY = VENDORS.key();

	private static final Logger LOG = LoggerFactory.getLogger(_Person.class);
	
  public com.eltekfw.model.Person localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Person localInstance = (com.eltekfw.model.Person)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean administrator() {
    return (Boolean) storedValueForKey(_Person.ADMINISTRATOR_KEY);
  }

  public void setAdministrator(Boolean value) {
	_Person.LOG.debug( "updating administrator from {} to {}", administrator(), value);
	takeStoredValueForKey(value, _Person.ADMINISTRATOR_KEY);
  }

  public Boolean current() {
    return (Boolean) storedValueForKey(_Person.CURRENT_KEY);
  }

  public void setCurrent(Boolean value) {
	_Person.LOG.debug( "updating current from {} to {}", current(), value);
	takeStoredValueForKey(value, _Person.CURRENT_KEY);
  }

  public String emailAddress() {
    return (String) storedValueForKey(_Person.EMAIL_ADDRESS_KEY);
  }

  public void setEmailAddress(String value) {
	_Person.LOG.debug( "updating emailAddress from {} to {}", emailAddress(), value);
	takeStoredValueForKey(value, _Person.EMAIL_ADDRESS_KEY);
  }

  public String firstName() {
    return (String) storedValueForKey(_Person.FIRST_NAME_KEY);
  }

  public void setFirstName(String value) {
	_Person.LOG.debug( "updating firstName from {} to {}", firstName(), value);
	takeStoredValueForKey(value, _Person.FIRST_NAME_KEY);
  }

  public String lastName() {
    return (String) storedValueForKey(_Person.LAST_NAME_KEY);
  }

  public void setLastName(String value) {
	_Person.LOG.debug( "updating lastName from {} to {}", lastName(), value);
	takeStoredValueForKey(value, _Person.LAST_NAME_KEY);
  }

  public String loginName() {
    return (String) storedValueForKey(_Person.LOGIN_NAME_KEY);
  }

  public void setLoginName(String value) {
	_Person.LOG.debug( "updating loginName from {} to {}", loginName(), value);
	takeStoredValueForKey(value, _Person.LOGIN_NAME_KEY);
  }

  public String password() {
    return (String) storedValueForKey(_Person.PASSWORD_KEY);
  }

  public void setPassword(String value) {
	_Person.LOG.debug( "updating password from {} to {}", password(), value);
	takeStoredValueForKey(value, _Person.PASSWORD_KEY);
  }

  public String phoneNumber() {
    return (String) storedValueForKey(_Person.PHONE_NUMBER_KEY);
  }

  public void setPhoneNumber(String value) {
	_Person.LOG.debug( "updating phoneNumber from {} to {}", phoneNumber(), value);
	takeStoredValueForKey(value, _Person.PHONE_NUMBER_KEY);
  }

  public com.eltekfw.model.Security security() {
    return (com.eltekfw.model.Security)storedValueForKey(_Person.SECURITY_KEY);
  }
  
  public void setSecurity(com.eltekfw.model.Security value) {
    takeStoredValueForKey(value, _Person.SECURITY_KEY);
  }

  public void setSecurityRelationship(com.eltekfw.model.Security value) {

	  _Person.LOG.debug("updating security from {} to {}", security(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setSecurity(value);
    }
    else if (value == null) {
    	com.eltekfw.model.Security oldValue = security();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Person.SECURITY_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Person.SECURITY_KEY);
    }
  }
  
  public NSArray<com.eltekfw.model.Preference> preferences() {
    return (NSArray<com.eltekfw.model.Preference>)storedValueForKey(_Person.PREFERENCES_KEY);
  }

  public NSArray<com.eltekfw.model.Preference> preferences(EOQualifier qualifier) {
    return preferences(qualifier, null, false);
  }

  public NSArray<com.eltekfw.model.Preference> preferences(EOQualifier qualifier, boolean fetch) {
    return preferences(qualifier, null, fetch);
  }

  public NSArray<com.eltekfw.model.Preference> preferences(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.eltekfw.model.Preference> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.eltekfw.model.Preference.USER_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.eltekfw.model.Preference.fetchPreferences(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = preferences();
      if (qualifier != null) {
        results = (NSArray<com.eltekfw.model.Preference>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.eltekfw.model.Preference>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToPreferences(com.eltekfw.model.Preference object) {
    includeObjectIntoPropertyWithKey(object, _Person.PREFERENCES_KEY);
  }

  public void removeFromPreferences(com.eltekfw.model.Preference object) {
    excludeObjectFromPropertyWithKey(object, _Person.PREFERENCES_KEY);
  }

  public void addToPreferencesRelationship(com.eltekfw.model.Preference object) {
    
    _Person.LOG.debug("adding {} to preferences relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToPreferences(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Person.PREFERENCES_KEY);
    }
  }

  public void removeFromPreferencesRelationship(com.eltekfw.model.Preference object) {
 
      _Person.LOG.debug("removing {} to preferences relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromPreferences(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.PREFERENCES_KEY);
    }
  }

  public com.eltekfw.model.Preference createPreferencesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.eltekfw.model.Preference.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Person.PREFERENCES_KEY);
    return (com.eltekfw.model.Preference) eo;
  }

  public void deletePreferencesRelationship(com.eltekfw.model.Preference object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.PREFERENCES_KEY);
  }

  public void deleteAllPreferencesRelationships() {
    Enumeration<com.eltekfw.model.Preference> objects = preferences().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deletePreferencesRelationship(objects.nextElement());
    }
  }

  public NSArray<com.eltekfw.model.Vendor> vendors() {
    return (NSArray<com.eltekfw.model.Vendor>)storedValueForKey(_Person.VENDORS_KEY);
  }

  public NSArray<com.eltekfw.model.Vendor> vendors(EOQualifier qualifier) {
    return vendors(qualifier, null, false);
  }

  public NSArray<com.eltekfw.model.Vendor> vendors(EOQualifier qualifier, boolean fetch) {
    return vendors(qualifier, null, fetch);
  }

  public NSArray<com.eltekfw.model.Vendor> vendors(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.eltekfw.model.Vendor> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.eltekfw.model.Vendor.PERSON_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.eltekfw.model.Vendor.fetchVendors(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = vendors();
      if (qualifier != null) {
        results = (NSArray<com.eltekfw.model.Vendor>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.eltekfw.model.Vendor>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToVendors(com.eltekfw.model.Vendor object) {
    includeObjectIntoPropertyWithKey(object, _Person.VENDORS_KEY);
  }

  public void removeFromVendors(com.eltekfw.model.Vendor object) {
    excludeObjectFromPropertyWithKey(object, _Person.VENDORS_KEY);
  }

  public void addToVendorsRelationship(com.eltekfw.model.Vendor object) {
    
    _Person.LOG.debug("adding {} to vendors relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToVendors(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Person.VENDORS_KEY);
    }
  }

  public void removeFromVendorsRelationship(com.eltekfw.model.Vendor object) {
 
      _Person.LOG.debug("removing {} to vendors relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromVendors(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.VENDORS_KEY);
    }
  }

  public com.eltekfw.model.Vendor createVendorsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.eltekfw.model.Vendor.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Person.VENDORS_KEY);
    return (com.eltekfw.model.Vendor) eo;
  }

  public void deleteVendorsRelationship(com.eltekfw.model.Vendor object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Person.VENDORS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllVendorsRelationships() {
    Enumeration<com.eltekfw.model.Vendor> objects = vendors().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteVendorsRelationship(objects.nextElement());
    }
  }


  public static com.eltekfw.model.Person createPerson(EOEditingContext editingContext, Boolean administrator
, Boolean current
, String firstName
, String loginName
, String password
, com.eltekfw.model.Security security) {
    com.eltekfw.model.Person eo = (com.eltekfw.model.Person) EOUtilities.createAndInsertInstance(editingContext, _Person.ENTITY_NAME);    
		eo.setAdministrator(administrator);
		eo.setCurrent(current);
		eo.setFirstName(firstName);
		eo.setLoginName(loginName);
		eo.setPassword(password);
    eo.setSecurityRelationship(security);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Person> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Person>(_Person.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Person> fetchAllPersons(EOEditingContext editingContext) {
    return _Person.fetchAllPersons(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Person> fetchAllPersons(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Person.fetchPersons(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Person> fetchPersons(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Person> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Person>(_Person.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Person> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Person fetchPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Person fetchPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Person> eoObjects = _Person.fetchPersons(editingContext, qualifier, null);
    com.eltekfw.model.Person eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Person fetchRequiredPerson(EOEditingContext editingContext, String keyName, Object value) {
    return _Person.fetchRequiredPerson(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Person fetchRequiredPerson(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Person eoObject = _Person.fetchPerson(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Person that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Person localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Person eo) {
    com.eltekfw.model.Person localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
