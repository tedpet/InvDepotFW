// DO NOT EDIT.  Make changes to com.eltekfw.model.Invoice.java instead.
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
public abstract class _Invoice extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Invoice";

  // Attribute Keys
  public static final ERXKey<Boolean> CURRENT = new ERXKey<Boolean>("current");
  public static final ERXKey<String> JOB_NUMBER = new ERXKey<String>("jobNumber");
  // Relationship Keys
  public static final ERXKey<com.eltekfw.model.SavedDoc> SAVED_DOCS = new ERXKey<com.eltekfw.model.SavedDoc>("savedDocs");
  public static final ERXKey<com.eltekfw.model.Vendor> VENDOR = new ERXKey<com.eltekfw.model.Vendor>("vendor");

  // Attributes
  public static final String CURRENT_KEY = CURRENT.key();
  public static final String JOB_NUMBER_KEY = JOB_NUMBER.key();
  // Relationships
  public static final String SAVED_DOCS_KEY = SAVED_DOCS.key();
  public static final String VENDOR_KEY = VENDOR.key();

	private static final Logger LOG = LoggerFactory.getLogger(_Invoice.class);
	
  public com.eltekfw.model.Invoice localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.Invoice localInstance = (com.eltekfw.model.Invoice)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean current() {
    return (Boolean) storedValueForKey(_Invoice.CURRENT_KEY);
  }

  public void setCurrent(Boolean value) {
	_Invoice.LOG.debug( "updating current from {} to {}", current(), value);
	takeStoredValueForKey(value, _Invoice.CURRENT_KEY);
  }

  public String jobNumber() {
    return (String) storedValueForKey(_Invoice.JOB_NUMBER_KEY);
  }

  public void setJobNumber(String value) {
	_Invoice.LOG.debug( "updating jobNumber from {} to {}", jobNumber(), value);
	takeStoredValueForKey(value, _Invoice.JOB_NUMBER_KEY);
  }

  public com.eltekfw.model.Vendor vendor() {
    return (com.eltekfw.model.Vendor)storedValueForKey(_Invoice.VENDOR_KEY);
  }
  
  public void setVendor(com.eltekfw.model.Vendor value) {
    takeStoredValueForKey(value, _Invoice.VENDOR_KEY);
  }

  public void setVendorRelationship(com.eltekfw.model.Vendor value) {

	  _Invoice.LOG.debug("updating vendor from {} to {}", vendor(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setVendor(value);
    }
    else if (value == null) {
    	com.eltekfw.model.Vendor oldValue = vendor();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Invoice.VENDOR_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Invoice.VENDOR_KEY);
    }
  }
  
  public NSArray<com.eltekfw.model.SavedDoc> savedDocs() {
    return (NSArray<com.eltekfw.model.SavedDoc>)storedValueForKey(_Invoice.SAVED_DOCS_KEY);
  }

  public NSArray<com.eltekfw.model.SavedDoc> savedDocs(EOQualifier qualifier) {
    return savedDocs(qualifier, null, false);
  }

  public NSArray<com.eltekfw.model.SavedDoc> savedDocs(EOQualifier qualifier, boolean fetch) {
    return savedDocs(qualifier, null, fetch);
  }

  public NSArray<com.eltekfw.model.SavedDoc> savedDocs(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<com.eltekfw.model.SavedDoc> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(com.eltekfw.model.SavedDoc.INVOICE_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = com.eltekfw.model.SavedDoc.fetchSavedDocs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = savedDocs();
      if (qualifier != null) {
        results = (NSArray<com.eltekfw.model.SavedDoc>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<com.eltekfw.model.SavedDoc>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToSavedDocs(com.eltekfw.model.SavedDoc object) {
    includeObjectIntoPropertyWithKey(object, _Invoice.SAVED_DOCS_KEY);
  }

  public void removeFromSavedDocs(com.eltekfw.model.SavedDoc object) {
    excludeObjectFromPropertyWithKey(object, _Invoice.SAVED_DOCS_KEY);
  }

  public void addToSavedDocsRelationship(com.eltekfw.model.SavedDoc object) {
    
    _Invoice.LOG.debug("adding {} to savedDocs relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToSavedDocs(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Invoice.SAVED_DOCS_KEY);
    }
  }

  public void removeFromSavedDocsRelationship(com.eltekfw.model.SavedDoc object) {
 
      _Invoice.LOG.debug("removing {} to savedDocs relationship", object);
    
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromSavedDocs(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Invoice.SAVED_DOCS_KEY);
    }
  }

  public com.eltekfw.model.SavedDoc createSavedDocsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( com.eltekfw.model.SavedDoc.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Invoice.SAVED_DOCS_KEY);
    return (com.eltekfw.model.SavedDoc) eo;
  }

  public void deleteSavedDocsRelationship(com.eltekfw.model.SavedDoc object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Invoice.SAVED_DOCS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllSavedDocsRelationships() {
    Enumeration<com.eltekfw.model.SavedDoc> objects = savedDocs().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteSavedDocsRelationship(objects.nextElement());
    }
  }


  public static com.eltekfw.model.Invoice createInvoice(EOEditingContext editingContext, Boolean current
, String jobNumber
, com.eltekfw.model.Vendor vendor) {
    com.eltekfw.model.Invoice eo = (com.eltekfw.model.Invoice) EOUtilities.createAndInsertInstance(editingContext, _Invoice.ENTITY_NAME);    
		eo.setCurrent(current);
		eo.setJobNumber(jobNumber);
    eo.setVendorRelationship(vendor);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.Invoice> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.Invoice>(_Invoice.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.Invoice> fetchAllInvoices(EOEditingContext editingContext) {
    return _Invoice.fetchAllInvoices(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.Invoice> fetchAllInvoices(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Invoice.fetchInvoices(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.Invoice> fetchInvoices(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.Invoice> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.Invoice>(_Invoice.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.Invoice> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.Invoice fetchInvoice(EOEditingContext editingContext, String keyName, Object value) {
    return _Invoice.fetchInvoice(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Invoice fetchInvoice(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.Invoice> eoObjects = _Invoice.fetchInvoices(editingContext, qualifier, null);
    com.eltekfw.model.Invoice eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Invoice that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Invoice fetchRequiredInvoice(EOEditingContext editingContext, String keyName, Object value) {
    return _Invoice.fetchRequiredInvoice(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.Invoice fetchRequiredInvoice(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.Invoice eoObject = _Invoice.fetchInvoice(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Invoice that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.Invoice localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.Invoice eo) {
    com.eltekfw.model.Invoice localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
