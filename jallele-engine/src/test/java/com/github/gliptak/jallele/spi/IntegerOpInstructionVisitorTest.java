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
public class IntegerOpInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.IntegerOpInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		IntegerOpInstructionVisitor v=new IntegerOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.IntegerOpInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchIAdd() {
		VisitStatus vs=new VisitStatus("Class", "method", "()I", 10, 1);
		vs.setOpCode(Opcodes.IADD);
		Random random = new Random();
		IntegerOpInstructionVisitor v=new IntegerOpInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		// Should transform to a different integer operation
		assertThat(vsNew.getOpCode(), not(Opcodes.IADD));
		// Should be one of the valid integer operations
		int[] validOps = {Opcodes.IADD, Opcodes.IAND, Opcodes.IDIV, Opcodes.IMUL, Opcodes.IOR,
				Opcodes.IREM, Opcodes.ISHL, Opcodes.ISHR, Opcodes.ISUB, Opcodes.IUSHR, Opcodes.IXOR};
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
