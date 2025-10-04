package com.github.gliptak.jallele.testArrayStore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate ArrayStoreInstructionVisitor functionality.
 * This test ensures our new visitor doesn't break the execution of array store operations.
 */
public class ArrayStoreIntegrationTest {

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
	public final void testReturnIntArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method that uses iastore for array elements
		int[] result = test.returnIntArray();
		assertThat(result.length, is(2));
		assertThat(result[0], is(10));
		assertThat(result[1], is(20));
	}
	
	@Test
	public final void testReturnLongArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method that uses lastore for long array elements
		long[] result = test.returnLongArray();
		assertThat(result.length, is(2));
		assertThat(result[0], is(123L));
		assertThat(result[1], is(456L));
	}
	
	@Test
	public final void testReturnDoubleArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method that uses dastore for double array elements
		double[] result = test.returnDoubleArray();
		assertThat(result.length, is(2));
		assertThat(result[0], is(1.5));
		assertThat(result[1], is(2.5));
	}
	
	@Test
	public final void testReturnStringArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method that uses aastore for reference array elements
		String[] result = test.returnStringArray();
		assertThat(result.length, is(2));
		assertThat(result[0], is("first"));
		assertThat(result[1], is("second"));
	}
	
	@Test
	public final void testStoreIntInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with iastore operations - should execute without exception
		test.storeIntInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreLongInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with lastore operations - should execute without exception
		test.storeLongInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreFloatInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with fastore operations - should execute without exception
		test.storeFloatInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreDoubleInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with dastore operations - should execute without exception
		test.storeDoubleInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreStringInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with aastore operations - should execute without exception
		test.storeStringInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreByteInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with bastore operations - should execute without exception
		test.storeByteInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreCharInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with castore operations - should execute without exception
		test.storeCharInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testStoreShortInArray() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with sastore operations - should execute without exception
		test.storeShortInArray(); // No exception thrown means success
	}
	
	@Test
	public final void testComplexArrayOperations() {
		ArrayStoreTest test = new ArrayStoreTest();
		
		// Test method with multiple array store operations - should execute without exception
		test.complexArrayOperations(); // No exception thrown means success
	}
}