package com.github.gliptak.jallele.testInsn;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoolClassTest {

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
	public final void testReturnTrue() {
		BoolClass b=new BoolClass();
		assertThat(b.returnTrue(), Is.is(true));
	}

	@Test
	public final void testReturnFalse() {
		BoolClass b=new BoolClass();
		assertThat(b.returnFalse(), Is.is(false));
	}

}
