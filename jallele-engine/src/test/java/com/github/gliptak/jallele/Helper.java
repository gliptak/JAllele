package com.github.gliptak.jallele;

import java.util.Arrays;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import static org.junit.Assert.assertThat;
import com.github.gliptak.jallele.Agent;

public class Helper {

	public static void runRandomized(String test, String source, int count) throws Exception {
		TestRunner runner = new EngineJUnit4Runner(Arrays.asList(test));
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
		Agent.attach();
		ClassRandomizer cr = new ClassRandomizer(Arrays.asList(source), runner);
		cr.recordMatches();
		for (int i = 0; i < 10; i++) {
			cr.randomizeRun();
			assertThat(runner.getFailureCount(), IsNot.not(0));
		}
		Agent.removeTransformer(cr);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}

}
