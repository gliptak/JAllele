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

public class LConstInstructionVisitorTest {

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
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=Opcodes.LCONST_0;
		vs.setOpCode(opCode);
		LConstInstructionVisitor v=new LConstInstructionVisitor();
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(),
				anyOf(equalTo(Opcodes.LCONST_0), equalTo(Opcodes.LCONST_1)));
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}
	
	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		LConstInstructionVisitor v=new LConstInstructionVisitor();
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}
}
