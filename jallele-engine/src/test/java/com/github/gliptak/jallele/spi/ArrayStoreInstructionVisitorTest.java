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
public class ArrayStoreInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.RETURN; // not an array store instruction
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(opCode)); // unchanged
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 * 
	 * Tests that LASTORE is correctly mutated to POP2.
	 * Stack effect: LASTORE consumes arrayref(1) + index(1) + long_value(2) = 4 slots
	 *              POP2 consumes 2 slots
	 *              Net: leaves arrayref + index (2 slots) on stack
	 */
	@Test
	public final void testIsMatchLastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.LASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.DASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 * 
	 * Tests that IASTORE is correctly mutated to POP2.
	 * Stack effect: IASTORE consumes arrayref(1) + index(1) + int_value(1) = 3 slots
	 *              POP2 consumes 2 slots  
	 *              Net: leaves arrayref (1 slot) on stack
	 */
	@Test
	public final void testIsMatchIastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.IASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.FASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchAastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.AASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchBasstore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.BASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchCastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.CASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.ArrayStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchSastore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()V", 10, 1);
		int opCode=Opcodes.SASTORE;
		vs.setOpCode(opCode);
		Random random = new Random();
		ArrayStoreInstructionVisitor v=new ArrayStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), Is.is(Opcodes.POP2));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}
}