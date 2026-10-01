package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.eocontrol.EOEditingContext;

public class Vendor extends com.eltekfw.model.eogen._Vendor {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(com.eltekfw.model.eogen._Vendor.class);

	
	public void init(EOEditingContext ec) {
		super.init(ec);
		LOG.debug("initializing a Person");
		setCurrent(true);
		
		
	}
	
}
