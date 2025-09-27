package com.github.gliptak.jallele.testLong;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class LongOpInstructionVisitorTest {

	@Test
	public void testRunLongOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testLong.LongOperationsTest",
				"com.github.gliptak.jallele.testLong.LongOperations",
				10
				);
	}
	
}