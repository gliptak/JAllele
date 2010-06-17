/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.github.gliptak.jallele.testInsn.SimpleClass;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizerTest {

	private static Logger logger = Logger.getLogger(ClassRandomizerTest.class.getName());

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
	 * Test method for
	 * {@link com.github.gliptak.jallele.ClassRandomizer#transform(java.lang.ClassLoader, java.lang.String, java.lang.Class, java.security.ProtectionDomain, byte[])}
	 * .
	 * @throws IOException 
	 * @throws IllegalClassFormatException 
	 * 
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 * @throws IOException
	 * @throws IllegalClassFormatException
	 */
	@Test
	public final void testTransform() throws IllegalClassFormatException, IOException {
		byte[] in=Agent.getClassBytes(SimpleClass.class);
		List<String> sources=new ArrayList<String>();
		sources.add(SimpleClass.class.getName());
		ClassRandomizer cr = new ClassRandomizer(sources);
//		byte[] out=cr.transform(null, SimpleClass.class.getName(), SimpleClass.class, null, in);
//		assertThat(out, IsNot.not(in));
	}
	
	@Test
	public void testSimple() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.SimpleClassTest"};
		MockSystem system=new MockSystem();
		Result result=runSimpleClassTest(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			List<String> sources=new ArrayList<String>();
			sources.add("com.github.gliptak.jallele.testInsn.SimpleClass");
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
	    	result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(SimpleClass.class);
		result=runSimpleClassTest(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
	}
	
	@Test
	public void testSimpleNoMatch() throws Exception{
		MockSystem system=new MockSystem();
		String[] tests={"com.github.gliptak.jallele.testInsn.SimpleClassTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testInsn.SimpleClass");
		Agent.attach();
		ClassRandomizer cr=new ClassRandomizer(sources);
    	Result result=cr.randomizeRun(system, Arrays.asList(tests));
		assertThat(result.getFailureCount(), Is.is(0));
		Agent.removeTransformer(cr);
	}
	
	@Test
	public void runJumpTest() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testJump.IfNullTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testJump.IfNull");
		MockSystem system=new MockSystem();
		for (int i=0;i<10;i++){
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
			Result result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
	}
	
	/**
	 * 
	 */
	protected Result runSimpleClassTest(MockSystem system, String[] tests) {
		Result result= new JUnitCore().runMain(system, tests);
		logger.fine("exit code: "+system.getExitCode());
		logger.fine(result.toString());
		return result;
	}

	public class MockSystem implements JUnitSystem {
		
		protected int exitCode=0;

		public int getExitCode() {
			return exitCode;
		}

		public void exit(int code) {
			exitCode=code;
		}

		public PrintStream out() {
			return System.out;
		}

	}
}
