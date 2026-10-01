// DO NOT EDIT.  Make changes to com.eltekfw.model.Security.java instead.
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
public abstract class _Security extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Security";

  // Attribute Keys
  public static final ERXKey<Boolean> APPROVE_INVOICE = new ERXKey<Boolean>("approveInvoice");
  public static final ERXKey<Boolean> CREATE_CLIENTS = new ERXKey<Boolean>("createClients");
  public static final ERXKey<Boolean> CREATE_PERSON = new ERXKey<Boolean>("createPerson");
  public static final ERXKey<Boolean> EDIT_CLIENTS = new ERXKey<Boolean>("editClients");
  // Relationship Keys
  public static final ERXKey<com.eltekfw.model.Person> PERSONS = new ERXKey<com.eltekfw.model.Person>("persons");

  // Attributes
  public static final String APPROVE_INVOICE_KEY = APPROVE_INVOICE.key();
  public static final String CREATE_CLIENTS_KEY = CREATE_CLIENTS.key();
  public static final String CREATE_PERSON_KEY = CREATE_PERSON.key();
  public static final String EDIT_CLIENTS_KEY = EDIT_CLIENTS.key();
  // Relationships
  public static final String PERSONS_KEY = PERSONS.key();

	private static final Logger LOG = LoggerFactory.getLogger(_Security.class);
	
  public com.eltekfw.model.Security localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Security localInstance = (com.eltekfw.model.Security)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean approveInvoice() {
    return (Boolean) storedValueForKey(_Security.APPROVE_INVOICE_KEY);
  }

  public void setApproveInvoice(Boolean value) {
	_Security.LOG.debug( "updating approveInvoice from {} to {}", approveInvoice(), value);
	takeStoredValueForKey(value, _Security.APPROVE_INVOICE_KEY);
  }

  public Boolean createClients() {
    return (Boolean) storedValueForKey(_Security.CREATE_CLIENTS_KEY);
  }

  public void setCreateClients(Boolean value) {
	_Security.LOG.debug( "updating createClients from {} to {}", createClients(), value);
	takeStoredValueForKey(value, _Security.CREATE_CLIENTS_KEY);
  }

  public Boolean createPerson() {
    return (Boolean) storedValueForKey(_Security.CREATE_PERSON_KEY);
  }

  public void setCreatePerson(Boolean value) {
	_Security.LOG.debug( "updating createPerson from {} to {}", createPerson(), value);
	takeStoredValueForKey(value, _Security.CREATE_PERSON_KEY);
  }

  public Boolean editClients() {
    return (Boolean) storedValueForKey(_Security.EDIT_CLIENTS_KEY);
  }

  public void setEditClients(Boolean value) {
	_Security.LOG.debug( "updating editClients from {} to {}", editClients(), value);
	takeStoredValueForKey(value, _Security.EDIT_CLIENTS_KEY);
  }

  public NSArray<com.eltekfw.model.Person> persons() {
    return (NSArray<com.eltekfw.model.Person>)storedValueForKey(_Security.PERSONS_KEY);
  }

  public NSArray<com.eltekfw.model.Person> persons(EOQualifier qualifier) {
    return persons(qualifier, null, false);
  }

  public NSArray<com.eltekfw.model.Person> persons(EOQualifier qualifier, boolean fetch) {
    return persons(qualifier, null, fetch);
  }

  public NSArray<com.eltekfw.model.Person> persons(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.eltekfw.model.Person> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.eltekfw.model.Person.SECURITY_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.eltekfw.model.Person.fetchPersons(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = persons();
      if (qualifier != null) {
        results = (NSArray<com.eltekfw.model.Person>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.eltekfw.model.Person>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToPersons(com.eltekfw.model.Person object) {
    includeObjectIntoPropertyWithKey(object, _Security.PERSONS_KEY);
  }

  public void removeFromPersons(com.eltekfw.model.Person object) {
    excludeObjectFromPropertyWithKey(object, _Security.PERSONS_KEY);
  }

  public void addToPersonsRelationship(com.eltekfw.model.Person object) {
    
    _Security.LOG.debug("adding {} to persons relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToPersons(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Security.PERSONS_KEY);
    }
  }

  public void removeFromPersonsRelationship(com.eltekfw.model.Person object) {
 
      _Security.LOG.debug("removing {} to persons relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromPersons(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Security.PERSONS_KEY);
    }
  }

  public com.eltekfw.model.Person createPersonsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.eltekfw.model.Person.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Security.PERSONS_KEY);
    return (com.eltekfw.model.Person) eo;
  }

  public void deletePersonsRelationship(com.eltekfw.model.Person object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Security.PERSONS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllPersonsRelationships() {
    Enumeration<com.eltekfw.model.Person> objects = persons().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deletePersonsRelationship(objects.nextElement());
    }
  }


  public static com.eltekfw.model.Security createSecurity(EOEditingContext editingContext, Boolean approveInvoice
, Boolean createClients
, Boolean createPerson
, Boolean editClients
) {
    com.eltekfw.model.Security eo = (com.eltekfw.model.Security) EOUtilities.createAndInsertInstance(editingContext, _Security.ENTITY_NAME);    
		eo.setApproveInvoice(approveInvoice);
		eo.setCreateClients(createClients);
		eo.setCreatePerson(createPerson);
		eo.setEditClients(editClients);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Security> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Security>(_Security.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Security> fetchAllSecurities(EOEditingContext editingContext) {
    return _Security.fetchAllSecurities(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Security> fetchAllSecurities(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Security.fetchSecurities(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Security> fetchSecurities(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Security> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Security>(_Security.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Security> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Security fetchSecurity(EOEditingContext editingContext, String keyName, Object value) {
    return _Security.fetchSecurity(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Security fetchSecurity(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Security> eoObjects = _Security.fetchSecurities(editingContext, qualifier, null);
    com.eltekfw.model.Security eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Security that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Security fetchRequiredSecurity(EOEditingContext editingContext, String keyName, Object value) {
    return _Security.fetchRequiredSecurity(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Security fetchRequiredSecurity(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Security eoObject = _Security.fetchSecurity(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Security that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Security localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Security eo) {
    com.eltekfw.model.Security localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
