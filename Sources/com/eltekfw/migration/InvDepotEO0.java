package com.eltekfw.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXJDBCUtilities;
import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class InvDepotEO0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable clientTable = database.newTableNamed("client");
		clientTable.newLargeStringColumn("adress_line1", ALLOWS_NULL);
		clientTable.newLargeStringColumn("adress_line2", ALLOWS_NULL);
		clientTable.newLargeStringColumn("client_city", ALLOWS_NULL);
		clientTable.newLargeStringColumn("client_name", ALLOWS_NULL);
		clientTable.newLargeStringColumn("client_post_code", ALLOWS_NULL);
		clientTable.newLargeStringColumn("client_state", ALLOWS_NULL);
		clientTable.newFlagBooleanColumn("current", NOT_NULL);
		clientTable.newIntegerColumn("id", NOT_NULL);
		clientTable.create();
	 	clientTable.setPrimaryKey("id");

		ERXMigrationTable invoiceTable = database.newTableNamed("invoice");
		invoiceTable.newFlagBooleanColumn("current", NOT_NULL);
		invoiceTable.newIntegerColumn("id", NOT_NULL);
		invoiceTable.newLargeStringColumn("job_number", NOT_NULL);
		invoiceTable.newIntegerColumn("vendor_id", NOT_NULL);
		invoiceTable.create();
	 	invoiceTable.setPrimaryKey("id");

		ERXMigrationTable personTable = database.newTableNamed("person");
		personTable.newFlagBooleanColumn("current", NOT_NULL);
		personTable.newLargeStringColumn("first_name", NOT_NULL);
		personTable.newIntegerColumn("id", NOT_NULL);
		personTable.newLargeStringColumn("last_name", ALLOWS_NULL);
		personTable.newLargeStringColumn("login_name", NOT_NULL);
		personTable.newLargeStringColumn("password", NOT_NULL);
		personTable.newIntegerColumn("security_id", NOT_NULL);
		personTable.create();
	 	personTable.setPrimaryKey("id");

		ERXMigrationTable preferenceTable = database.newTableNamed("preference");
		preferenceTable.newIntegerColumn("id", NOT_NULL);
		preferenceTable.newLargeStringColumn("name", NOT_NULL);
		preferenceTable.newIntegerColumn("person_id", ALLOWS_NULL);
		preferenceTable.newLargeStringColumn("value", NOT_NULL);
		preferenceTable.create();
	 	preferenceTable.setPrimaryKey("id");

		ERXMigrationTable savedDocTable = database.newTableNamed("saved_doc");
		savedDocTable.newIntegerColumn("er_attachment_id", NOT_NULL);
		savedDocTable.newIntegerColumn("id", NOT_NULL);
		savedDocTable.newIntegerColumn("invoice_id", NOT_NULL);
		savedDocTable.newLargeStringColumn("some_text", NOT_NULL);
		savedDocTable.create();
	 	savedDocTable.setPrimaryKey("id");

		ERXMigrationTable securityTable = database.newTableNamed("security");
		securityTable.newFlagBooleanColumn("approve_invoice", NOT_NULL);
		securityTable.newFlagBooleanColumn("create_clients", NOT_NULL);
		securityTable.newFlagBooleanColumn("create_person", NOT_NULL);
		securityTable.newFlagBooleanColumn("edit_clients", NOT_NULL);
		securityTable.newIntegerColumn("id", NOT_NULL);
		securityTable.create();
	 	securityTable.setPrimaryKey("id");

		ERXMigrationTable vendorTable = database.newTableNamed("vendor");
		vendorTable.newFlagBooleanColumn("current", NOT_NULL);
		vendorTable.newIntegerColumn("id", NOT_NULL);
		vendorTable.newLargeStringColumn("password", NOT_NULL);
		vendorTable.newIntegerColumn("person_id", NOT_NULL);
		vendorTable.newLargeStringColumn("vendor_name", NOT_NULL);
		vendorTable.create();
	 	vendorTable.setPrimaryKey("id");

		invoiceTable.addForeignKey("vendor_id", "vendor", "id");
		personTable.addForeignKey("security_id", "security", "id");
		preferenceTable.addForeignKey("person_id", "person", "id");
		savedDocTable.addForeignKey("er_attachment_id", "ERAttachment", "id");
		savedDocTable.addForeignKey("invoice_id", "invoice", "id");
		vendorTable.addForeignKey("person_id", "person", "id");

		ERXJDBCUtilities.executeUpdate(database.adaptorChannel(),
			    "CREATE UNIQUE INDEX preference_name_user_uq ON preference (name, person_id)");
			ERXJDBCUtilities.executeUpdate(database.adaptorChannel(),
			    "CREATE UNIQUE INDEX preference_name_global_uq ON preference (name) WHERE person_id IS NULL");
			
		ERXJDBCUtilities.executeUpdateScriptFromResourceNamed(database.adaptorChannel(),
				"Startup-" + ERXJDBCUtilities.databaseProductName(database.adaptorChannel()) + ".sql", "InvDepotEO");

	}
}
