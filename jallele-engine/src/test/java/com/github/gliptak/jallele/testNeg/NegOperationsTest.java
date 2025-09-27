package com.github.gliptak.jallele.testNeg;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NegOperationsTest {

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
	public final void testNegateDouble() {
		NegOperations no = new NegOperations();
		double result = no.negateDouble(5.0);
		assertThat(result, Is.is(-5.0));
	}

	@Test
	public final void testNegateDoubleNegative() {
		NegOperations no = new NegOperations();
		double result = no.negateDouble(-3.0);
		assertThat(result, Is.is(3.0));
	}

	@Test
	public final void testNegateFloat() {
		NegOperations no = new NegOperations();
		float result = no.negateFloat(4.0f);
		assertThat(result, Is.is(-4.0f));
	}
	
	@Test
	public final void testNegateFloatNegative() {
		NegOperations no = new NegOperations();
		float result = no.negateFloat(-2.5f);
		assertThat(result, Is.is(2.5f));
	}
	
	@Test
	public final void testNegateInt() {
		NegOperations no = new NegOperations();
		int result = no.negateInt(10);
		assertThat(result, Is.is(-10));
	}
	
	@Test
	public final void testNegateIntNegative() {
		NegOperations no = new NegOperations();
		int result = no.negateInt(-7);
		assertThat(result, Is.is(7));
	}
	
	@Test
	public final void testNegateLong() {
		NegOperations no = new NegOperations();
		long result = no.negateLong(1000L);
		assertThat(result, Is.is(-1000L));
	}
	
	@Test
	public final void testNegateLongNegative() {
		NegOperations no = new NegOperations();
		long result = no.negateLong(-500L);
		assertThat(result, Is.is(500L));
	}

	@Test
	public final void testAbsoluteDifference() {
		NegOperations no = new NegOperations();
		double result = no.absoluteDifference(10.0, 3.0);
		assertThat(result, Is.is(7.0));
	}
	
	@Test
	public final void testAbsoluteDifferenceNegative() {
		NegOperations no = new NegOperations();
		double result = no.absoluteDifference(3.0, 10.0);
		assertThat(result, Is.is(7.0));
	}

	@Test
	public final void testComputeBalance() {
		NegOperations no = new NegOperations();
		int result = no.computeBalance(100, 30);
		assertThat(result, Is.is(70));
	}

	@Test
	public final void testIsNegative() {
		NegOperations no = new NegOperations();
		boolean result = no.isNegative(-5.0f);
		assertThat(result, Is.is(true));
	}
	
	@Test
	public final void testIsNegativePositive() {
		NegOperations no = new NegOperations();
		boolean result = no.isNegative(5.0f);
		assertThat(result, Is.is(false));
	}
}