// DO NOT EDIT.  Make changes to com.eltekfw.model.Preference.java instead.
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
public abstract class _Preference extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Preference";

  // Attribute Keys
  public static final ERXKey<String> NAME = new ERXKey<String>("name");
  public static final ERXKey<String> VALUE = new ERXKey<String>("value");
  // Relationship Keys
  public static final ERXKey<com.eltekfw.model.Person> USER = new ERXKey<com.eltekfw.model.Person>("user");

  // Attributes
  public static final String NAME_KEY = NAME.key();
  public static final String VALUE_KEY = VALUE.key();
  // Relationships
  public static final String USER_KEY = USER.key();

	private static final Logger LOG = LoggerFactory.getLogger(_Preference.class);
	
  public com.eltekfw.model.Preference localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Preference localInstance = (com.eltekfw.model.Preference)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String name() {
    return (String) storedValueForKey(_Preference.NAME_KEY);
  }

  public void setName(String value) {
	_Preference.LOG.debug( "updating name from {} to {}", name(), value);
	takeStoredValueForKey(value, _Preference.NAME_KEY);
  }

  public String value() {
    return (String) storedValueForKey(_Preference.VALUE_KEY);
  }

  public void setValue(String value) {
	_Preference.LOG.debug( "updating value from {} to {}", value(), value);
	takeStoredValueForKey(value, _Preference.VALUE_KEY);
  }

  public com.eltekfw.model.Person user() {
    return (com.eltekfw.model.Person)storedValueForKey(_Preference.USER_KEY);
  }
  
  public void setUser(com.eltekfw.model.Person value) {
    takeStoredValueForKey(value, _Preference.USER_KEY);
  }

  public void setUserRelationship(com.eltekfw.model.Person value) {

	  _Preference.LOG.debug("updating user from {} to {}", user(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setUser(value);
    }
    else if (value == null) {
    	com.eltekfw.model.Person oldValue = user();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Preference.USER_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Preference.USER_KEY);
    }
  }
  

  public static com.eltekfw.model.Preference createPreference(EOEditingContext editingContext, String name
, String value
) {
    com.eltekfw.model.Preference eo = (com.eltekfw.model.Preference) EOUtilities.createAndInsertInstance(editingContext, _Preference.ENTITY_NAME);    
		eo.setName(name);
		eo.setValue(value);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Preference> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Preference>(_Preference.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Preference> fetchAllPreferences(EOEditingContext editingContext) {
    return _Preference.fetchAllPreferences(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Preference> fetchAllPreferences(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Preference.fetchPreferences(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Preference> fetchPreferences(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Preference> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Preference>(_Preference.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Preference> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Preference fetchPreference(EOEditingContext editingContext, String keyName, Object value) {
    return _Preference.fetchPreference(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Preference fetchPreference(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Preference> eoObjects = _Preference.fetchPreferences(editingContext, qualifier, null);
    com.eltekfw.model.Preference eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Preference that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Preference fetchRequiredPreference(EOEditingContext editingContext, String keyName, Object value) {
    return _Preference.fetchRequiredPreference(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Preference fetchRequiredPreference(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Preference eoObject = _Preference.fetchPreference(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Preference that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Preference localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Preference eo) {
    com.eltekfw.model.Preference localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
