package com.github.gliptak.jallele.testInteger;

import com.github.gliptak.jallele.Helper;
import org.junit.Test;

public class IPushInstructionVisitorTest {

	@Test
	public void testRunPlusTwo() throws Exception{
		Helper.runRandomized(
				"com.github.gliptak.jallele.testInteger.IPushTest",
				"com.github.gliptak.jallele.testInteger.IPush",
				10
				);
	}

}
