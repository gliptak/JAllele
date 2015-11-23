package com.github.gliptak.jallele.testInsn;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class InsnTest {
	
	@Test
	public void testRunPlusTwo() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testInsn.PlusTwoTest",
				"com.github.gliptak.jallele.testInsn.PlusTwo",
				10
				);
	}
	
	@Test
	public void testRunBoolClass() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testInsn.BoolClassTest",
				"com.github.gliptak.jallele.testInsn.BoolClass",
				10
				);
	}
	
	@Test
	public void testRunLongClass() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testInsn.LongClassTest",
				"com.github.gliptak.jallele.testInsn.LongClass",
				10
				);
	}
	
}
