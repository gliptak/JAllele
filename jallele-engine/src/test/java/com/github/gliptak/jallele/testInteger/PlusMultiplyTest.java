package com.github.gliptak.jallele.testInteger;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlusMultiplyTest {

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
	public final void testNineteenTimes() {
		PlusMultiply sc=new PlusMultiply();
		int result=sc.timesNineteen(9);
		assertThat(result, Is.is(171));
	}

	@Test
	public final void testPlusSeventeen() {
		PlusMultiply sc=new PlusMultiply();
		int result=sc.plusSeventeen(9);
		assertThat(result, Is.is(26));
	}
	
	@Test(expected=ArithmeticException.class)
	public void testRuntimeException() {
		@SuppressWarnings("unused")
		int _ignore=5/0;
	}
}
