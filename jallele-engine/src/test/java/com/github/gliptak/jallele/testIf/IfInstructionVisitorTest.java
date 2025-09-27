package com.github.gliptak.jallele.testIf;

import org.junit.Test;

import com.github.gliptak.jallele.Helper;

public class IfInstructionVisitorTest {

	@Test
	public void testRunIfOperations() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testIf.IfOperationsTest",
				"com.github.gliptak.jallele.testIf.IfOperations",
				10
				);
	}
	
}