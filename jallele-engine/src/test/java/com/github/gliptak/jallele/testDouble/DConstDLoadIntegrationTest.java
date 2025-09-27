package com.github.gliptak.jallele.testDouble;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate DConstInstructionVisitor and DLoadInstructionVisitor functionality.
 * This test ensures our new visitors don't break the execution of double operations.
 */
public class DConstDLoadIntegrationTest {

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
	public final void testDConstOperations() {
		DConstDLoadTest test = new DConstDLoadTest();
		
		// Test methods that use dconst_0 and dconst_1
		double result0 = test.getConstantZero();
		assertThat(result0, is(0.0));
		
		double result1 = test.getConstantOne();
		assertThat(result1, is(1.0));
	}
	
	@Test
	public final void testDLoadOperations() {
		DConstDLoadTest test = new DConstDLoadTest();
		
		// Test method that uses dload for parameter
		double result = test.loadDoubleFromParameter(5.5);
		assertThat(result, is(5.5));
		
		// Test method that uses dload for local variable
		double localResult = test.loadDoubleFromLocal();
		assertThat(localResult, is(42.0));
	}
	
	@Test
	public final void testComplexDoubleOperation() {
		DConstDLoadTest test = new DConstDLoadTest();
		
		// Test method that combines dconst and dload operations
		double result = test.complexDoubleOperation();
		assertThat(result, is(1.0)); // 0.0 + 1.0 = 1.0
	}
}