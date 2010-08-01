/**
 * 
 */
package com.github.gliptak.jallele;

import java.io.PrintStream;

import org.junit.internal.JUnitSystem;

public class MockSystem implements JUnitSystem {
	
	protected int exitCode=0;

	public int getExitCode() {
		return exitCode;
	}

	public void exit(int code) {
		exitCode=code;
	}

	public PrintStream out() {
		return System.out;
	}

}