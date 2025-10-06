package com.github.gliptak.jallele;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.testng.annotations.*;

/**
 * Comprehensive TestNG test suite for SimpleClass that exercises all instruction visitors.
 * 
 * @author gliptak
 */
public class SimpleClassTestNGTest {

	private SimpleClass sc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeMethod
	public void setUp() throws Exception {
		sc = new SimpleClass();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		sc = null;
	}

	// Integer operation tests
	@Test
	public void testTwoTimes() {
		int result = sc.twoTimes(4);
		assertThat(result, Is.is(8));
	}

	@Test
	public void testPlusTwo() {
		int result = sc.plusTwo(5);
		assertThat(result, Is.is(7));
	}
	
	@Test
	public void testIntOperations() {
		int result = sc.intOperations(5, 3);
		assertThat(result, Is.is(17)); // (5+3)*2+1 = 17
	}
	
	// Long operation tests
	@Test
	public void testLongOperations() {
		long result = sc.longOperations(10L, 5L);
		assertThat(result, Is.is(120L)); // (10+5)<<2 = 60, 60*2 = 120
	}
	
	// Double operation tests
	@Test
	public void testDoubleOperations() {
		double result = sc.doubleOperations(5.0, 3.0);
		assertThat(result, Is.is(-16.0)); // (5.0+3.0)*2.0 = 16.0, -16.0
	}
	
	// Float operation tests
	@Test
	public void testFloatOperations() {
		float result = sc.floatOperations(5.0f, 3.0f);
		assertThat(result, Is.is(-16.0f)); // (5.0+3.0)*2.0 = 16.0, -16.0
	}
	
	// Reference operation tests
	@Test
	public void testReferenceOperationsNonNull() {
		String result = sc.referenceOperations("hello", "world");
		assertThat(result, Is.is("hello"));
	}
	
	@Test
	public void testReferenceOperationsNull() {
		String result = sc.referenceOperations(null, "world");
		assertThat(result, Is.is("world"));
	}
	
	@Test
	public void testReferenceOperationsSameReference() {
		String str = "test";
		String result = sc.referenceOperations(str, str);
		assertThat(result, Is.is("same"));
	}
	
	// Conditional operation tests
	@Test
	public void testConditionalOperationsGreater() {
		int result = sc.conditionalOperations(10, 5);
		assertThat(result, Is.is(11)); // a > b, result = 10, then 10++ = 11
	}
	
	@Test
	public void testConditionalOperationsLess() {
		int result = sc.conditionalOperations(5, 10);
		assertThat(result, Is.is(11)); // a < b, result = 10, then 10++ = 11
	}
	
	@Test
	public void testConditionalOperationsEqual() {
		int result = sc.conditionalOperations(5, 5);
		assertThat(result, Is.is(11)); // a == b, result = 10, then 10++ = 11
	}
	
	// Array operation tests
	@Test
	public void testArrayOperations() {
		int[] input = {1, 2, 3, 4, 5};
		int[] result = sc.arrayOperations(input);
		assertArrayEquals(new int[]{2, 4, 6, 8, 10}, result);
	}
	
	@Test(expectedExceptions=ArithmeticException.class)
	public void testRuntimeException() {
		@SuppressWarnings("unused")
		int i = 5 / 0;
	}
}