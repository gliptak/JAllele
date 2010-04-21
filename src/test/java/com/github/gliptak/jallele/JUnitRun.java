package com.github.gliptak.jallele;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.security.Permission;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnitRun {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	protected static class ExitException extends SecurityException {
	    private static final long serialVersionUID = -1982617086752946683L;
	    public final int status;

	    public ExitException(int status) {
	        super("There is no escape!");
	        this.status = status;
	    }
	}
	
	public void startJUnitCheckExit(){
		SecurityManager securityManager = new SecurityManager() {
			@Override
		    public void checkExit(int status) {
		        super.checkExit(status);
		        throw new ExitException(status);
		    }
		};
		System.setSecurityManager(securityManager);
    	try 
    	{
    		JUnitCore.main(new String[0]);
    	} catch (ExitException e){
    	}
    	System.out.println("hello");
	}

	public void startJUnitCheckPermission(){
		SecurityManager securityManager = new SecurityManager() {
		    public void checkPermission(Permission permission) {
		    	super.checkPermission(permission);
		    	if ("exitVM".equals(permission.getName())) {
		    		throw new SecurityException("System.exit attempted and blocked.");
		    	}
		    }
		};
		System.setSecurityManager(securityManager);
    	try 
    	{
    		JUnitCore.main(new String[0]);
    	} catch (SecurityException e){
    	}
    	System.out.println("hello");
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

	@Test
	public void runJUnit() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		for (int i=0;i<5;i++){
			RandomizingClassloader cl=new RandomizingClassloader();
			MockSystem system=new MockSystem();
			String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
			cl.loadClass("com.github.gliptak.jallele.testA.SimpleClassTest");
			Result result= new JUnitCore().runMain(system, args);
			System.out.println("exit code: "+system.getExitCode());
			System.out.println(result);
		}
	}

	@Ignore
	@Test
	public void runJUnitCL() {
		for (int i=0;i<5;i++){
			ClassLoader oldCl=Thread.currentThread().getContextClassLoader(); 
			ClassLoader cl=new RandomizingClassloader();
			try {
				Thread.currentThread().setContextClassLoader(cl);
				callJunit();
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);				
			}
			
		}
	}

	@Ignore
	@Test
	public void runJUnitThread() {
		for (int i=0;i<5;i++){
			ClassLoader cl=new RandomizingClassloader();
			Thread t=new Thread(new CallJUnit());
			t.setContextClassLoader(cl);
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected class CallJUnit implements Runnable {

		@Override
		public void run() {
			String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
			JUnitSystem system=new MockSystem();
			JUnitCore core=new JUnitCore();
			Result result= core.runMain(system, args);
			System.out.println(result);
		}
		
	}
	
	protected void callJunit(JUnitCore core) {
		String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
		JUnitSystem system=new MockSystem();
		Result result= core.runMain(system, args);
		System.out.println(result);
	}
	
	protected void callJunit() {
		String[] args={"com.github.gliptak.jallele.testA.SimpleClassTest"};
		JUnitSystem system=new MockSystem();
		JUnitCore core=new JUnitCore();
		Result result= core.runMain(system, args);
		System.out.println(result);
	}
}