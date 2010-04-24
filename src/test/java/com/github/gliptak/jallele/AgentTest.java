/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;

/**
 * @author gliptak
 *
 */
public class AgentTest {

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
	 * Test method for {@link com.github.gliptak.jallele.Agent#attach()}.
	 * @throws AttachNotSupportedException 
	 * @throws AgentInitializationException 
	 * @throws AgentLoadException 
	 * @throws CannotCompileException 
	 * @throws NotFoundException 
	 * @throws IOException 
	 */
	@Test
	public void testAttach() throws Exception {
		Agent.attach();
		Agent.attach();
	}

}
