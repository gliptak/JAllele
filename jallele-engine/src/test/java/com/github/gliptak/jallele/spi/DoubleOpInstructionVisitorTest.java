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
public class DoubleOpInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.DoubleOpInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		DoubleOpInstructionVisitor v=new DoubleOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.DoubleOpInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDAdd() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		vs.setOpCode(Opcodes.DADD);
		Random random = new Random();
		DoubleOpInstructionVisitor v=new DoubleOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to a different double operation
		assertThat(vsNew.getOpCode(), not(Opcodes.DADD));
		// Should be one of the valid double operations
		int[] validOps = {Opcodes.DADD, Opcodes.DDIV, Opcodes.DMUL, Opcodes.DREM, Opcodes.DSUB};
		boolean isValid = false;
		for (int op : validOps) {
			if (vsNew.getOpCode() == op) {
				isValid = true;
				break;
			}
		}
		assertThat(isValid, is(true));
	}
}
