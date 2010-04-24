package com.github.gliptak.jallele;

import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.github.gliptak.jallele.testA.SimpleClass;

public class JUnitRun {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Agent.attach();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	protected ClassRandomizer cr;
	
	@Before
	public void setUp() throws Exception {
		cr=new ClassRandomizer();
		cr.setFilter("com.github.gliptak.jallele.*");
		Agent.addTransformer(cr, true);
	}

	@After
	public void tearDown() throws Exception {
		Agent.removeTransformer(cr);
	}

	@Test
	public void runJUnit() throws Exception{
		for (int i=0;i<5;i++){
			Agent.restransform(SimpleClass.class);
			MockSystem system=new MockSystem();
			String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
			Result result= new JUnitCore().runMain(system, args);
			System.out.println("exit code: "+system.getExitCode());
			System.out.println(result);
		}
	}

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
}