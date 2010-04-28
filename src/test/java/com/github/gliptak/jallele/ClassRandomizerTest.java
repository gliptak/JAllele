/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.IllegalClassFormatException;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.gliptak.jallele.testA.SimpleClass;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizerTest {

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
	 * 
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 * @throws IOException
	 * @throws IllegalClassFormatException
	 */
	@Test
	public final void testTransform() throws NotFoundException,
			IllegalClassFormatException, IOException, CannotCompileException {
		ClassRandomizer cr = new ClassRandomizer();
		cr.setFilter(SimpleClass.class.getName());
		cr.transform(null, SimpleClass.class.getName(), SimpleClass.class,
				null, getClassBytes(SimpleClass.class));
	}

	protected byte[] getClassBytes(Class clazz) throws IOException {
		String name = clazz.getName().replace('.', '/') + ".class";
		InputStream iStream = clazz.getClassLoader().getResourceAsStream(name);
		try {
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (true) {
				int len = iStream.read(buffer);
				if (len < 0) {
					break;
				}
				oStream.write(buffer, 0, len);
			}
			return oStream.toByteArray();
		} finally {
			iStream.close();
		}
	}
}
