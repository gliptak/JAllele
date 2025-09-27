package com.github.gliptak.jallele.testIf;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IfOperationsTest {

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
	public final void testIsZero() {
		IfOperations io = new IfOperations();
		boolean result = io.isZero(0);
		assertThat(result, Is.is(true));
		result = io.isZero(5);
		assertThat(result, Is.is(false));
	}

	@Test
	public final void testIsNotZero() {
		IfOperations io = new IfOperations();
		boolean result = io.isNotZero(0);
		assertThat(result, Is.is(false));
		result = io.isNotZero(5);
		assertThat(result, Is.is(true));
	}
	
	@Test
	public final void testIsNegative() {
		IfOperations io = new IfOperations();
		boolean result = io.isNegative(-1);
		assertThat(result, Is.is(true));
		result = io.isNegative(0);
		assertThat(result, Is.is(false));
		result = io.isNegative(1);
		assertThat(result, Is.is(false));
	}
	
	@Test
	public final void testIsPositiveOrZero() {
		IfOperations io = new IfOperations();
		boolean result = io.isPositiveOrZero(-1);
		assertThat(result, Is.is(false));
		result = io.isPositiveOrZero(0);
		assertThat(result, Is.is(true));
		result = io.isPositiveOrZero(1);
		assertThat(result, Is.is(true));
	}
	
	@Test
	public final void testIsPositive() {
		IfOperations io = new IfOperations();
		boolean result = io.isPositive(-1);
		assertThat(result, Is.is(false));
		result = io.isPositive(0);
		assertThat(result, Is.is(false));
		result = io.isPositive(1);
		assertThat(result, Is.is(true));
	}

	@Test
	public final void testIsNegativeOrZero() {
		IfOperations io = new IfOperations();
		boolean result = io.isNegativeOrZero(-1);
		assertThat(result, Is.is(true));
		result = io.isNegativeOrZero(0);
		assertThat(result, Is.is(true));
		result = io.isNegativeOrZero(1);
		assertThat(result, Is.is(false));
	}
}