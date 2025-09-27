package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class NegInstructionVisitorTest {

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
	public final void testIsMatchDNeg() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=Opcodes.DNEG;
		vs.setOpCode(opCode);
		Random random = new Random();
		NegInstructionVisitor v=new NegInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), equalTo(Opcodes.NOP));
		// Reset opcode to verify equality in other aspects
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}

	@Test
	public final void testIsMatchFNeg() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FNEG;
		vs.setOpCode(opCode);
		Random random = new Random();
		NegInstructionVisitor v=new NegInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), equalTo(Opcodes.NOP));
		// Reset opcode to verify equality in other aspects
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}

	@Test
	public final void testIsMatchINeg() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=Opcodes.INEG;
		vs.setOpCode(opCode);
		Random random = new Random();
		NegInstructionVisitor v=new NegInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), equalTo(Opcodes.NOP));
		// Reset opcode to verify equality in other aspects
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}

	@Test
	public final void testIsMatchLNeg() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=Opcodes.LNEG;
		vs.setOpCode(opCode);
		Random random = new Random();
		NegInstructionVisitor v=new NegInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), equalTo(Opcodes.NOP));
		// Reset opcode to verify equality in other aspects
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}
	
	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.NegInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		NegInstructionVisitor v=new NegInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}
}