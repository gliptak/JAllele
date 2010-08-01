/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import java.io.IOException;
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
}
