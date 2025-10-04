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
public class IfNullInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IfNullInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IfNullInstructionVisitor v=new IfNullInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfNullInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfNull() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IFNULL);
		Random random = new Random();
		IfNullInstructionVisitor v=new IfNullInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IFNONNULL
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IFNONNULL));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IfNullInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIfNonNull() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		vs.setOpCode(Opcodes.IFNONNULL);
		Random random = new Random();
		IfNullInstructionVisitor v=new IfNullInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to IFNULL
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.IFNULL));
	}
}
