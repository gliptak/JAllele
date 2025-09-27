package com.github.gliptak.jallele.testFloat;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FloatOperationsTest {

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
		FloatOperations fo = new FloatOperations();
		float result = fo.addTwo(5.0f);
		assertThat(result, Is.is(7.0f));
	}

	@Test
	public final void testMultiplyBy() {
		FloatOperations fo = new FloatOperations();
		float result = fo.multiplyBy(4.0f, 2.0f);
		assertThat(result, Is.is(8.0f));
	}
	
	@Test
	public final void testDivideBy() {
		FloatOperations fo = new FloatOperations();
		float result = fo.divideBy(8.0f, 2.0f);
		assertThat(result, Is.is(4.0f));
	}
	
	@Test
	public final void testSubtractFrom() {
		FloatOperations fo = new FloatOperations();
		float result = fo.subtractFrom(10.0f, 3.0f);
		assertThat(result, Is.is(7.0f));
	}
	
	@Test
	public final void testRemainder() {
		FloatOperations fo = new FloatOperations();
		float result = fo.remainder(7.0f, 3.0f);
		assertThat(result, Is.is(1.0f));
	}

	@Test
	public void testDivisionByZero() {
		FloatOperations fo = new FloatOperations();
		float result = fo.divideBy(5.0f, 0.0f);
		assertThat(Float.isInfinite(result), Is.is(true));
	}
}