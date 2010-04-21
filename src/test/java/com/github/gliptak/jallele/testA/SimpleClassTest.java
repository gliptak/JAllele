package com.github.gliptak.jallele.testA;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
		System.out.println(result);
	}
	
	/**
	 * Test method for {@link junit_test.SimpleClass#twoTimes(int)}.
	 */
	@Ignore
	@Test
	public void testTwoTimesFail() {
		SimpleClass sc=new SimpleClass();
		int result=sc.twoTimes(4);
		assertThat(7, Is.is(result));
	}
}