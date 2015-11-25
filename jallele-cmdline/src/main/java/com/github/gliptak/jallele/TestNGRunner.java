package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestNGRunner implements TestRunner {

	final private List<String> tests;
	private List<ITestResult> failures;

	public TestNGRunner(List<String> tests) {
		this.tests=tests;
	}

	@Override
	public void runTests() {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		@SuppressWarnings("rawtypes")
		List<Class> classes=new ArrayList<Class>();
		for (String s: tests) {
			try {
				classes.add(Class.forName(s));
			} catch (ClassNotFoundException cnfe) {
				failures=new ArrayList<ITestResult>();
				ITestResult result=null;
				failures.add(result);
				return;
			}
		}
		testng.setTestClasses(classes.toArray(new Class[0]));
		testng.addListener(tla);
		testng.run();
		failures=tla.getFailedTests();
	}

	@Override
	public int getFailureCount() {
		return failures.size();
	}

}
