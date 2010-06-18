/**
 * 
 */
package com.github.gliptak.jallele.testJump;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author gliptak
 *
 */
public class IfNullTest {

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
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfNull#ifNull(java.lang.String)}.
	 */
	@Test
	public final void testIfNull() {
		IfNull in=new IfNull();
		assertThat(in.ifNull(null), Is.is("true"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfNull#ifNull(java.lang.String)}.
	 */
	@Test
	public final void testTestIfNullNeg() {
		IfNull in=new IfNull();
		assertThat(in.ifNull("foo"), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfNull#ifNotNull(java.lang.String)}.
	 */
	@Test
	public final void testIfNotNull() {
		IfNull in=new IfNull();
		assertThat(in.ifNotNull(null), Is.is("false"));
	}

	/**
	 * Test method for {@link com.github.gliptak.jallele.testJump.IfNull#ifNotNull(java.lang.String)}.
	 */
	@Test
	public final void testIfNotNullNeg() {
		IfNull in=new IfNull();
		assertThat(in.ifNotNull("bar"), Is.is("true"));
	}
}
