package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

public class IincInstructionVisitorTest {

	private IincInstructionVisitor visitor;
	private Random random;

	@Before
	public void setUp() {
		random = new Random(12345); // Use fixed seed for reproducible tests
		visitor = new IincInstructionVisitor(random);
	}

	@Test
	public void testIincInstructionOperandChange() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		vs.setOpCode(Opcodes.IINC);
		vs.setOperand(5); // Original increment value
		
		VisitStatus result = visitor.isMatch(vs);
		
		// IINC opcode should remain the same
		assertThat(result.getOpCode(), is(Opcodes.IINC));
		// But operand should be different
		assertThat(result.getOperand(), not(5));
		// Operand should be within byte range
		assertTrue("Operand should be >= -128", result.getOperand() >= -128);
		assertTrue("Operand should be <= 127", result.getOperand() <= 127);
	}

	@Test
	public void testNonMatchingOpcode() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		vs.setOpCode(Opcodes.IADD); // Not handled by this visitor
		
		VisitStatus result = visitor.isMatch(vs);
		
		// Should return unchanged status
		assertThat(result.getOpCode(), is(Opcodes.IADD));
		assertThat(result.getOperand(), is(vs.getOperand()));
	}

	@Test
	public void testMultipleIincOperandChanges() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		vs.setOpCode(Opcodes.IINC);
		vs.setOperand(10);
		
		// Test multiple mutations to ensure randomness
		VisitStatus result1 = visitor.isMatch(vs);
		VisitStatus result2 = visitor.isMatch(vs);
		
		// Both should be different from original
		assertThat(result1.getOperand(), not(10));
		assertThat(result2.getOperand(), not(10));
		
		// Both should be in valid range
		assertTrue("Result1 operand should be >= -128", result1.getOperand() >= -128);
		assertTrue("Result1 operand should be <= 127", result1.getOperand() <= 127);
		assertTrue("Result2 operand should be >= -128", result2.getOperand() >= -128);
		assertTrue("Result2 operand should be <= 127", result2.getOperand() <= 127);
	}

	@Test
	public void testIincWithZeroOperand() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()V", 1, 1);
		vs.setOpCode(Opcodes.IINC);
		vs.setOperand(0); // Original increment value of 0
		
		VisitStatus result = visitor.isMatch(vs);
		
		// IINC opcode should remain the same
		assertThat(result.getOpCode(), is(Opcodes.IINC));
		// But operand should be different from 0
		assertThat(result.getOperand(), not(0));
		// Operand should be within byte range
		assertTrue("Operand should be >= -128", result.getOperand() >= -128);
		assertTrue("Operand should be <= 127", result.getOperand() <= 127);
	}
}