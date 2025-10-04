/**
 * 
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
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
public class IfACompareInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IfACompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IfACompareInstructionVisitor v=new IfACompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfACompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfACmpEq() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ACMPEQ);
		Random random = new Random();
		IfACompareInstructionVisitor v=new IfACompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ACMPNE
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ACMPNE));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfACompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfACmpNe() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ACMPNE);
		Random random = new Random();
		IfACompareInstructionVisitor v=new IfACompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ACMPEQ
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ACMPEQ));
	}
}
