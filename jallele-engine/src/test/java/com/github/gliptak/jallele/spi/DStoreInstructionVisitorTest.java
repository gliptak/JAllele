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
public class DStoreInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDStore() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=Opcodes.DSTORE;
		vs.setOpCode(opCode);
		vs.setOperand(1); // variable index
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
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
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDStore0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=0x47; // dstore_0
		vs.setOpCode(opCode);
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
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
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDStore1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=0x48; // dstore_1
		vs.setOpCode(opCode);
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
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
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDStore2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=0x49; // dstore_2
		vs.setOpCode(opCode);
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
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
	 * Test method for {@link com.github.gliptak.jallele.spi.DStoreInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchDStore3() {
		VisitStatus vs=new VisitStatus("Class", "method", "()D", 10, 1);
		int opCode=0x4a; // dstore_3
		vs.setOpCode(opCode);
		Random random = new Random();
		DStoreInstructionVisitor v=new DStoreInstructionVisitor(random);
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