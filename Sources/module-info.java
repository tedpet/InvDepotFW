module com.eltekfw.InvDepotFW {
	exports com.eltekfw;
	exports com.eltekfw.components;
	exports com.eltekfw.migration;
	exports com.eltekfw.model.eogen;
	exports com.eltekfw.model;

	requires org.slf4j;
	requires org.wocommunity.webobjects.eoaccess;
	requires org.wocommunity.webobjects.eocontrol;
	requires org.wocommunity.webobjects.foundation;
	requires org.wocommunity.webobjects.webobjects;
	requires transitive org.wocommunity.wonder.erextensions;
	requires org.wocommunity.wonder.erattachment;
}
