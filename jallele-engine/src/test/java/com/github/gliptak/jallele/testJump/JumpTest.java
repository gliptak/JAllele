package com.github.gliptak.jallele.testJump;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;

import com.github.gliptak.jallele.Agent;
import com.github.gliptak.jallele.ClassRandomizer;
import com.github.gliptak.jallele.EngineJUnit4Runner;
import com.github.gliptak.jallele.TestRunner;


public class JumpTest {
	
	@Test
	public void testRunIfNull() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testJump.IfNullTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testJump.IfNull");
		TestRunner runner=new EngineJUnit4Runner(Arrays.asList(tests));
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources, runner);
			cr.recordMatches();
			cr.randomizeRun();
			assertThat(runner.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(IfNull.class);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}

}
