// DO NOT EDIT.  Make changes to com.eltekfw.model.SavedDoc.java instead.
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
public abstract class _SavedDoc extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "SavedDoc";

  // Attribute Keys
  public static final ERXKey<java.math.BigDecimal> GROSS_AMOUNT = new ERXKey<java.math.BigDecimal>("grossAmount");
  public static final ERXKey<Integer> QUANTITY = new ERXKey<Integer>("quantity");
  public static final ERXKey<java.math.BigDecimal> SALES_TAX = new ERXKey<java.math.BigDecimal>("salesTax");
  public static final ERXKey<String> SOME_TEXT = new ERXKey<String>("someText");
  // Relationship Keys
  public static final ERXKey<er.attachment.model.ERAttachment> AN_IMAGE = new ERXKey<er.attachment.model.ERAttachment>("anImage");
  public static final ERXKey<com.eltekfw.model.Invoice> INVOICE = new ERXKey<com.eltekfw.model.Invoice>("invoice");

  // Attributes
  public static final String GROSS_AMOUNT_KEY = GROSS_AMOUNT.key();
  public static final String QUANTITY_KEY = QUANTITY.key();
  public static final String SALES_TAX_KEY = SALES_TAX.key();
  public static final String SOME_TEXT_KEY = SOME_TEXT.key();
  // Relationships
  public static final String AN_IMAGE_KEY = AN_IMAGE.key();
  public static final String INVOICE_KEY = INVOICE.key();

	private static final Logger LOG = LoggerFactory.getLogger(_SavedDoc.class);
	
  public com.eltekfw.model.SavedDoc localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.SavedDoc localInstance = (com.eltekfw.model.SavedDoc)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public java.math.BigDecimal grossAmount() {
    return (java.math.BigDecimal) storedValueForKey(_SavedDoc.GROSS_AMOUNT_KEY);
  }

  public void setGrossAmount(java.math.BigDecimal value) {
	_SavedDoc.LOG.debug( "updating grossAmount from {} to {}", grossAmount(), value);
	takeStoredValueForKey(value, _SavedDoc.GROSS_AMOUNT_KEY);
  }

  public Integer quantity() {
    return (Integer) storedValueForKey(_SavedDoc.QUANTITY_KEY);
  }

  public void setQuantity(Integer value) {
	_SavedDoc.LOG.debug( "updating quantity from {} to {}", quantity(), value);
	takeStoredValueForKey(value, _SavedDoc.QUANTITY_KEY);
  }

  public java.math.BigDecimal salesTax() {
    return (java.math.BigDecimal) storedValueForKey(_SavedDoc.SALES_TAX_KEY);
  }

  public void setSalesTax(java.math.BigDecimal value) {
	_SavedDoc.LOG.debug( "updating salesTax from {} to {}", salesTax(), value);
	takeStoredValueForKey(value, _SavedDoc.SALES_TAX_KEY);
  }

  public String someText() {
    return (String) storedValueForKey(_SavedDoc.SOME_TEXT_KEY);
  }

  public void setSomeText(String value) {
	_SavedDoc.LOG.debug( "updating someText from {} to {}", someText(), value);
	takeStoredValueForKey(value, _SavedDoc.SOME_TEXT_KEY);
  }

  public er.attachment.model.ERAttachment anImage() {
    return (er.attachment.model.ERAttachment)storedValueForKey(_SavedDoc.AN_IMAGE_KEY);
  }
  
  public void setAnImage(er.attachment.model.ERAttachment value) {
    takeStoredValueForKey(value, _SavedDoc.AN_IMAGE_KEY);
  }

  public void setAnImageRelationship(er.attachment.model.ERAttachment value) {

	  _SavedDoc.LOG.debug("updating anImage from {} to {}", anImage(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setAnImage(value);
    }
    else if (value == null) {
    	er.attachment.model.ERAttachment oldValue = anImage();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _SavedDoc.AN_IMAGE_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _SavedDoc.AN_IMAGE_KEY);
    }
  }
  
  public com.eltekfw.model.Invoice invoice() {
    return (com.eltekfw.model.Invoice)storedValueForKey(_SavedDoc.INVOICE_KEY);
  }
  
  public void setInvoice(com.eltekfw.model.Invoice value) {
    takeStoredValueForKey(value, _SavedDoc.INVOICE_KEY);
  }

  public void setInvoiceRelationship(com.eltekfw.model.Invoice value) {

	  _SavedDoc.LOG.debug("updating invoice from {} to {}", invoice(), value);
   
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setInvoice(value);
    }
    else if (value == null) {
    	com.eltekfw.model.Invoice oldValue = invoice();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _SavedDoc.INVOICE_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _SavedDoc.INVOICE_KEY);
    }
  }
  

  public static com.eltekfw.model.SavedDoc createSavedDoc(EOEditingContext editingContext, java.math.BigDecimal grossAmount
, java.math.BigDecimal salesTax
, String someText
, er.attachment.model.ERAttachment anImage, com.eltekfw.model.Invoice invoice) {
    com.eltekfw.model.SavedDoc eo = (com.eltekfw.model.SavedDoc) EOUtilities.createAndInsertInstance(editingContext, _SavedDoc.ENTITY_NAME);    
		eo.setGrossAmount(grossAmount);
		eo.setSalesTax(salesTax);
		eo.setSomeText(someText);
    eo.setAnImageRelationship(anImage);
    eo.setInvoiceRelationship(invoice);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.SavedDoc> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.SavedDoc>(_SavedDoc.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.SavedDoc> fetchAllSavedDocs(EOEditingContext editingContext) {
    return _SavedDoc.fetchAllSavedDocs(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.SavedDoc> fetchAllSavedDocs(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _SavedDoc.fetchSavedDocs(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.SavedDoc> fetchSavedDocs(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.SavedDoc> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.SavedDoc>(_SavedDoc.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<com.eltekfw.model.SavedDoc> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.SavedDoc fetchSavedDoc(EOEditingContext editingContext, String keyName, Object value) {
    return _SavedDoc.fetchSavedDoc(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.SavedDoc fetchSavedDoc(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.SavedDoc> eoObjects = _SavedDoc.fetchSavedDocs(editingContext, qualifier, null);
    com.eltekfw.model.SavedDoc eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one SavedDoc that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.SavedDoc fetchRequiredSavedDoc(EOEditingContext editingContext, String keyName, Object value) {
    return _SavedDoc.fetchRequiredSavedDoc(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static com.eltekfw.model.SavedDoc fetchRequiredSavedDoc(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.SavedDoc eoObject = _SavedDoc.fetchSavedDoc(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no SavedDoc that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.SavedDoc localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.SavedDoc eo) {
    com.eltekfw.model.SavedDoc localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }

}
