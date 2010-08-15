package com.github.gliptak.jallele;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.internal.JUnitSystem;

public class EngineJUnit4Runner implements TestRunner {

	private final List<String> tests;
	private Result result;
	private JUnitSystem system;

	public EngineJUnit4Runner(List<String> tests) {
		this.tests=tests;
		this.system=new EngineMockSystem();
	}

	public int getFailureCount() {
		return result.getFailureCount();
	}

	public void runTests() {
		result=new JUnitCore().runMain(system, (String[])tests.toArray(new String[tests.size()]));
	}

}
