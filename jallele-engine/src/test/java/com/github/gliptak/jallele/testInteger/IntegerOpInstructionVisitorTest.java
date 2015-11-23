package com.github.gliptak.jallele.testInteger;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class IntegerOpInstructionVisitorTest {

	@Test
	public void testRunPlusTwo() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testInteger.PlusMultiplyTest",
				"com.github.gliptak.jallele.testInteger.PlusMultiply",
				10
				);
	}

}
