package com.github.gliptak.jallele.testJump;

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
import com.github.gliptak.jallele.testInsn.SimpleClass;


public class JumpTest {
	
	@Test
	public void testRunIfNull() throws Exception{
		String[] tests={"com.github.gliptak.jallele.testJump.IfNullTest"};
		List<String> sources=new ArrayList<String>();
		sources.add("com.github.gliptak.jallele.testJump.IfNull");
		MockSystem system=new MockSystem();
		Result result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
		for (int i=0;i<10;i++){
			Agent.attach();
			ClassRandomizer cr=new ClassRandomizer(sources);
			cr.recordMatches();
			result=cr.randomizeRun(system, Arrays.asList(tests));
			assertThat(result.getFailureCount(), IsNot.not(0));
			Agent.removeTransformer(cr);
		}
		Agent.restransform(IfNull.class);
		result= new JUnitCore().runMain(system, tests);
		assertThat(result.getFailureCount(), Is.is(0));
	}

}
