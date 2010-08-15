package com.github.gliptak.jallele;

import java.util.List;

import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnit4Runner implements TestRunner {

	private final List<String> tests;
	private Result result;
	private JUnitSystem system;

	public JUnit4Runner(List<String> tests) {
		this.tests=tests;
		this.system=new Main.MockSystem();
	}

	public int getFailureCount() {
		return result.getFailureCount();
	}

	public void runTests() {
		result=new JUnitCore().runMain(system, (String[])tests.toArray(new String[tests.size()]));
	}
}
