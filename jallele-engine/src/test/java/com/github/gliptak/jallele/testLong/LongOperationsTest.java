package com.github.gliptak.jallele.testLong;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongOperationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddTwo() {
		LongOperations lo = new LongOperations();
		long result = lo.addTwo(5L);
		assertThat(result, Is.is(7L));
	}

	@Test
	public final void testMultiplyBy() {
		LongOperations lo = new LongOperations();
		long result = lo.multiplyBy(4L, 2L);
		assertThat(result, Is.is(8L));
	}
	
	@Test
	public final void testDivideBy() {
		LongOperations lo = new LongOperations();
		long result = lo.divideBy(8L, 2L);
		assertThat(result, Is.is(4L));
	}
	
	@Test
	public final void testSubtractFrom() {
		LongOperations lo = new LongOperations();
		long result = lo.subtractFrom(10L, 3L);
		assertThat(result, Is.is(7L));
	}
	
	@Test
	public final void testRemainder() {
		LongOperations lo = new LongOperations();
		long result = lo.remainder(7L, 3L);
		assertThat(result, Is.is(1L));
	}

	@Test
	public final void testBitwiseAnd() {
		LongOperations lo = new LongOperations();
		long result = lo.bitwiseAnd(0xFFL, 0x0FL);
		assertThat(result, Is.is(0x0FL));
	}

	@Test
	public final void testBitwiseOr() {
		LongOperations lo = new LongOperations();
		long result = lo.bitwiseOr(0xF0L, 0x0FL);
		assertThat(result, Is.is(0xFFL));
	}

	@Test
	public final void testBitwiseXor() {
		LongOperations lo = new LongOperations();
		long result = lo.bitwiseXor(0xFFL, 0xF0L);
		assertThat(result, Is.is(0x0FL));
	}

	@Test
	public void testDivisionByZero() {
		LongOperations lo = new LongOperations();
		try {
			lo.divideBy(5L, 0L);
			fail("Should have thrown ArithmeticException");
		} catch (ArithmeticException e) {
			// Expected behavior for integer division by zero
		}
	}
}