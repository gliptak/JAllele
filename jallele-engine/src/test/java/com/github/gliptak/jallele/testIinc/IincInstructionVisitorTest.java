package com.github.gliptak.jallele.testIinc;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class IincInstructionVisitorTest {

	@Test
	public void testRunIincOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testIinc.IincOperationsTest",
				"com.github.gliptak.jallele.testIinc.IincOperations",
				10
				);
	}
	
}