// DO NOT EDIT.  Make changes to com.eltekfw.model.Vendor.java instead.
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
public abstract class _Vendor extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Vendor";

  // Attribute Keys
  public static final ERXKey<Boolean> CURRENT = new ERXKey<Boolean>("current");
  public static final ERXKey<String> PASSWORD = new ERXKey<String>("password");
  public static final ERXKey<String> VENDOR_NAME = new ERXKey<String>("vendorName");
  // Relationship Keys
  public static final ERXKey<com.eltekfw.model.Invoice> INVOICES = new ERXKey<com.eltekfw.model.Invoice>("invoices");
  public static final ERXKey<com.eltekfw.model.Person> PERSON = new ERXKey<com.eltekfw.model.Person>("person");

  // Attributes
  public static final String CURRENT_KEY = CURRENT.key();
  public static final String PASSWORD_KEY = PASSWORD.key();
  public static final String VENDOR_NAME_KEY = VENDOR_NAME.key();
  // Relationships
  public static final String INVOICES_KEY = INVOICES.key();
  public static final String PERSON_KEY = PERSON.key();

	private static final Logger LOG = LoggerFactory.getLogger(_Vendor.class);
	
  public com.eltekfw.model.Vendor localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Vendor localInstance = (com.eltekfw.model.Vendor)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean current() {
    return (Boolean) storedValueForKey(_Vendor.CURRENT_KEY);
  }

  public void setCurrent(Boolean value) {
	_Vendor.LOG.debug( "updating current from {} to {}", current(), value);
	takeStoredValueForKey(value, _Vendor.CURRENT_KEY);
  }

  public String password() {
    return (String) storedValueForKey(_Vendor.PASSWORD_KEY);
  }

  public void setPassword(String value) {
	_Vendor.LOG.debug( "updating password from {} to {}", password(), value);
	takeStoredValueForKey(value, _Vendor.PASSWORD_KEY);
  }

  public String vendorName() {
    return (String) storedValueForKey(_Vendor.VENDOR_NAME_KEY);
  }

  public void setVendorName(String value) {
	_Vendor.LOG.debug( "updating vendorName from {} to {}", vendorName(), value);
	takeStoredValueForKey(value, _Vendor.VENDOR_NAME_KEY);
  }

  public com.eltekfw.model.Person person() {
    return (com.eltekfw.model.Person)storedValueForKey(_Vendor.PERSON_KEY);
  }
  
  public void setPerson(com.eltekfw.model.Person value) {
    takeStoredValueForKey(value, _Vendor.PERSON_KEY);
  }

  public void setPersonRelationship(com.eltekfw.model.Person value) {

	  _Vendor.LOG.debug("updating person from {} to {}", person(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setPerson(value);
    }
    else if (value == null) {
    	com.eltekfw.model.Person oldValue = person();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Vendor.PERSON_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Vendor.PERSON_KEY);
    }
  }
  
  public NSArray<com.eltekfw.model.Invoice> invoices() {
    return (NSArray<com.eltekfw.model.Invoice>)storedValueForKey(_Vendor.INVOICES_KEY);
  }

  public NSArray<com.eltekfw.model.Invoice> invoices(EOQualifier qualifier) {
    return invoices(qualifier, null, false);
  }

  public NSArray<com.eltekfw.model.Invoice> invoices(EOQualifier qualifier, boolean fetch) {
    return invoices(qualifier, null, fetch);
  }

  public NSArray<com.eltekfw.model.Invoice> invoices(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.eltekfw.model.Invoice> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.eltekfw.model.Invoice.VENDOR_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.eltekfw.model.Invoice.fetchInvoices(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = invoices();
      if (qualifier != null) {
        results = (NSArray<com.eltekfw.model.Invoice>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.eltekfw.model.Invoice>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToInvoices(com.eltekfw.model.Invoice object) {
    includeObjectIntoPropertyWithKey(object, _Vendor.INVOICES_KEY);
  }

  public void removeFromInvoices(com.eltekfw.model.Invoice object) {
    excludeObjectFromPropertyWithKey(object, _Vendor.INVOICES_KEY);
  }

  public void addToInvoicesRelationship(com.eltekfw.model.Invoice object) {
    
    _Vendor.LOG.debug("adding {} to invoices relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToInvoices(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Vendor.INVOICES_KEY);
    }
  }

  public void removeFromInvoicesRelationship(com.eltekfw.model.Invoice object) {
 
      _Vendor.LOG.debug("removing {} to invoices relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromInvoices(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Vendor.INVOICES_KEY);
    }
  }

  public com.eltekfw.model.Invoice createInvoicesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.eltekfw.model.Invoice.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Vendor.INVOICES_KEY);
    return (com.eltekfw.model.Invoice) eo;
  }

  public void deleteInvoicesRelationship(com.eltekfw.model.Invoice object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Vendor.INVOICES_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllInvoicesRelationships() {
    Enumeration<com.eltekfw.model.Invoice> objects = invoices().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteInvoicesRelationship(objects.nextElement());
    }
  }


  public static com.eltekfw.model.Vendor createVendor(EOEditingContext editingContext, Boolean current
, String password
, String vendorName
, com.eltekfw.model.Person person) {
    com.eltekfw.model.Vendor eo = (com.eltekfw.model.Vendor) EOUtilities.createAndInsertInstance(editingContext, _Vendor.ENTITY_NAME);    
		eo.setCurrent(current);
		eo.setPassword(password);
		eo.setVendorName(vendorName);
    eo.setPersonRelationship(person);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Vendor> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Vendor>(_Vendor.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Vendor> fetchAllVendors(EOEditingContext editingContext) {
    return _Vendor.fetchAllVendors(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Vendor> fetchAllVendors(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Vendor.fetchVendors(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Vendor> fetchVendors(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Vendor> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Vendor>(_Vendor.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Vendor> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Vendor fetchVendor(EOEditingContext editingContext, String keyName, Object value) {
    return _Vendor.fetchVendor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Vendor fetchVendor(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Vendor> eoObjects = _Vendor.fetchVendors(editingContext, qualifier, null);
    com.eltekfw.model.Vendor eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Vendor that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Vendor fetchRequiredVendor(EOEditingContext editingContext, String keyName, Object value) {
    return _Vendor.fetchRequiredVendor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Vendor fetchRequiredVendor(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Vendor eoObject = _Vendor.fetchVendor(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Vendor that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Vendor localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Vendor eo) {
    com.eltekfw.model.Vendor localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
