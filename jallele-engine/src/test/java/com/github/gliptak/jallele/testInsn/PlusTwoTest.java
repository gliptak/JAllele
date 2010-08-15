package com.github.gliptak.jallele.testInsn;

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
		int result=sc.twoTimes(4);
		assertThat(result, Is.is(8));
	}

	@Test
	public final void testPlusTwo() {
		PlusTwo sc=new PlusTwo();
		int result=sc.plusTwo(5);
		assertThat(result, Is.is(7));
	}
	
	@Test(expected=ArithmeticException.class)
	public void testRuntimeException() {
		@SuppressWarnings("unused")
		int i=5/0;
	}
}
