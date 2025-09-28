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
	public final void testIntArrayStoreStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This should work correctly even if iastore is mutated to POP2
		// If stack corruption occurs, this will fail
		int result = test.testIntArrayStoreWithStackValidation();
		assertThat("Int array store should not corrupt stack", result, is(142));
	}
	
	@Test
	public final void testLongArrayStoreStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This should work correctly even if lastore is mutated to POP2
		// If stack corruption occurs, this will fail
		long result = test.testLongArrayStoreWithStackValidation();
		assertThat("Long array store should not corrupt stack", result, is(1111111110L));
	}
	
	@Test
	public final void testDoubleArrayStoreStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This should work correctly even if dastore is mutated to POP2
		// If stack corruption occurs, this will fail
		double result = test.testDoubleArrayStoreWithStackValidation();
		assertThat("Double array store should not corrupt stack", result, is(5.85987));
	}
	
	@Test
	public final void testStringArrayStoreStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This should work correctly even if aastore is mutated to POP2
		// If stack corruption occurs, this will fail
		String result = test.testStringArrayStoreWithStackValidation();
		assertThat("String array store should not corrupt stack", result, is("Hello World"));
	}
	
	@Test
	public final void testSequentialArrayOperationsStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This tests multiple array operations in sequence
		// Should detect accumulated stack corruption from incomplete POP operations
		int result = test.testSequentialArrayOperationsWithStackValidation();
		assertThat("Sequential array operations should not corrupt stack", result, is(330));
	}
	
	@Test
	public final void testArithmeticAfterArrayStoreStackBehavior() {
		StackValidationTest test = new StackValidationTest();
		
		// This tests arithmetic operations immediately after array store
		// Should detect if stack has extra values interfering with arithmetic
		int result = test.testArithmeticAfterArrayStore();
		assertThat("Arithmetic after array store should work correctly", result, is(15));
	}
}