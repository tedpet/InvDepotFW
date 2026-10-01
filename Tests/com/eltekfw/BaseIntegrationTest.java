package com.eltekfw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.postgresql.Driver;
import org.testcontainers.postgresql.PostgreSQLContainer;

import com.webobjects.eoaccess.EOModelGroup;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.migration.ERXMigrator;
import er.jdbcadaptor.postgresql.PostgresqlPlugIn;

public abstract class BaseIntegrationTest {

	public BaseIntegrationTest() {
	}

	public static final String CREATE_DBUPDATER_STMT = "CREATE TABLE _dbupdater (lockowner varchar(100), modelname varchar(100) PRIMARY KEY, updatelock int4 NOT NULL, version int4 NOT NULL);"
			+ "insert into _dbupdater(modelname, version, updatelock, lockowner) values ('InvDepotFW', -1, 0, NULL)";
	
	static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18")
		.withExposedPorts(5432);
	
	static {
		postgres.start();

		try(Connection conn = postgres.createConnection("");
				PreparedStatement stmt = conn.prepareStatement(CREATE_DBUPDATER_STMT);) {
			stmt.execute();
		} catch (SQLException e) {
			// Failed to run _dbupdater setup, just die.
			throw new RuntimeException("Failed to initialize db.", e);
		}

		NSMutableDictionary<String, Object> connectionDictionary = new NSMutableDictionary<>();
		connectionDictionary.setObjectForKey(postgres.getUsername(), "username");
		connectionDictionary.setObjectForKey(postgres.getPassword(), "password");
		connectionDictionary.setObjectForKey(postgres.getJdbcUrl(), "URL");
		connectionDictionary.setObjectForKey(Driver.class.getName(), "driver");
		connectionDictionary.setObjectForKey(PostgresqlPlugIn.class.getName(), "plugin");
		EOModelGroup.defaultGroup().modelNamed("InvDepotFW").setConnectionDictionary(connectionDictionary.immutableClone());

		new ERXMigrator("test-0").migrateToLatest();
	}

}
