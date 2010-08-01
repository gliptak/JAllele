package com.github.gliptak.jallele.testInsn;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.github.gliptak.jallele.Agent;
import com.github.gliptak.jallele.ClassRandomizer;
import com.github.gliptak.jallele.MockSystem;


public class InsnTest {
	
	@Test
	public void testRunSimpleClass() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.SimpleClassTest"};
		MockSystem system=new MockSystem();
		Result result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			List<String> sources=new ArrayList<String>();
			sources.add("com.github.gliptak.jallele.testInsn.SimpleClass");
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
	    	result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(SimpleClass.class);
		result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
	}
	
	@Test
	public void testRunBoolClass() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.BoolClassTest"};
		MockSystem system=new MockSystem();
		Result result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			List<String> sources=new ArrayList<String>();
			sources.add("com.github.gliptak.jallele.testInsn.BoolClass");
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
            result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(SimpleClass.class);
		result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
	}
	
	@Test
	public void testRunLongClass() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testInsn.LongClassTest"};
		MockSystem system=new MockSystem();
		Result result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			List<String> sources=new ArrayList<String>();
			sources.add("com.github.gliptak.jallele.testInsn.LongClass");
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
            result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(SimpleClass.class);
		result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
	}
	
}
