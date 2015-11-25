/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizerTest {

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
		List<String> tests=new ArrayList<String>();
		TestRunner runner=new JUnit4Runner(tests);
		ClassRandomizer cr = new ClassRandomizer(sources, runner);
		byte[] out=cr.transform(null, SimpleClass.class.getName(), SimpleClass.class, null, in);
		assertThat(out, IsNot.not(in));
	}
	
	@Test
	public void testSimpleNoMatch() throws Exception {
		String[] tests={"com.github.gliptak.jallele.SimpleClassJUnitTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.SimpleClass");
		TestRunner runner=new JUnit4Runner(Arrays.asList(tests));
		Agent.attach();
		ClassRandomizer cr=new ClassRandomizer(sources, runner);
    	cr.randomizeRun();
		assertThat(runner.getFailureCount(), Is.is(0));
		Agent.removeTransformer(cr);
	}
}
