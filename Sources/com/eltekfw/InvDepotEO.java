package com.eltekfw;

import er.attachment.model.ERAttachment;
import er.corebusinesslogic.ERCoreBusinessLogic;
import er.extensions.ERXFrameworkPrincipal;

public class InvDepotEO extends ERXFrameworkPrincipal {
	protected static InvDepotEO sharedInstance;
	@SuppressWarnings("unchecked")
	public final static Class<? extends ERXFrameworkPrincipal> REQUIRES[] = new Class[] {
			
	};

	static {
		setUpFrameworkPrincipalClass(InvDepotEO.class);
	}

	public static InvDepotEO sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = sharedInstance(InvDepotEO.class);
		}
		return sharedInstance;
	}

	@Override
	public void finishInitialization() {
		log.debug("InvDepotEO loaded");
	}
}
