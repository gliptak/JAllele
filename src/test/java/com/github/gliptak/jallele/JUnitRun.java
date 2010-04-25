package com.github.gliptak.jallele;

import java.io.PrintStream;
import java.security.Permission;

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

	@Before
	public void setUp() throws Exception {
		securityManager = System.getSecurityManager();
	    System.setSecurityManager(new NoExitSecurityManager());
	}

	@After
	public void tearDown() throws Exception {
		System.setSecurityManager(securityManager);
	}

	@Test
	public void runJUnit() throws Exception{
		for (int i=0;i<2;i++){
			ClassRandomizer cr=new ClassRandomizer();
			cr.setFilter("com.github.gliptak.jallele.testA.*");
			Agent.addTransformer(cr, true);
			Agent.restransform(SimpleClass.class);
			MockSystem system=new MockSystem();
			String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
			Result result= new JUnitCore().runMain(system, args);
			System.out.println("exit code: "+system.getExitCode());
			System.out.println(result);
			Agent.removeTransformer(cr);
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
	
	protected static class ExitException extends SecurityException {
	    private static final long serialVersionUID = -1982617086752946683L;
	    public final int status;

	    public ExitException(int status) {
	        super("There is no escape!");
	        this.status = status;
	    }
	}

	private static class NoExitSecurityManager extends SecurityManager {
	    @Override
	    public void checkPermission(Permission perm) {
	        // allow anything.
	    }

	    @Override
	    public void checkPermission(Permission perm, Object context) {
	        // allow anything.
	    }

	    @Override
	    public void checkExit(int status) {
	        super.checkExit(status);
	        throw new ExitException(status);
	    }
	}

	private SecurityManager securityManager;
	
}