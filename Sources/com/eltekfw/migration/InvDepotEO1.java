package com.eltekfw.migration;

import com.eltekfw.utilities.EltekUtilities;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.jdbc.ERXJDBCUtilities;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationDatabase.Migration;

public class InvDepotEO1 extends Migration {

	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// TODO Auto-generated method stub

	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// TODO Auto-generated method stub
// this fixes the password so it is encrypted.... not real secure
		ERXJDBCUtilities.executeUpdate(
                database.adaptorChannel(),
                "UPDATE person set password = '" + EltekUtilities.SHABase64String("3368") + "' where login_name = 'tedpet'");

		ERXJDBCUtilities.executeUpdate(
                database.adaptorChannel(),
                "UPDATE person set password = '" + EltekUtilities.SHABase64String("4004") + "' where login_name = 'bilsim'");

		ERXJDBCUtilities.executeUpdate(
                database.adaptorChannel(),
                "UPDATE person set password = '" + EltekUtilities.SHABase64String("1234") + "' where login_name = 'salann'");

		ERXJDBCUtilities.executeUpdate(
                database.adaptorChannel(),
                "UPDATE vendor set password = '" + EltekUtilities.SHABase64String("1234") + "' where id = '1'");
		
	}

}
