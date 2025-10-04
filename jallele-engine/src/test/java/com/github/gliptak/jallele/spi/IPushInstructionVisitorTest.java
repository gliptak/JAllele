/**
 * 
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

/**
 * @author gliptak
 *
 */
public class IPushInstructionVisitorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IPushInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IPushInstructionVisitor v=new IPushInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IPushInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchBIPush() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.BIPUSH);
		vs.setOperand(10);
		Random random = new Random();
		IPushInstructionVisitor v=new IPushInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Verify opcode stayed the same
		assertThat(vsNew.getOpCode(), is(Opcodes.BIPUSH));
		// Verify operand changed (could be same by chance, but verify object is different)
		assertThat(vsNew, not(sameInstance(vs)));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IPushInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchSIPush() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.SIPUSH);
		vs.setOperand(1000);
		Random random = new Random();
		IPushInstructionVisitor v=new IPushInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Verify opcode stayed the same
		assertThat(vsNew.getOpCode(), is(Opcodes.SIPUSH));
		// Verify we got a new object
		assertThat(vsNew, not(sameInstance(vs)));
	}
}
