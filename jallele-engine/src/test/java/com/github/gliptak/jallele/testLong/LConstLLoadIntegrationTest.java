package com.github.gliptak.jallele.testLong;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Integration test to demonstrate LConstInstructionVisitor functionality.
 * 
 * NOTE: LLoadInstructionVisitor was DELETED because lconst* mutations are NOT compatible with lload* instructions.
 * 
 * Why lconst* is incompatible with lload*:
 * - lload instructions read long values from local variables and push them onto the operand stack
 * - lconst instructions push constant long values (0L, 1L) onto the operand stack
 * - Replacing lload with lconst violates JVM bytecode verification rules because:
 *   1. It creates stack map frame inconsistencies (the JVM verifier expects specific local variable usage patterns)
 *   2. It changes program semantics by replacing a variable read with a constant, breaking data flow
 *   3. Local variable slots that should be initialized by lload would become uninitialized
 * 
 * This test ensures LConstInstructionVisitor correctly mutates between lconst_0 and lconst_1
 * without attempting to mutate lload instructions.
 */
public class LConstLLoadIntegrationTest {

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
	public final void testLConstOperations() {
		LConstLLoadTest test = new LConstLLoadTest();
		
		// Test methods that use lconst_0 and lconst_1
		long result0 = test.getConstantZero();
		assertThat(result0, is(0L));
		
		long result1 = test.getConstantOne();
		assertThat(result1, is(1L));
	}
	
	@Test
	public final void testLLoadOperations() {
		LConstLLoadTest test = new LConstLLoadTest();
		
		// Test method that uses lload for parameter
		long result = test.loadLongFromParameter(5L);
		assertThat(result, is(5L));
		
		// Test method that uses lload for local variable
		long localResult = test.loadLongFromLocal();
		assertThat(localResult, is(42L));
	}
	
	@Test
	public final void testComplexLongOperation() {
		LConstLLoadTest test = new LConstLLoadTest();
		
		// Test method that combines lconst and lload operations
		long result = test.complexLongOperation();
		assertThat(result, is(1L)); // 0L + 1L = 1L
	}
}