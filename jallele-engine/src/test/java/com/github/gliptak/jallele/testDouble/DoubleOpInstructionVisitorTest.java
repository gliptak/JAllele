package com.github.gliptak.jallele.testDouble;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class DoubleOpInstructionVisitorTest {

	@Test
	public void testRunPlusTwo() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testDouble.PlusTwoTest",
				"com.github.gliptak.jallele.testDouble.PlusTwo",
				10
				);
	}
	
}
