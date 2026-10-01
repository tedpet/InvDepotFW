// DO NOT EDIT.  Make changes to com.eltekfw.model.Client.java instead.
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
public abstract class _Client extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Client";

  // Attribute Keys
  public static final ERXKey<String> ADRESS_LINE1 = new ERXKey<String>("adressLine1");
  public static final ERXKey<String> ADRESS_LINE2 = new ERXKey<String>("adressLine2");
  public static final ERXKey<String> CLIENT_CITY = new ERXKey<String>("clientCity");
  public static final ERXKey<String> CLIENT_NAME = new ERXKey<String>("clientName");
  public static final ERXKey<String> CLIENT_POST_CODE = new ERXKey<String>("clientPostCode");
  public static final ERXKey<String> CLIENT_STATE = new ERXKey<String>("clientState");
  public static final ERXKey<Boolean> CURRENT = new ERXKey<Boolean>("current");
  // Relationship Keys

  // Attributes
  public static final String ADRESS_LINE1_KEY = ADRESS_LINE1.key();
  public static final String ADRESS_LINE2_KEY = ADRESS_LINE2.key();
  public static final String CLIENT_CITY_KEY = CLIENT_CITY.key();
  public static final String CLIENT_NAME_KEY = CLIENT_NAME.key();
  public static final String CLIENT_POST_CODE_KEY = CLIENT_POST_CODE.key();
  public static final String CLIENT_STATE_KEY = CLIENT_STATE.key();
  public static final String CURRENT_KEY = CURRENT.key();
  // Relationships

	private static final Logger LOG = LoggerFactory.getLogger(_Client.class);
	
  public com.eltekfw.model.Client localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Client localInstance = (com.eltekfw.model.Client)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String adressLine1() {
    return (String) storedValueForKey(_Client.ADRESS_LINE1_KEY);
  }

  public void setAdressLine1(String value) {
	_Client.LOG.debug( "updating adressLine1 from {} to {}", adressLine1(), value);
	takeStoredValueForKey(value, _Client.ADRESS_LINE1_KEY);
  }

  public String adressLine2() {
    return (String) storedValueForKey(_Client.ADRESS_LINE2_KEY);
  }

  public void setAdressLine2(String value) {
	_Client.LOG.debug( "updating adressLine2 from {} to {}", adressLine2(), value);
	takeStoredValueForKey(value, _Client.ADRESS_LINE2_KEY);
  }

  public String clientCity() {
    return (String) storedValueForKey(_Client.CLIENT_CITY_KEY);
  }

  public void setClientCity(String value) {
	_Client.LOG.debug( "updating clientCity from {} to {}", clientCity(), value);
	takeStoredValueForKey(value, _Client.CLIENT_CITY_KEY);
  }

  public String clientName() {
    return (String) storedValueForKey(_Client.CLIENT_NAME_KEY);
  }

  public void setClientName(String value) {
	_Client.LOG.debug( "updating clientName from {} to {}", clientName(), value);
	takeStoredValueForKey(value, _Client.CLIENT_NAME_KEY);
  }

  public String clientPostCode() {
    return (String) storedValueForKey(_Client.CLIENT_POST_CODE_KEY);
  }

  public void setClientPostCode(String value) {
	_Client.LOG.debug( "updating clientPostCode from {} to {}", clientPostCode(), value);
	takeStoredValueForKey(value, _Client.CLIENT_POST_CODE_KEY);
  }

  public String clientState() {
    return (String) storedValueForKey(_Client.CLIENT_STATE_KEY);
  }

  public void setClientState(String value) {
	_Client.LOG.debug( "updating clientState from {} to {}", clientState(), value);
	takeStoredValueForKey(value, _Client.CLIENT_STATE_KEY);
  }

  public Boolean current() {
    return (Boolean) storedValueForKey(_Client.CURRENT_KEY);
  }

  public void setCurrent(Boolean value) {
	_Client.LOG.debug( "updating current from {} to {}", current(), value);
	takeStoredValueForKey(value, _Client.CURRENT_KEY);
  }


  public static com.eltekfw.model.Client createClient(EOEditingContext editingContext, Boolean current
) {
    com.eltekfw.model.Client eo = (com.eltekfw.model.Client) EOUtilities.createAndInsertInstance(editingContext, _Client.ENTITY_NAME);    
		eo.setCurrent(current);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Client> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Client>(_Client.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Client> fetchAllClients(EOEditingContext editingContext) {
    return _Client.fetchAllClients(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Client> fetchAllClients(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Client.fetchClients(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Client> fetchClients(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Client> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Client>(_Client.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Client> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Client fetchClient(EOEditingContext editingContext, String keyName, Object value) {
    return _Client.fetchClient(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Client fetchClient(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Client> eoObjects = _Client.fetchClients(editingContext, qualifier, null);
    com.eltekfw.model.Client eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Client that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Client fetchRequiredClient(EOEditingContext editingContext, String keyName, Object value) {
    return _Client.fetchRequiredClient(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Client fetchRequiredClient(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Client eoObject = _Client.fetchClient(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Client that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Client localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Client eo) {
    com.eltekfw.model.Client localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
