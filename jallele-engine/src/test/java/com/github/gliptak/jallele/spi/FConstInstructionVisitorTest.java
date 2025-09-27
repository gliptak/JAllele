/**
 * 
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNot;
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
public class FConstInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.FConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		FConstInstructionVisitor v=new FConstInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFConst0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FCONST_0;
		vs.setOpCode(opCode);
		Random random = new Random();
		FConstInstructionVisitor v=new FConstInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(), anyOf(equalTo(Opcodes.FCONST_0), equalTo(Opcodes.FCONST_1), equalTo(Opcodes.FCONST_2)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), equalTo(vs.getClassName()));
		assertThat(vsNew.getMethodName(), equalTo(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), equalTo(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), equalTo(vs.getCount()));
		assertThat(vsNew.getLineNumber(), equalTo(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFConst1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FCONST_1;
		vs.setOpCode(opCode);
		Random random = new Random();
		FConstInstructionVisitor v=new FConstInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(), anyOf(equalTo(Opcodes.FCONST_0), equalTo(Opcodes.FCONST_1), equalTo(Opcodes.FCONST_2)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), equalTo(vs.getClassName()));
		assertThat(vsNew.getMethodName(), equalTo(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), equalTo(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), equalTo(vs.getCount()));
		assertThat(vsNew.getLineNumber(), equalTo(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFConst2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FCONST_2;
		vs.setOpCode(opCode);
		Random random = new Random();
		FConstInstructionVisitor v=new FConstInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(), anyOf(equalTo(Opcodes.FCONST_0), equalTo(Opcodes.FCONST_1), equalTo(Opcodes.FCONST_2)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), equalTo(vs.getClassName()));
		assertThat(vsNew.getMethodName(), equalTo(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), equalTo(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), equalTo(vs.getCount()));
		assertThat(vsNew.getLineNumber(), equalTo(vs.getLineNumber()));
	}
}