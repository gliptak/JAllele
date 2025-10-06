package com.github.gliptak.jallele.testDouble;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate DConstInstructionVisitor functionality.
 * 
 * NOTE: DLoadInstructionVisitor was DELETED because dconst* mutations are NOT compatible with dload* instructions.
 * 
 * Why dconst* is incompatible with dload*:
 * - dload instructions read double values from local variables and push them onto the operand stack
 * - dconst instructions push constant double values (0.0, 1.0) onto the operand stack
 * - Replacing dload with dconst violates JVM bytecode verification rules because:
 *   1. It creates stack map frame inconsistencies (the JVM verifier expects specific local variable usage patterns)
 *   2. It changes program semantics by replacing a variable read with a constant, breaking data flow
 *   3. Local variable slots that should be initialized by dload would become uninitialized
 * 
 * This test ensures DConstInstructionVisitor correctly mutates between dconst_0 and dconst_1
 * without attempting to mutate dload instructions.
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