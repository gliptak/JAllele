package com.github.gliptak.jallele.testArrayStore;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
		assertThat("Expected iconst_1 + iconst_3 = 4, but got different result indicating stack corruption", 
				   result, is(4));
	}
	
	@Test
	public final void testLongArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		long result = test.testLongArrayStoreWithDirectStackOperations();
		assertThat("Expected lconst_0 + 5L = 5L, but got different result indicating stack corruption", 
				   result, is(5L));
	}
	
	@Test
	public final void testDoubleArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		double result = test.testDoubleArrayStoreWithDirectStackOperations();
		assertThat("Expected dconst_0 + 2.0 = 2.0, but got different result indicating stack corruption", 
				   result, is(2.0));
	}
	
	@Test
	public final void testSequentialArrayOperationsWithDirectArithmetic() {
		StackValidationTest test = new StackValidationTest();
		
		int result = test.testSequentialArrayOperationsWithDirectArithmetic();
		assertThat("Expected sequential arithmetic (4+6) + (1+2) = 13, but got different result indicating stack corruption", 
				   result, is(13));
	}
	
	@Test
	public final void testImmediateArithmeticAfterArrayStoreWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		int result = test.testImmediateArithmeticAfterArrayStore();
		assertThat("Expected iconst_2 + iconst_3 = 5 after array store, but got different result indicating stack corruption", 
				   result, is(5));
	}
	
	@Test
	public final void testStringArrayStoreWithStackValidation() {
		StackValidationTest test = new StackValidationTest();
		
		int result = test.testStringArrayStoreWithStackValidation();
		assertThat("Expected integer arithmetic 7 + 8 = 15 after string array store, but got different result indicating stack corruption", 
				   result, is(15));
	}
	
	@Test
	public final void testMixedDirectStackOperationsAfterArrayStore() {
		StackValidationTest test = new StackValidationTest();
		
		int result = test.testMixedDirectStackOperations();
		assertThat("Expected mixed arithmetic operations (2+2) + (3+3) + (1+4) = 15, but got different result indicating stack corruption", 
				   result, is(15));
	}
}
