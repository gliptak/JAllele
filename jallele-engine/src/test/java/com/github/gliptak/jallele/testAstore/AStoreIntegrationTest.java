package com.github.gliptak.jallele.testAstore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate AStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of reference operations.
 */
public class AStoreIntegrationTest {

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
	public final void testStoreStringToVariable() {
		AStoreTest test = new AStoreTest();
		
		// Test method that uses astore for local variable
		String result = test.storeStringToVariable("Hello");
		assertThat(result, is("Hello"));
	}
	
	@Test
	public final void testStoreStringMultipleVariables() {
		AStoreTest test = new AStoreTest();
		
		// Test method with multiple astore operations
		String result = test.storeStringMultipleVariables("A", "B");
		assertThat(result, is("ABABAB!"));
	}
	
	@Test
	public final void testStoreStringWithNoReturn() {
		AStoreTest test = new AStoreTest();
		
		// Test method that stores but doesn't return the value
		// Should not throw an exception even with mutation
		test.storeStringWithNoReturn("Test");
	}
	
	@Test
	public final void testComplexStoreOperation() {
		AStoreTest test = new AStoreTest();
		
		// Test method with complex store operations
		String result = test.complexStoreOperation();
		assertThat(result, is("Hello World!"));
	}
	
	@Test
	public final void testStoreParameterToLocal() {
		AStoreTest test = new AStoreTest();
		
		// Test method that stores parameters to local variables
		String result = test.storeParameterToLocal("Hello", "World");
		assertThat(result, is("HelloWorld"));
	}
	
	@Test
	public final void testStoreArrayToVariable() {
		AStoreTest test = new AStoreTest();
		
		// Test method that stores array reference to local variable
		Object[] input = {"a", "b", "c"};
		Object[] result = test.storeArrayToVariable(input);
		assertThat(result, is(input));
	}
}