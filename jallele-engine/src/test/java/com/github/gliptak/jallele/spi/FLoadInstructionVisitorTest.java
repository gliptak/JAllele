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
public class FLoadInstructionVisitorTest {

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
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchNot() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		vs.setOpCode(Opcodes.RETURN);
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew, equalTo(vs));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFLoad() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FLOAD;
		vs.setOpCode(opCode);
		vs.setOperand(1); // variable index
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.FCONST_0), Is.is(Opcodes.FCONST_1), Is.is(Opcodes.FCONST_2), Is.is(Opcodes.POP)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFLoad0() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=0x22; // fload_0
		vs.setOpCode(opCode);
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.FCONST_0), Is.is(Opcodes.FCONST_1), Is.is(Opcodes.FCONST_2), Is.is(Opcodes.POP)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFLoad1() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=0x23; // fload_1
		vs.setOpCode(opCode);
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.FCONST_0), Is.is(Opcodes.FCONST_1), Is.is(Opcodes.FCONST_2), Is.is(Opcodes.POP)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFLoad2() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=0x24; // fload_2
		vs.setOpCode(opCode);
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.FCONST_0), Is.is(Opcodes.FCONST_1), Is.is(Opcodes.FCONST_2), Is.is(Opcodes.POP)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.spi.FLoadInstructionVisitor#isMatch(com.github.gliptak.jallele.VisitStatus)}.
	 */
	@Test
	public final void testIsMatchFLoad3() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=0x25; // fload_3
		vs.setOpCode(opCode);
		Random random = new Random();
		FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
		VisitStatus vsNew=v.isMatch(vs);
		assertThat(vsNew.getOpCode(), anyOf(Is.is(Opcodes.FCONST_0), Is.is(Opcodes.FCONST_1), Is.is(Opcodes.FCONST_2), Is.is(Opcodes.POP)));
		// Verify other properties remain the same
		assertThat(vsNew.getClassName(), Is.is(vs.getClassName()));
		assertThat(vsNew.getMethodName(), Is.is(vs.getMethodName()));
		assertThat(vsNew.getMethodDesc(), Is.is(vs.getMethodDesc()));
		assertThat(vsNew.getCount(), Is.is(vs.getCount()));
		assertThat(vsNew.getLineNumber(), Is.is(vs.getLineNumber()));
	}
	
	/**
	 * Test method to verify POP can be selected as a mutation target.
	 */
	@Test
	public final void testIsMatchFLoadCanMutateToPop() {
		VisitStatus vs=new VisitStatus("Class", "method", "()F", 10, 1);
		int opCode=Opcodes.FLOAD;
		vs.setOpCode(opCode);
		
		// Run many times to check what mutations we get
		boolean foundPop = false;
		boolean foundFConst0 = false;
		boolean foundFConst1 = false; 
		boolean foundFConst2 = false;
		
		for (int i = 0; i < 1000; i++) { // More iterations to find all values
			Random random = new Random(System.nanoTime() + i); // Use different seed strategy
			FLoadInstructionVisitor v=new FLoadInstructionVisitor(random);
			VisitStatus vsNew=v.isMatch(vs);
			if (vsNew.getOpCode() == Opcodes.POP) {
				foundPop = true;
			} else if (vsNew.getOpCode() == Opcodes.FCONST_0) {
				foundFConst0 = true;
			} else if (vsNew.getOpCode() == Opcodes.FCONST_1) {
				foundFConst1 = true;
			} else if (vsNew.getOpCode() == Opcodes.FCONST_2) {
				foundFConst2 = true;
			}
			
			// Stop early if we found all
			if (foundPop && foundFConst0 && foundFConst1 && foundFConst2) {
				break;
			}
		}
		
		// Verify all possible mutations can occur
		assertThat("FCONST_0 should be a possible mutation target", foundFConst0, is(true));
		assertThat("FCONST_1 should be a possible mutation target", foundFConst1, is(true));
		assertThat("FCONST_2 should be a possible mutation target", foundFConst2, is(true));
		assertThat("POP should be a possible mutation target", foundPop, is(true));
	}
}