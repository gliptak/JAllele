package com.github.gliptak.jallele.testInsn;

import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;

public class LongClassTest {

	@Test
	public final void testReturnZero() {
		LongClass l=new LongClass();
		assertThat(l.returnZero(), Is.is(0L));
	}

	@Test
	public final void testReturnOne() {
		LongClass l=new LongClass();
		assertThat(l.returnOne(), Is.is(1L));
	}

}
