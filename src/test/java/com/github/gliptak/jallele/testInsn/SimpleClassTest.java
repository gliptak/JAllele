package com.github.gliptak.jallele.testInsn;

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
public class SimpleClassTest {

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
	 * Test method for {@link junit_test.SimpleClass#twoTimes(int)}.
	 */
	@Test
	public void testTwoTimes() {
		SimpleClass sc=new SimpleClass();
		int result=sc.twoTimes(4);
		assertThat(result, Is.is(8));
	}

	/**
	 * Test method for {@link junit_test.SimpleClass#twoTimes(int)}.
	 */
	@Test
	public void testPlusTwo() {
		SimpleClass sc=new SimpleClass();
		int result=sc.plusTwo(5);
		assertThat(result, Is.is(7));
	}
	
	@Test(expected=ArithmeticException.class)
	public void testRuntimeException() {
		int i=5/0;
	}
}