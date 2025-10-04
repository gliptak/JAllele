/**
 * Test for InstructionVisitor base class
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

/**
 * @author gliptak
 * Test class for InstructionVisitor base class functionality
 */
public class InstructionVisitorTest {

	// Concrete implementation for testing the abstract base class
	private static class TestInstructionVisitor extends InstructionVisitor {
		public TestInstructionVisitor(Random random) {
			super(random);
		}

		@Override
		public VisitStatus isMatch(VisitStatus vs) {
			return vs;
		}
		
		// Expose protected method for testing
		public String testGetOpcodeName(int opCode) {
			return getOpcodeName(opCode);
		}
	}

	private TestInstructionVisitor visitor;

	@Before
	public void setUp() throws Exception {
		visitor = new TestInstructionVisitor(new Random());
	}

	@After
	public void tearDown() throws Exception {
		visitor = null;
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.InstructionVisitor#getOpcodeName(int)}.
	 */
	@Test
	public final void testGetOpcodeNameValidOpcodes() {
		// Test various valid opcodes
		assertThat(visitor.testGetOpcodeName(Opcodes.NOP), is("NOP"));
		assertThat(visitor.testGetOpcodeName(Opcodes.ACONST_NULL), is("ACONST_NULL"));
		assertThat(visitor.testGetOpcodeName(Opcodes.ICONST_0), is("ICONST_0"));
		assertThat(visitor.testGetOpcodeName(Opcodes.ICONST_1), is("ICONST_1"));
		assertThat(visitor.testGetOpcodeName(Opcodes.FSTORE), is("FSTORE"));
		assertThat(visitor.testGetOpcodeName(Opcodes.ISTORE), is("ISTORE"));
		assertThat(visitor.testGetOpcodeName(Opcodes.POP), is("POP"));
		assertThat(visitor.testGetOpcodeName(Opcodes.POP2), is("POP2"));
		assertThat(visitor.testGetOpcodeName(Opcodes.IADD), is("IADD"));
		assertThat(visitor.testGetOpcodeName(Opcodes.IMUL), is("IMUL"));
		assertThat(visitor.testGetOpcodeName(Opcodes.IFEQ), is("IFEQ"));
		assertThat(visitor.testGetOpcodeName(Opcodes.IFNE), is("IFNE"));
		assertThat(visitor.testGetOpcodeName(Opcodes.RETURN), is("RETURN"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.InstructionVisitor#getOpcodeName(int)}.
	 * Test with negative opcode value
	 */
	@Test
	public final void testGetOpcodeNameNegativeValue() {
		String result = visitor.testGetOpcodeName(-1);
		assertThat(result, startsWith("UNKNOWN("));
		assertThat(result, containsString("-1"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.InstructionVisitor#getOpcodeName(int)}.
	 * Test with out of range opcode value
	 */
	@Test
	public final void testGetOpcodeNameOutOfRange() {
		String result = visitor.testGetOpcodeName(999);
		assertThat(result, startsWith("UNKNOWN("));
		assertThat(result, containsString("999"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.InstructionVisitor#getOpcodeName(int)}.
	 * Test boundary cases
	 */
	@Test
	public final void testGetOpcodeNameBoundary() {
		// Test opcode 0 (valid - should be NOP)
		assertThat(visitor.testGetOpcodeName(0), is("NOP"));
		
		// Test an out-of-range opcode (higher than valid range)
		String result = visitor.testGetOpcodeName(300);
		// Should return UNKNOWN for out-of-range values
		assertThat(result, startsWith("UNKNOWN"));
		assertThat(result, containsString("300"));
	}

	/**
	 * Test that logger is initialized
	 */
	@Test
	public final void testLoggerInitialization() {
		// Logger should be initialized and not null
		// We can't access the logger directly, but we can verify the visitor was constructed
		assertThat(visitor, is(notNullValue()));
	}

	/**
	 * Test that random is initialized
	 */
	@Test
	public final void testRandomInitialization() {
		// Random should be initialized
		assertThat(visitor.random, is(notNullValue()));
	}
}
