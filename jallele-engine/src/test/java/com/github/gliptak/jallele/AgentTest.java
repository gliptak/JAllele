/**
 * 
 */
package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.List;

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
	public void testInstAttach() throws Exception {
		Agent.attach();
		Agent.attach();
	}
	
	@Test
	public void testInstAddRemoveTransformer() throws Exception {
		List<String> tests=new ArrayList<String>();
		TestRunner runner=new EngineJUnit4Runner(tests);
		Agent.attach();
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>(), runner);
		Agent.addTransformer(cr, true);
		Agent.removeTransformer(cr);
	}

}
