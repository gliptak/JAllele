package com.github.gliptak.jallele;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
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
			VisitStatus[] vss=cr.randomizeRun();
			if (runner.getFailureCount()>0) {
				if (vss.length>1) {
					System.out.println(vss[0]+" -> "+vss[1]);
					System.out.println(getDebugDescription(vss[0]));
				}
				assertThat(runner.getFailureCount(), IsNot.not(0));
			}
		}
		Agent.removeTransformer(cr);
		runner.runTests();
		assertThat(runner.getFailureCount(), Is.is(0));
	}

	public static String getDebugDescription(VisitStatus vs) {
		StringBuilder sb=new StringBuilder();
		String[] e=StringUtils.split(vs.getClassName(), '/');
		sb.append(vs.getClassName().replace('/', '.'));
		sb.append(".");
		sb.append(vs.getMethodName());
		sb.append("(");
		sb.append(e[e.length-1]);
		sb.append(".java:");
		sb.append(vs.getLineNumber());
		sb.append(")");			
		return sb.toString();
	}
}
