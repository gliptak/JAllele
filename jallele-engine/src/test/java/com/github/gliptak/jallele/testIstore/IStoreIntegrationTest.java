package com.github.gliptak.jallele.testIstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate IStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of integer operations.
 */
public class IStoreIntegrationTest {

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
	public final void testStoreIntToVariable() {
		IStoreTest test = new IStoreTest();
		
		// Test method that uses istore for local variable
		int result = test.storeIntToVariable(42);
		assertThat(result, is(42));
	}
	
	@Test
	public final void testStoreIntMultipleVariables() {
		IStoreTest test = new IStoreTest();
		
		// Test method that uses multiple istore operations
		int result = test.storeIntMultipleVariables(5, 3);
		assertThat(result, is(5 + 3 + 8 + 15)); // 31
	}
	
	@Test
	public final void testStoreIntWithNoReturn() {
		IStoreTest test = new IStoreTest();
		
		// Test method that stores but doesn't return - should not throw exception
		test.storeIntWithNoReturn(100);
		// If mutation to POP works, this should still execute without issues
	}
	
	@Test
	public final void testComplexStoreOperation() {
		IStoreTest test = new IStoreTest();
		
		// Test method with complex store operations
		int result = test.complexStoreOperation();
		assertThat(result, is(60)); // (10 + 20) * 2 = 60
	}
	
	@Test
	public final void testStoreParameterToLocal() {
		IStoreTest test = new IStoreTest();
		
		// Test method that stores parameters to local variables
		int result = test.storeParameterToLocal(15, 25);
		assertThat(result, is(40));
	}
}