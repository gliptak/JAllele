package com.github.gliptak.jallele.testLong;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class LongShiftInstructionVisitorTest {

	@Test
	public void testRunLongShiftOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testLong.LongShiftOperationsTest",
				"com.github.gliptak.jallele.testLong.LongShiftOperations",
				10
				);
	}
	
}