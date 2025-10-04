package com.github.gliptak.jallele;

import java.io.PrintStream;
import java.security.Permission;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.internal.JUnitSystem;
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
	    configureLogging(bean.getLogLevel());
	    if (bean.isRunJUnit()) {
		    rc=runJUnitTests(bean.getCount(), bean.getSources(), bean.getTests());
	    }
	    if (rc!=0){
	    	return rc;
	    }
	    if (bean.isRunTestNG()) {
		    rc=runTestNGTests(bean.getCount(), bean.getSources(), bean.getTests());
	    }
	    return rc;
	}

	protected int runTestNGTests(int count, List<String> sources, List<String> tests) throws Exception {
		TestRunner runner=new TestNGRunner(tests);
		return runTests(count, runner, sources);
	}

	protected int runJUnitTests(int count, List<String> sources, List<String> tests) throws Exception {
		TestRunner runner=new JUnit4Runner(tests);
		return runTests(count, runner, sources);
	}

	@SuppressWarnings("removal")
	protected int runTests(int count, TestRunner runner, List<String> sources) throws Exception {
		SecurityManager securityManager = null;
		boolean useSecurityManager = true;
		
		// Try to use SecurityManager if available (Java < 21)
		try {
			securityManager = System.getSecurityManager();
			System.setSecurityManager(new NoExitSecurityManager());
		} catch (UnsupportedOperationException e) {
			// SecurityManager is not supported (Java 21+), continue without it
			useSecurityManager = false;
		}

	    // first run to succeed
	    logger.fine("First run start");
		runner.runTests();
		if (runner.getFailureCount()!=0){
			logger.severe("First run failed");
			if (useSecurityManager) {
				System.setSecurityManager(securityManager);
			}
			return 1;
		}
	    logger.fine("First run complete");
		
	    logger.fine("Record matches start");
		Agent.attach();
		ClassRandomizer cr=new ClassRandomizer(sources, runner);
		cr.recordMatches();
	    logger.fine("Record matches complete");

        // second run to succeed
	    logger.fine("Second run start");
		runner.runTests();
		if (runner.getFailureCount()!=0){
			logger.severe("Second run failed");
			if (useSecurityManager) {
				System.setSecurityManager(securityManager);
			}
			return 1;
		}
	    logger.fine("Second run complete");		
		
		int expectedFailure=0;
	    for (int current=0; current<count; current++){
	    	cr.randomizeRun();
			if (runner.getFailureCount()>0){
				expectedFailure++;
			}
	    }

	    if (useSecurityManager) {
	    	System.setSecurityManager(securityManager);
	    }
	    logger.info("Results: "+expectedFailure+"/"+count+" ("+((float)expectedFailure/count)+")");
		return 0;
	}

	/**
	 * Configure logging level based on command line argument
	 * 
	 * @param logLevelStr the log level string (OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL)
	 */
	protected void configureLogging(String logLevelStr) {
		try {
			Level level = Level.parse(logLevelStr.toUpperCase());
			Logger rootLogger = Logger.getLogger("");
			rootLogger.setLevel(level);
			for (Handler handler : rootLogger.getHandlers()) {
				handler.setLevel(level);
			}
			// Also set level for the main logger
			logger.setLevel(level);
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid log level: " + logLevelStr + ". Using WARNING as default.");
		}
	}

	/**
	 * @param args
	 * @param bean
	 */
	protected int parseArguments(String[] args, CommandLineArgs bean) {
		CmdLineParser parser = new CmdLineParser(bean);
	    try {
			parser.parseArgument(args);
			if (bean.isHelp()) {
				System.out.println("java -jar jallele.jar [options...] ...");
				parser.printUsage(System.out);
				return 1;
			}
			if (bean.getArguments().size()>0){
				throw new CmdLineException("unparsable arguments: "+bean.getArguments().toString());
			}
			if (!bean.isRunJUnit() && !bean.isRunTestNG()) {
				throw new CmdLineException("one of JUnit or TestNG required");				
			}
	        return 0;
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
		    System.err.println("java -jar jallele.jar [options...] ...");
		    parser.printUsage(System.err);
		    return 2;
		}
	}

	public static class MockSystem implements JUnitSystem {
		
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

	@SuppressWarnings("removal")
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