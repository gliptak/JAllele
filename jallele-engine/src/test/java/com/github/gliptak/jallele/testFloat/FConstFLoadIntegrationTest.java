package com.github.gliptak.jallele.testFloat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate FConstInstructionVisitor and FLoadInstructionVisitor functionality.
 * This test ensures our new visitors don't break the execution of float operations.
 */
public class FConstFLoadIntegrationTest {

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
	public final void testFConstOperations() {
		FConstFLoadTest test = new FConstFLoadTest();
		
		// Test methods that use fconst_0, fconst_1, and fconst_2
		float result0 = test.getConstantZero();
		assertThat(result0, is(0.0f));
		
		float result1 = test.getConstantOne();
		assertThat(result1, is(1.0f));
		
		float result2 = test.getConstantTwo();
		assertThat(result2, is(2.0f));
	}
	
	@Test
	public final void testFLoadOperations() {
		FConstFLoadTest test = new FConstFLoadTest();
		
		// Test method that uses fload for parameter
		float result = test.loadFloatFromParameter(5.5f);
		assertThat(result, is(5.5f));
		
		// Test method that uses fload for local variable
		float localResult = test.loadFloatFromLocal();
		assertThat(localResult, is(42.0f));
	}
	
	@Test
	public final void testComplexFloatOperation() {
		FConstFLoadTest test = new FConstFLoadTest();
		
		// Test method that combines fconst and fload operations
		float result = test.complexFloatOperation();
		assertThat(result, is(3.0f)); // 0.0f + 1.0f + 2.0f = 3.0f
	}
}