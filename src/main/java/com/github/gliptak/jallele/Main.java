package com.github.gliptak.jallele;

import java.io.PrintStream;
import java.security.Permission;
import java.util.List;
import java.util.logging.Logger;

import org.junit.internal.JUnitSystem;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class.getName());

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Main m=new Main();
		int rc=m.execute(args);
	    System.exit(rc);
	}

	public int execute(String[] args) throws Exception {
		CommandLineArgs bean = new CommandLineArgs();
	    int rc=parseArguments(args, bean);
	    if (rc!=0){
	    	return rc;
	    }
	    rc=runTests(bean.getCount(), bean.getSources(), bean.getTests());
	    return rc;
	}

	protected int runTests(int count, List<String> sources, List<String> tests) throws Exception {
		SecurityManager securityManager = System.getSecurityManager();
	    System.setSecurityManager(new NoExitSecurityManager());
	    
        // first run to succeed
		MockSystem system=new MockSystem();
		Result firstResult= new JUnitCore().runMain(system, (String[])tests.toArray(new String[tests.size()]));
		if (firstResult.getFailureCount()!=0){
			System.out.println("first run failed");
			return 1;
		}
		
		Agent.attach();
		ClassRandomizer cr=new ClassRandomizer(sources);
		cr.recordMatches();

        // second run to succeed
		Result secondResult= new JUnitCore().runMain(system, (String[])tests.toArray(new String[tests.size()]));
		if (secondResult.getFailureCount()!=0){
			System.out.println("second run failed");
			return 1;
		}
		
		
		int expectedFailure=0;
	    for (int current=0; current<count; current++){
	    	Result result=cr.randomizeRun(system, tests);
			if (result.getFailureCount()>0){
				expectedFailure++;
			}
	    	count--;   	
	    }
	    
	    System.setSecurityManager(securityManager);
	    System.out.println("results "+expectedFailure+"/"+count+" ("+((float)expectedFailure/count)+")");
		return 0;
	}

	/**
	 * @param args
	 * @param bean
	 */
	protected int parseArguments(String[] args, CommandLineArgs bean) {
		CmdLineParser parser = new CmdLineParser(bean);
	    try {
			parser.parseArgument(args);
			if (bean.getArguments().size()>0){
				throw new CmdLineException("unparsable arguments: "+bean.getArguments().toString());
			}
	        return 0;
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		    System.err.println("java -jar jallele.jar [options...] ...");
		    parser.printUsage(System.err);
		    return 2;
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
		private static final long serialVersionUID = -4046443090085152692L;
		
		public final int status;

	    public ExitException(int status) {
	        super("There is no escape!");
	        this.status = status;
	    }
	}

	protected static class NoExitSecurityManager extends SecurityManager {
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
}