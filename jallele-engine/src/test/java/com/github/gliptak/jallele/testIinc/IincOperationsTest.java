package com.github.gliptak.jallele.testIinc;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IincOperationsTest {

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
	public final void testIncrementByOne() {
		IincOperations io = new IincOperations();
		int result = io.incrementByOne(5);
		assertThat(result, Is.is(6));
	}

	@Test
	public final void testIncrementByFive() {
		IincOperations io = new IincOperations();
		int result = io.incrementByFive(10);
		assertThat(result, Is.is(15));
	}
	
	@Test
	public final void testDecrementByOne() {
		IincOperations io = new IincOperations();
		int result = io.decrementByOne(8);
		assertThat(result, Is.is(7));
	}
	
	@Test
	public final void testIncrementByTen() {
		IincOperations io = new IincOperations();
		int result = io.incrementByTen(20);
		assertThat(result, Is.is(30));
	}
	
	@Test
	public final void testDecrementByThree() {
		IincOperations io = new IincOperations();
		int result = io.decrementByThree(15);
		assertThat(result, Is.is(12));
	}

	@Test
	public void testWithZeroValue() {
		IincOperations io = new IincOperations();
		int result = io.incrementByOne(0);
		assertThat(result, Is.is(1));
	}
	
	@Test
	public void testWithNegativeValue() {
		IincOperations io = new IincOperations();
		int result = io.decrementByOne(-5);
		assertThat(result, Is.is(-6));
	}
}