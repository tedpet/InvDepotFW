package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eltekfw.model.Security;
import com.webobjects.eocontrol.EOEditingContext;
import com.eltekfw.model.eogen._Person;

public class Person extends _Person {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(_Person.class);

	public void init(EOEditingContext ec) {
		super.init(ec);
		LOG.debug("initializing a Person");
		setCurrent(true);
					// approveInvoice,    createClients,     createPerson,      editClients
		
		setSecurityRelationship(Security.createSecurity(ec, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));
		
	}
	
	
}
