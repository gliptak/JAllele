package com.github.gliptak.jallele;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
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
	    
	    // Add classpath entries to the system classloader
	    addToClasspath(bean.getSourcePath());
	    addToClasspath(bean.getTestPath());
	    
	    // Discover source classes
	    List<String> sourceClassNames = discoverSourceClasses(bean);
	    logger.fine("Source classes: " + sourceClassNames);
	    
	    // Discover test classes
	    List<String> testClassNames = discoverTestClasses(bean);
	    logger.fine("Test classes: " + testClassNames);
	    
	    if (bean.isRunJUnit()) {
		    rc=runJUnitTests(bean.getCount(), sourceClassNames, testClassNames);
	    }
	    if (rc!=0){
	    	return rc;
	    }
	    if (bean.isRunTestNG()) {
		    rc=runTestNGTests(bean.getCount(), sourceClassNames, testClassNames);
	    }
	    return rc;
	}
	
	/**
	 * Discover source classes from the command line arguments
	 * 
	 * @param bean command line arguments
	 * @return list of fully qualified source class names
	 */
	protected List<String> discoverSourceClasses(CommandLineArgs bean) {
		// If using explicit class names, use those
		if (!bean.getSourceClasses().isEmpty()) {
			return bean.getSourceClasses();
		}
		
		// Otherwise, discover from classpath and patterns
		return ClassDiscovery.discoverClasses(bean.getSourcePath(), bean.getSourcePatterns());
	}
	
	/**
	 * Discover test classes from the command line arguments
	 * 
	 * @param bean command line arguments
	 * @return list of fully qualified test class names
	 */
	protected List<String> discoverTestClasses(CommandLineArgs bean) {
		// If using explicit class names, use those
		if (!bean.getTestClasses().isEmpty()) {
			return bean.getTestClasses();
		}
		
		// Otherwise, discover from classpath and patterns
		return ClassDiscovery.discoverClasses(bean.getTestPath(), bean.getTestPatterns());
	}
	
	/**
	 * Add classpath entries to the system classloader
	 * This is necessary so that discovered classes can be loaded by JUnit/TestNG
	 * 
	 * @param classpaths list of classpath entries (jar files or directories)
	 */
	protected void addToClasspath(List<String> classpaths) {
		if (classpaths.isEmpty()) {
			return;
		}
		
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		
		// For Java 9+, we need to set the thread context classloader to a URLClassLoader
		// that includes the additional classpath entries
		try {
			URL[] urls = new URL[classpaths.size()];
			for (int i = 0; i < classpaths.size(); i++) {
				File file = new File(classpaths.get(i));
				if (!file.exists()) {
					logger.warning("Classpath does not exist: " + classpaths.get(i));
					continue;
				}
				urls[i] = file.toURI().toURL();
				logger.fine("Added to classpath: " + classpaths.get(i));
			}
			
			// Create a new URLClassLoader with the current context classloader as parent
			ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
			if (currentClassLoader == null) {
				currentClassLoader = systemClassLoader;
			}
			URLClassLoader newClassLoader = new URLClassLoader(urls, currentClassLoader);
			
			// Set it as the thread context classloader
			Thread.currentThread().setContextClassLoader(newClassLoader);
			logger.fine("Set thread context classloader with " + classpaths.size() + " classpath entries");
			
		} catch (Exception e) {
			logger.severe("Failed to add classpath entries: " + e.getMessage());
			e.printStackTrace();
		}
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
				System.out.println();
				System.out.println("Examples:");
				System.out.println("  Explicit class names:");
				System.out.println("    java -jar jallele.jar --count 10 --junit \\");
				System.out.println("      --source-classes com.example.MyClass \\");
				System.out.println("      --test-classes com.example.MyClassTest");
				System.out.println();
				System.out.println("  With classpath and patterns:");
				System.out.println("    java -jar jallele.jar --count 10 --junit \\");
				System.out.println("      --source-path target/classes \\");
				System.out.println("      --source-patterns 'com.example.**' \\");
				System.out.println("      --test-path target/test-classes \\");
				System.out.println("      --test-patterns 'com.example.**Test'");
				System.out.println();
				System.out.println("  With multiple paths/patterns:");
				System.out.println("    java -jar jallele.jar --count 10 --junit \\");
				System.out.println("      --source-path module1/target/classes module2/target/classes \\");
				System.out.println("      --source-patterns 'com.example.**' 'org.other.**' \\");
				System.out.println("      --test-path module1/target/test-classes module2/target/test-classes \\");
				System.out.println("      --test-patterns 'com.example.**Test' 'org.other.**Test'");
				System.out.println();
				System.out.println("  With JAR files:");
				System.out.println("    java -jar jallele.jar --count 10 --junit \\");
				System.out.println("      --source-path myproject.jar \\");
				System.out.println("      --source-patterns 'com.example.**' \\");
				System.out.println("      --test-path myproject-tests.jar \\");
				System.out.println("      --test-patterns 'com.example.**Test'");
				return 1;
			}
			if (bean.getArguments().size()>0){
				throw new CmdLineException("unparsable arguments: "+bean.getArguments().toString());
			}
			if (!bean.isRunJUnit() && !bean.isRunTestNG()) {
				throw new CmdLineException("one of JUnit or TestNG required");				
			}
			
			// Require at least one way to specify source classes
			if (bean.getSourceClasses().isEmpty() && bean.getSourcePath().isEmpty()) {
				throw new CmdLineException("must specify sources using --source-classes or --source-path with --source-patterns");
			}
			
			// Require at least one way to specify test classes
			if (bean.getTestClasses().isEmpty() && bean.getTestPath().isEmpty()) {
				throw new CmdLineException("must specify tests using --test-classes or --test-path with --test-patterns");
			}
			
			// If using classpath, require patterns
			if (!bean.getSourcePath().isEmpty() && bean.getSourcePatterns().isEmpty() && bean.getSourceClasses().isEmpty()) {
				throw new CmdLineException("--source-path requires --source-patterns or --source-classes");
			}
			
			if (!bean.getTestPath().isEmpty() && bean.getTestPatterns().isEmpty() && bean.getTestClasses().isEmpty()) {
				throw new CmdLineException("--test-path requires --test-patterns or --test-classes");
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