package com.github.gliptak.jallele.testFstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate FStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of float operations.
 */
public class FStoreIntegrationTest {

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
	public final void testStoreFloatToVariable() {
		FStoreTest test = new FStoreTest();
		
		// Test method that uses fstore for local variable
		float result = test.storeFloatToVariable(42.5f);
		assertThat(result, is(42.5f));
	}
	
	@Test
	public final void testStoreFloatMultipleVariables() {
		FStoreTest test = new FStoreTest();
		
		// Test method with multiple fstore operations
		float result = test.storeFloatMultipleVariables(10.0f, 20.0f);
		assertThat(result, is(260.0f)); // 10 + 20 + 30 + 200 = 260
	}
	
	@Test
	public final void testStoreFloatWithNoReturn() {
		FStoreTest test = new FStoreTest();
		
		// Test method that stores but doesn't return the value
		// Should not throw an exception even with mutation
		test.storeFloatWithNoReturn(99.9f);
	}
	
	@Test
	public final void testComplexStoreOperation() {
		FStoreTest test = new FStoreTest();
		
		// Test method with complex store operations
		float result = test.complexStoreOperation();
		assertThat(result, is(60.0f)); // (10 + 20) * 2 = 60
	}
	
	@Test
	public final void testStoreParameterToLocal() {
		FStoreTest test = new FStoreTest();
		
		// Test method that stores parameters to local variables
		float result = test.storeParameterToLocal(15.5f, 25.5f);
		assertThat(result, is(41.0f));
	}
}