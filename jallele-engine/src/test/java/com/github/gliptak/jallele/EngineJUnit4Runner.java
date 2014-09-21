package com.github.gliptak.jallele;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        try {
            Class<?>[] argClasses={JUnitSystem.class, String[].class};
            Method method = JUnitCore.class.getDeclaredMethod("runMain", argClasses);
            method.setAccessible(true);
            Object[] args = {system, (String[])tests.toArray(new String[tests.size()])};
            result=(Result)method.invoke(new JUnitCore(), args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
	}

}
