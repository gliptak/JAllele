package com.github.gliptak.jallele.testDstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate DStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of double operations.
 */
public class DStoreIntegrationTest {

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
	public final void testStoreDoubleToVariable() {
		DStoreTest test = new DStoreTest();
		
		// Test method that uses dstore for local variable
		double result = test.storeDoubleToVariable(42.5);
		assertThat(result, is(42.5));
	}
	
	@Test
	public final void testStoreDoubleMultipleVariables() {
		DStoreTest test = new DStoreTest();
		
		// Test method with multiple dstore operations
		double result = test.storeDoubleMultipleVariables(10.0, 20.0);
		assertThat(result, is(260.0)); // 10 + 20 + 30 + 200 = 260
	}
	
	@Test
	public final void testStoreDoubleWithNoReturn() {
		DStoreTest test = new DStoreTest();
		
		// Test method that stores but doesn't return the value
		// Should not throw an exception even with mutation
		test.storeDoubleWithNoReturn(99.9);
	}
	
	@Test
	public final void testComplexStoreOperation() {
		DStoreTest test = new DStoreTest();
		
		// Test method with complex store operations
		double result = test.complexStoreOperation();
		assertThat(result, is(60.0)); // (10 + 20) * 2 = 60
	}
	
	@Test
	public final void testStoreParameterToLocal() {
		DStoreTest test = new DStoreTest();
		
		// Test method that stores parameters to local variables
		double result = test.storeParameterToLocal(15.5, 25.5);
		assertThat(result, is(41.0));
	}
}