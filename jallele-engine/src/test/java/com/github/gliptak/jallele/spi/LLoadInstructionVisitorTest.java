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
public class LLoadInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchLLoad() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=Opcodes.LLOAD;
		vs.setOpCode(opCode);
		vs.setOperand(1); // variable index
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.LCONST_0), Is.is(Opcodes.LCONST_1)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchLLoad0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=0x1e; // lload_0
		vs.setOpCode(opCode);
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.LCONST_0), Is.is(Opcodes.LCONST_1)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchLLoad1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=0x1f; // lload_1
		vs.setOpCode(opCode);
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.LCONST_0), Is.is(Opcodes.LCONST_1)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchLLoad2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=0x20; // lload_2
		vs.setOpCode(opCode);
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.LCONST_0), Is.is(Opcodes.LCONST_1)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.LLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchLLoad3() {
		VisitStatus vs=new VisitStatus("Class", "method", "()J", 10, 1);
		int opCode=0x21; // lload_3
		vs.setOpCode(opCode);
		Random random = new Random();
		LLoadInstructionVisitor v=new LLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.LCONST_0), Is.is(Opcodes.LCONST_1)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}
}