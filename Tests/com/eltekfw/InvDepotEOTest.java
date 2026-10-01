/**
 *
 */
package com.eltekfw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import com.eltekfw.model.NewEntity;

/**
 *
 */
class InvDepotEOTest extends BaseIntegrationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.eltekfw.InvDepotEO#sharedInstance()}.
	 */
	@Test
	void testSharedInstance() {
		final InvDepotEO framework = InvDepotEO.sharedInstance();
		Assertions.assertNotNull(framework);
	}

	@Test
	void testNewEntity() {
		EOEditingContext ec = ERXEC.newEditingContext();
		NewEntity e1 = NewEntity.createNewEntity(ec, 1);
		NewEntity e2 = NewEntity.createNewEntity(ec, 2);
		NewEntity e3 = NewEntity.createNewEntity(ec, 3);
		ec.saveChanges();
		NSArray<NewEntity> all = NewEntity.fetchAllNewEntities(ec);
		assertEquals(3, all.count());
		NSArray<NewEntity> gt2 = NewEntity.fetchNewEntities(ec, NewEntity.QUANTITY.gt(2), null);
		assertEquals(1, gt2.count());
		assertEquals(e3, gt2.lastObject());
	}
}
