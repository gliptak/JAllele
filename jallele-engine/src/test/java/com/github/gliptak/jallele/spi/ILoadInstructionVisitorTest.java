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
public class ILoadInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.ILoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchILoad1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x1b; // iload_1
		vs.setOpCode(opCode);
		Random random = new Random();
		ILoadInstructionVisitor v=new ILoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should mutate to one of the iconst values
		assertThat(vsNew.getOpCode(), anyOf(
			Is.is(Opcodes.ICONST_0), Is.is(Opcodes.ICONST_1), Is.is(Opcodes.ICONST_2),
			Is.is(Opcodes.ICONST_3), Is.is(Opcodes.ICONST_4), Is.is(Opcodes.ICONST_5), 
			Is.is(Opcodes.ICONST_M1)
		));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ILoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchILoad() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=Opcodes.ILOAD; // iload (0x15)
		vs.setOpCode(opCode);
		Random random = new Random();
		ILoadInstructionVisitor v=new ILoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should mutate to one of the iconst values
		assertThat(vsNew.getOpCode(), anyOf(
			Is.is(Opcodes.ICONST_0), Is.is(Opcodes.ICONST_1), Is.is(Opcodes.ICONST_2),
			Is.is(Opcodes.ICONST_3), Is.is(Opcodes.ICONST_4), Is.is(Opcodes.ICONST_5), 
			Is.is(Opcodes.ICONST_M1)
		));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ILoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchILoad0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x1a; // iload_0
		vs.setOpCode(opCode);
		Random random = new Random();
		ILoadInstructionVisitor v=new ILoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should mutate to one of the iconst values
		assertThat(vsNew.getOpCode(), anyOf(
			Is.is(Opcodes.ICONST_0), Is.is(Opcodes.ICONST_1), Is.is(Opcodes.ICONST_2),
			Is.is(Opcodes.ICONST_3), Is.is(Opcodes.ICONST_4), Is.is(Opcodes.ICONST_5), 
			Is.is(Opcodes.ICONST_M1)
		));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ILoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		ILoadInstructionVisitor v=new ILoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}
}