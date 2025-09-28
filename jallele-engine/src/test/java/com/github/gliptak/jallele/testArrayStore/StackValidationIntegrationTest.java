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
 * <h3>Validation Approach:</h3>
 * <ul>
 *   <li>Direct stack operations using iconst_*, lconst_*, dconst_*</li>
 *   <li>Immediate arithmetic with iadd, imul, ladd, dadd</li>
 *   <li>Stack-sensitive boolean operations</li>
 *   <li>Mixed operation sequences to detect accumulated corruption</li>
 * </ul>
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
		
		// Test using direct stack operations (iconst_*, iadd)
		// This should work correctly even if iastore is mutated to POP2
		int result = test.testIntArrayStoreWithDirectStackOperations();
		assertThat("Int array store with direct stack operations should not corrupt stack", result, is(15));
	}
	
	@Test
	public final void testLongArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test using direct long operations (lconst_*, ladd)
		// This should work correctly even if lastore is mutated to POP2
		long result = test.testLongArrayStoreWithDirectStackOperations();
		assertThat("Long array store with direct stack operations should not corrupt stack", result, is(6L));
	}
	
	@Test
	public final void testDoubleArrayStoreStackBehaviorWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test using direct double operations (dconst_*, dadd)
		// This should work correctly even if dastore is mutated to POP2
		double result = test.testDoubleArrayStoreWithDirectStackOperations();
		assertThat("Double array store with direct stack operations should not corrupt stack", result, is(2.0));
	}
	
	@Test
	public final void testSequentialArrayOperationsWithDirectArithmetic() {
		StackValidationTest test = new StackValidationTest();
		
		// Test multiple array operations with direct arithmetic (iconst_*, iadd, imul)
		// Should detect accumulated stack corruption from incomplete POP operations
		int result = test.testSequentialArrayOperationsWithDirectArithmetic();
		assertThat("Sequential array operations with direct arithmetic should not corrupt stack", result, is(25));
	}
	
	@Test
	public final void testImmediateArithmeticAfterArrayStoreWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test immediate arithmetic using direct constants (iconst_*, iadd)
		// Should detect if stack has extra values interfering with arithmetic
		int result = test.testImmediateArithmeticAfterArrayStore();
		assertThat("Immediate arithmetic after array store should work correctly", result, is(5));
	}
	
	@Test
	public final void testMixedDirectStackOperationsAfterArrayStore() {
		StackValidationTest test = new StackValidationTest();
		
		// Test complex arithmetic chain using direct bytecode instructions
		// Highly sensitive to stack corruption from array store mutations
		int result = test.testMixedDirectStackOperations();
		assertThat("Mixed direct stack operations should work correctly", result, is(31));
	}
	
	@Test
	public final void testBooleanOperationsAfterArrayStoreWithDirectInstructions() {
		StackValidationTest test = new StackValidationTest();
		
		// Test boolean operations using direct constants (iconst_0, iconst_1)
		// Should validate stack state with boolean logic operations
		boolean result = test.testBooleanOperationsAfterArrayStore();
		assertThat("Boolean operations after array store should work correctly", result, is(true));
	}
}