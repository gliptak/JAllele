package com.github.gliptak.jallele.testNeg;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class NegInstructionVisitorTest {

	@Test
	public void testRunNegOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testNeg.NegOperationsTest",
				"com.github.gliptak.jallele.testNeg.NegOperations",
				10
				);
	}
	
}