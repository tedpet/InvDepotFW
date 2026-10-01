// DO NOT EDIT!  Make changes to com.eltekfw.model.NewEntity.java instead.
package com.eltekfw.model.eogen;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;

import er.extensions.eof.*;
import er.extensions.eof.ERXKey.Type;
import er.extensions.foundation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public abstract class _NewEntity extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "NewEntity";

  // Attribute Keys
  public static final ERXKey<Integer> QUANTITY = new ERXKey<Integer>("quantity", Type.Attribute);

  // Relationship Keys

  // Attributes
  public static final String QUANTITY_KEY = QUANTITY.key();

  // Relationships

  private static final Logger log = LoggerFactory.getLogger(_NewEntity.class);

  public com.eltekfw.model.NewEntity localInstanceIn(EOEditingContext editingContext) {
    com.eltekfw.model.NewEntity localInstance = (com.eltekfw.model.NewEntity)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Integer quantity() {
    return (Integer) storedValueForKey(_NewEntity.QUANTITY_KEY);
  }

  public void setQuantity(Integer value) {
    log.debug( "updating quantity from {} to {}", quantity(), value);
    takeStoredValueForKey(value, _NewEntity.QUANTITY_KEY);
  }


  public static com.eltekfw.model.NewEntity createNewEntity(EOEditingContext editingContext, Integer quantity
) {
    com.eltekfw.model.NewEntity eo = (com.eltekfw.model.NewEntity) EOUtilities.createAndInsertInstance(editingContext, _NewEntity.ENTITY_NAME);
    eo.setQuantity(quantity);
    return eo;
  }

  public static ERXFetchSpecification<com.eltekfw.model.NewEntity> fetchSpec() {
    return new ERXFetchSpecification<com.eltekfw.model.NewEntity>(_NewEntity.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<com.eltekfw.model.NewEntity> fetchAllNewEntities(EOEditingContext editingContext) {
    return _NewEntity.fetchAllNewEntities(editingContext, null);
  }

  public static NSArray<com.eltekfw.model.NewEntity> fetchAllNewEntities(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _NewEntity.fetchNewEntities(editingContext, null, sortOrderings);
  }

  public static NSArray<com.eltekfw.model.NewEntity> fetchNewEntities(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<com.eltekfw.model.NewEntity> fetchSpec = new ERXFetchSpecification<com.eltekfw.model.NewEntity>(_NewEntity.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<com.eltekfw.model.NewEntity> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static com.eltekfw.model.NewEntity fetchNewEntity(EOEditingContext editingContext, String keyName, Object value) {
    return _NewEntity.fetchNewEntity(editingContext, ERXQ.equals(keyName, value));
  }

  public static com.eltekfw.model.NewEntity fetchNewEntity(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<com.eltekfw.model.NewEntity> eoObjects = _NewEntity.fetchNewEntities(editingContext, qualifier, null);
    com.eltekfw.model.NewEntity eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one NewEntity that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.NewEntity fetchRequiredNewEntity(EOEditingContext editingContext, String keyName, Object value) {
    return _NewEntity.fetchRequiredNewEntity(editingContext, ERXQ.equals(keyName, value));
  }

  public static com.eltekfw.model.NewEntity fetchRequiredNewEntity(EOEditingContext editingContext, EOQualifier qualifier) {
    com.eltekfw.model.NewEntity eoObject = _NewEntity.fetchNewEntity(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no NewEntity that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static com.eltekfw.model.NewEntity localInstanceIn(EOEditingContext editingContext, com.eltekfw.model.NewEntity eo) {
    com.eltekfw.model.NewEntity localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
