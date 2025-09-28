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
public class IStoreInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIStore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=Opcodes.ISTORE;
		vs.setOpCode(opCode);
		vs.setOperand(1); // variable index
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIStore0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x3b; // istore_0
		vs.setOpCode(opCode);
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIStore1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x3c; // istore_1
		vs.setOpCode(opCode);
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIStore2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x3d; // istore_2
		vs.setOpCode(opCode);
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIStore3() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		int opCode=0x3e; // istore_3
		vs.setOpCode(opCode);
		Random random = new Random();
		IStoreInstructionVisitor v=new IStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}
}