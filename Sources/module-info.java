module com.eltekfw.InvDepotFW {
	exports com.eltekfw;
	exports com.eltekfw.components;
	exports com.eltekfw.model.eogen;
	exports com.eltekfw.model;
	exports com.eltekfw.migration;

	requires org.slf4j;
	requires org.wocommunity.webobjects.eoaccess;
	requires org.wocommunity.webobjects.eocontrol;
	requires org.wocommunity.webobjects.foundation;
	requires org.wocommunity.webobjects.webobjects;
	requires transitive org.wocommunity.wonder.erextensions;
	requires org.wocommunity.wonder.erattachment;
	requires org.apache.pdfbox;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.scratchpad;
	requires org.wocommunity.wonder.directtoweb;
	requires org.wocommunity.webobjects.directtoweb;
	requires java.desktop;
	requires org.wocommunity.wonder.ercorebusinesslogic;
}
