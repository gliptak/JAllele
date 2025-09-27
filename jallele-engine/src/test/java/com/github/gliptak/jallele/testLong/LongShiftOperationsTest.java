package com.github.gliptak.jallele.testLong;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongShiftOperationsTest {

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
	public final void testLeftShift() {
		LongShiftOperations lso = new LongShiftOperations();
		long result = lso.leftShift(1L, 3);
		assertThat(result, Is.is(8L));
	}

	@Test
	public final void testRightShift() {
		LongShiftOperations lso = new LongShiftOperations();
		long result = lso.rightShift(16L, 2);
		assertThat(result, Is.is(4L));
	}

	@Test
	public final void testUnsignedRightShift() {
		LongShiftOperations lso = new LongShiftOperations();
		long result = lso.unsignedRightShift(-8L, 1);
		// -8L >>> 1 should give 9223372036854775804L
		assertThat(result, Is.is(9223372036854775804L));
	}
}