package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class FloatOpInstructionVisitorTest {

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
	public final void testIsMatch() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FADD;
		vs.setOpCode(opCode);
		Random random = new Random();
		FloatOpInstructionVisitor v=new FloatOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(),
				anyOf(equalTo(Opcodes.FADD), equalTo(Opcodes.FDIV), 
					  equalTo(Opcodes.FMUL), equalTo(Opcodes.FREM), equalTo(Opcodes.FSUB)));
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}
	
	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FloatOpInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		FloatOpInstructionVisitor v=new FloatOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}
}