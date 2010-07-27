/**
 * 
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

/**
 * @author gliptak
 *
 */
public class IConstInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10);
		vs.setOpCode(Opcodes.RETURN);
		IConstInstructionVisitor v=new IConstInstructionVisitor();
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatch() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10);
		int opCode=Opcodes.ICONST_0;
		vs.setOpCode(opCode);
		IConstInstructionVisitor v=new IConstInstructionVisitor();
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), IsNot.not(vs.getOpCode()));
		assertThat(vsNew.getOpCode(), anyOf(equalTo(Opcodes.ICONST_0), equalTo(Opcodes.ICONST_1),
				equalTo(Opcodes.ICONST_2), equalTo(Opcodes.ICONST_3), equalTo(Opcodes.ICONST_4),
				equalTo(Opcodes.ICONST_5), equalTo(Opcodes.ICONST_M1)));
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IConstInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchBool() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10);
		int opCode=Opcodes.ICONST_1;
		vs.setOpCode(opCode);
		IConstInstructionVisitor v=new IConstInstructionVisitor();
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.ICONST_0));
		vsNew.setOpCode(opCode);
		assertThat(vsNew, equalTo(vs));
	}
}
