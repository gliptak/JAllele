package com.github.gliptak.jallele.testLLoad;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LLoadTestClassTest {

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
	public final void testGetConstantZero() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.getConstantZero();
		assertThat(result, is(0L));
	}

	@Test
	public final void testGetConstantOne() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.getConstantOne();
		assertThat(result, is(1L));
	}

	@Test
	public final void testLoadLongFromParameter() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.loadLongFromParameter(12345L);
		assertThat(result, is(12345L));
	}

	@Test
	public final void testLoadLongFromLocal() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.loadLongFromLocal();
		assertThat(result, is(42L));
	}

	@Test
	public final void testComplexLongOperation() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.complexLongOperation();
		assertThat(result, is(1L)); // 0L + 1L = 1L
	}

	@Test
	public final void testMultipleParameters() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.multipleParameters(10L, 20L, 30L);
		assertThat(result, is(60L)); // 10L + 20L + 30L = 60L
	}

	@Test
	public final void testLongWithLocalVariables() {
		LLoadTestClass test = new LLoadTestClass();
		long result = test.testLongWithLocalVariables();
		assertThat(result, is(600L)); // 100L + 200L + 300L = 600L
	}
}