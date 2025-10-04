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
public class IfICompareInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpEq() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPEQ);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPNE
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPNE));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpNe() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPNE);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPEQ
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPEQ));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpLe() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPLE);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPGT
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPGT));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpLt() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPLT);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPGE
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPGE));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpGt() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPGT);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPLE
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPLE));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfICompareInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfICmpGe() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IF_ICMPGE);
		Random random = new Random();
		IfICompareInstructionVisitor v=new IfICompareInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IF_ICMPLT
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IF_ICMPLT));
	}
}
