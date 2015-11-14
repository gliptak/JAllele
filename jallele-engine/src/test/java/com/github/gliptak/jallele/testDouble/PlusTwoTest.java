package com.github.gliptak.jallele.testDouble;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlusTwoTest {

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
	public final void testTwoTimes() {
		PlusTwo sc=new PlusTwo();
		double result=sc.timesTwo(4.0);
		assertThat(result, Is.is(8.0));
	}

	@Test
	public final void testPlusTwo() {
		PlusTwo sc=new PlusTwo();
		double result=sc.plusTwo(5.0);
		assertThat(result, Is.is(7.0));
	}
	
	@Test
	public void testRuntimeException() {
		assertThat(Double.isInfinite(5.0/0.0), Is.is(true));
	}
}
