package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.testng.annotations.*;

/**
 * @author gliptak
 *
 */
public class SimpleClassTestNGTest {

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
	@BeforeMethod
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterMethod
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
	
	@Test(expectedExceptions=ArithmeticException.class)
	public void testRuntimeException() {
		@SuppressWarnings("unused")
		int i=5/0;
	}
}