package com.github.gliptak.jallele.testJump;

import org.junit.Test;
import com.github.gliptak.jallele.Helper;

public class JumpTest {
	
	@Test
	public void testRunIfNull() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testJump.IfNullTest",
				"com.github.gliptak.jallele.testJump.IfNull",
				10
				);
	}

	@Test
	public void testRunIfReferenceCompare() throws Exception {
		Helper.runRandomized(
				"com.github.gliptak.jallele.testJump.IfReferenceCompareTest",
				"com.github.gliptak.jallele.testJump.IfReferenceCompare",
				10
				);
	}

	@Test
	public void testRunIfIntegerCompare() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testJump.IfIntegerCompareTest",
				"com.github.gliptak.jallele.testJump.IfIntegerCompare",
				10
				);
	}

}
