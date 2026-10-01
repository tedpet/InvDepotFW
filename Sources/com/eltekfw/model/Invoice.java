package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eltekfw.model.eogen._Invoice;

import com.webobjects.eocontrol.EOEditingContext;

public class Invoice extends _Invoice {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Invoice.class);

	public void init(EOEditingContext ec) {
		super.init(ec);
		LOG.debug("initializing an Invoice");
		setCurrent(true);
		
	}
}
