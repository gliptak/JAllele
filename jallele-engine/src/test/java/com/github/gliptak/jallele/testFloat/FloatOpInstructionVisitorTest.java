package com.github.gliptak.jallele.testFloat;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class FloatOpInstructionVisitorTest {

	@Test
	public void testRunFloatOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testFloat.FloatOperationsTest",
				"com.github.gliptak.jallele.testFloat.FloatOperations",
				10
				);
	}
	
}