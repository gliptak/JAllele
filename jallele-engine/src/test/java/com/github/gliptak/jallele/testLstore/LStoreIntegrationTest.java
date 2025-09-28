package com.github.gliptak.jallele.testLstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate LStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of long operations.
 */
public class LStoreIntegrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testStoreLongToVariable() {
		LStoreTest test = new LStoreTest();
		
		// Test method that uses lstore for local variable
		long result = test.storeLongToVariable(42L);
		assertThat(result, is(42L));
	}
	
	@Test
	public final void testStoreLongMultipleVariables() {
		LStoreTest test = new LStoreTest();
		
		// Test method with multiple lstore operations
		long result = test.storeLongMultipleVariables(10L, 20L);
		assertThat(result, is(260L)); // 10 + 20 + 30 + 200 = 260
	}
	
	@Test
	public final void testStoreLongWithNoReturn() {
		LStoreTest test = new LStoreTest();
		
		// Test method that stores but doesn't return the value
		// Should not throw an exception even with mutation
		test.storeLongWithNoReturn(99L);
	}
	
	@Test
	public final void testComplexStoreOperation() {
		LStoreTest test = new LStoreTest();
		
		// Test method with complex store operations
		long result = test.complexStoreOperation();
		assertThat(result, is(60L)); // (10 + 20) * 2 = 60
	}
	
	@Test
	public final void testStoreParameterToLocal() {
		LStoreTest test = new LStoreTest();
		
		// Test method that stores parameters to local variables
		long result = test.storeParameterToLocal(15L, 25L);
		assertThat(result, is(40L));
	}
}