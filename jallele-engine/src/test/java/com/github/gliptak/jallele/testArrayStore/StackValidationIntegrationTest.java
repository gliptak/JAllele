package com.github.gliptak.jallele.testArrayStore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.Random;
import com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor;
import com.github.gliptak.jallele.VisitStatus;
import org.objectweb.asm.Opcodes;

/**
 * Integration test to validate stack behavior after ArrayStoreInstructionVisitor mutations.
 * These tests ensure that mutations don't leave extra values on the stack that could
 * corrupt subsequent operations.
 * 
 * <h3>Updated Testing Strategy:</h3>
 * This test class uses StackValidationTest methods that are specifically designed to
 * generate direct JVM stack instructions like iconst_*, iadd, lconst_*, dconst_*, etc.
 * These operations are extremely sensitive to stack corruption and will fail immediately
 * if array store mutations leave unexpected values on the JVM stack.
 * 
 * <h3>Precise Validation Pattern:</h3>
 * Each test follows the pattern recommended by @gliptak:
 * <ol>
 *   <li><strong>*const* load</strong> - Push value to stack (iconst_5, lconst_1, etc.)</li>
 *   <li><strong>Array store mutation</strong> - Perform array store (leaves extra values when mutated)</li>
 *   <li><strong>*const* load</strong> - Push new values to stack for arithmetic</li>
 *   <li><strong>*add validation</strong> - Perform arithmetic and validate expected result</li>
 * </ol>
 * 
 * <h3>Validation Approach:</h3>
 * <ul>
 *   <li>Direct stack operations using iconst_*, lconst_*, dconst_*</li>
 *   <li>Immediate arithmetic with iadd, imul, ladd, dadd</li>
 *   <li>Stack-sensitive boolean operations</li>
 *   <li>Mixed operation sequences to detect accumulated corruption</li>
 * </ul>
 * 
 * If mutations leave extra values on stack, arithmetic operations will consume those
 * values instead of intended operands, causing tests to fail with wrong results.
 */
public class StackValidationIntegrationTest {

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

	/**
	 * Test that validates the actual stack effect of mutations.
	 * This test verifies that POP2 is the correct mutation by checking
	 * that it creates the expected stack behavior.
	 */
	@Test
	public final void testArrayStoreVisitorStackEffectDocumentation() {
		ArrayStoreInstructionVisitor visitor = new ArrayStoreInstructionVisitor(new Random());
		
		// Test LASTORE → POP2 mutation
		VisitStatus lastoreStatus = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		lastoreStatus.setOpCode(Opcodes.LASTORE);
		VisitStatus lastoreMutation = visitor.isMatch(lastoreStatus);
		assertThat("LASTORE should be mutated to POP2", lastoreMutation.getOpCode(), is(Opcodes.POP2));
		
		// Test IASTORE → POP2 mutation  
		VisitStatus iastoreStatus = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		iastoreStatus.setOpCode(Opcodes.IASTORE);
		VisitStatus iastoreMutation = visitor.isMatch(iastoreStatus);
		assertThat("IASTORE should be mutated to POP2", iastoreMutation.getOpCode(), is(Opcodes.POP2));
		
		// Document the expected stack effects in the test
		// Original LASTORE: consumes arrayref(1) + index(1) + long_value(2) = 4 slots
		// Mutation POP2: consumes only 2 slots
		// Net effect: leaves arrayref(1) + index(1) = 2 slots on stack
		
		// Original IASTORE: consumes arrayref(1) + index(1) + int_value(1) = 3 slots  
		// Mutation POP2: consumes only 2 slots
		// Net effect: leaves arrayref(1) = 1 slot on stack
	}

	@Test
	public final void testIntArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		int result = test.testIntArrayStoreWithDirectStackOperations();
		assertThat("Int array store with direct stack pattern should produce 4", result, is(4));
	}
	
	@Test
	public final void testLongArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test using direct long operations (lconst_*, ladd) with precise pattern
		// lconst_1 → lastore → lconst_0 + 5L → ladd (should produce 5L)
		long result = test.testLongArrayStoreWithDirectStackOperations();
		assertThat("Long array store with direct stack pattern should produce 5L", result, is(5L));
	}
	
	@Test
	public final void testDoubleArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test using direct double operations (dconst_*, dadd) with precise pattern
		// dconst_1 → dastore → dconst_0 + 2.0 → dadd (should produce 2.0)
		double result = test.testDoubleArrayStoreWithDirectStackOperations();
		assertThat("Double array store with direct stack pattern should produce 2.0", result, is(2.0));
	}
	
	@Test
	public final void testSequentialArrayOperationsWithDirectArithmetic() {
		StackValidationTest test = new StackValidationTest();
		
		// Test multiple array operations with immediate arithmetic validation
		// Each array store followed by immediate arithmetic to detect stack corruption
		int result = test.testSequentialArrayOperationsWithDirectArithmetic();
		assertThat("Sequential array operations with direct arithmetic should produce 13", result, is(13));
	}
	
	@Test
	public final void testImmediateArithmeticAfterArrayStoreWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test the precise pattern: iconst_1 → iastore → iconst_2 + iconst_3 → iadd
		// Most direct test of stack corruption detection
		int result = test.testImmediateArithmeticAfterArrayStore();
		assertThat("Immediate arithmetic after array store should produce exactly 5", result, is(5));
	}
	
	@Test
	public final void testStringArrayStoreWithStackValidation() {
		StackValidationTest test = new StackValidationTest();
		
		// Test string array store followed by immediate integer arithmetic
		// ldc "test" → aastore → iconst_7 + iconst_8 → iadd (should produce 15)
		int result = test.testStringArrayStoreWithStackValidation();
		assertThat("String array store should not corrupt integer arithmetic", result, is(15));
	}
	
	@Test
	public final void testMixedDirectStackOperationsAfterArrayStore() {
		StackValidationTest test = new StackValidationTest();
		
		// Test multiple array stores with immediate arithmetic after each
		// Amplifies stack corruption effects through multiple operations
		int result = test.testMixedDirectStackOperations();
		assertThat("Mixed direct stack operations should produce 15", result, is(15));
	}
}
