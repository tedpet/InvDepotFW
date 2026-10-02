package com.eltekfw.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eltekfw.model.eogen._Preference;
import com.webobjects.eocontrol.EOEditingContext;


public class Preference extends _Preference {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(Preference.class);

	
	   @Override public void didInsert() { super.didInsert(); PreferenceStore.invalidate(); }
	   @Override public void didUpdate() { super.didUpdate(); PreferenceStore.invalidate(); }
	   @Override public void didDelete(EOEditingContext ec) { super.didDelete(ec); PreferenceStore.invalidate(); }
	
}
