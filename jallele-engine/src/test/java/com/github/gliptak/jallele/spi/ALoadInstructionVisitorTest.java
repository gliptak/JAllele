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
public class ALoadInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchALoad() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=Opcodes.ALOAD;
		vs.setOpCode(opCode);
		vs.setOperand(1); // variable index
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ACONST_NULL));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchALoad0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x2a; // aload_0
		vs.setOpCode(opCode);
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ACONST_NULL));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchALoad1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x2b; // aload_1
		vs.setOpCode(opCode);
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ACONST_NULL));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchALoad2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x2c; // aload_2
		vs.setOpCode(opCode);
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ACONST_NULL));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ALoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchALoad3() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x2d; // aload_3
		vs.setOpCode(opCode);
		Random random = new Random();
		ALoadInstructionVisitor v=new ALoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ACONST_NULL));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
	}
}