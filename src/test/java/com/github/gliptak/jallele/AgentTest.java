/**
 * 
 */
package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	 * @throws Exception 
	 */
	@Test
	public void testAttach() throws Exception {
		Agent.attach();
		Agent.attach();
	}
	
	@Test
	public void testAddRemoveTransformer() throws Exception {
		Agent.attach();
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>());
		Agent.addTransformer(cr, true);
		Agent.removeTransformer(cr);
	}

}
