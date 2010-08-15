package com.github.gliptak.jallele.testInsn;

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

public class InsnTest {
	
	@Test
	public void testRunPlusTwo() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.PlusTwoTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testInsn.PlusTwo");
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
		Agent.restransform(PlusTwo.class);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}
	
	@Test
	public void testRunBoolClass() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.BoolClassTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testInsn.BoolClass");
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
		Agent.restransform(BoolClass.class);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}
	
	@Test
	public void testRunLongClass() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.LongClassTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testInsn.LongClass");
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
		Agent.restransform(LongClass.class);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}
	
}
