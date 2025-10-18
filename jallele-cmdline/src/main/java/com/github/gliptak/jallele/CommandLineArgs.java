package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class CommandLineArgs {
	
	@Option(name="-sources",aliases={"--sources"},
			handler=StringArrayOptionHandler.class, usage="list of source class names (deprecated: use --source-classes)")
	private String[] sources=new String[0];
	
	@Option(name="-tests",aliases={"--tests"},
			handler=StringArrayOptionHandler.class, usage="list of test class names (deprecated: use --test-classes)")
	private String[] tests=new String[0];
	
	@Option(name="-sourceclasses",aliases={"--source-classes"},
			handler=StringArrayOptionHandler.class, usage="list of source class names")
	private String[] sourceClasses=new String[0];
	
	@Option(name="-testclasses",aliases={"--test-classes"},
			handler=StringArrayOptionHandler.class, usage="list of test class names")
	private String[] testClasses=new String[0];
	
	@Option(name="-sourcepath",aliases={"--source-path","--source-classpath"},
			handler=StringArrayOptionHandler.class, usage="classpath for source classes (jar files or directories)")
	private String[] sourcePath=new String[0];
	
	@Option(name="-testpath",aliases={"--test-path","--test-classpath"},
			handler=StringArrayOptionHandler.class, usage="classpath for test classes (jar files or directories)")
	private String[] testPath=new String[0];
	
	@Option(name="-sourcepatterns",aliases={"--source-patterns"},
			handler=StringArrayOptionHandler.class, usage="patterns for source classes (e.g., com.example.**)")
	private String[] sourcePatterns=new String[0];
	
	@Option(name="-testpatterns",aliases={"--test-patterns"},
			handler=StringArrayOptionHandler.class, usage="patterns for test classes (e.g., com.example.**Test)")
	private String[] testPatterns=new String[0];
	
	@Option(name="-count", aliases={"--count"}, required=true,
			usage="number of test runs")
	private int count=0;

	@Option(name="-junit", aliases={"--junit"}, usage="run with JUnit")
	private boolean runJUnit=false;

	@Option(name="-testng", aliases={"--testng"}, usage="run with TestNG")
	private boolean runTestNG=false;

	@Option(name="-h", aliases={"--help"}, usage="print this help message", help=true)
	private boolean help=false;

	@Option(name="-loglevel", aliases={"--log-level"}, usage="set log level (OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL)")
	private String logLevel="WARNING";

	@Argument
    private List<String> arguments = new ArrayList<String>();

	/**
	 * @return the sources (legacy support)
	 */
	public List<String> getSources() {
		if (sourceClasses.length > 0) {
			return Arrays.asList(sourceClasses);
		}
		return Arrays.asList(sources);
	}

	/**
	 * @return the tests (legacy support)
	 */
	public List<String> getTests() {
		if (testClasses.length > 0) {
			return Arrays.asList(testClasses);
		}
		return Arrays.asList(tests);
	}
	
	/**
	 * @return the source class names
	 */
	public List<String> getSourceClasses() {
		return Arrays.asList(sourceClasses);
	}
	
	/**
	 * @return the test class names
	 */
	public List<String> getTestClasses() {
		return Arrays.asList(testClasses);
	}
	
	/**
	 * @return the source classpath
	 */
	public List<String> getSourcePath() {
		return Arrays.asList(sourcePath);
	}
	
	/**
	 * @return the test classpath
	 */
	public List<String> getTestPath() {
		return Arrays.asList(testPath);
	}
	
	/**
	 * @return the source patterns
	 */
	public List<String> getSourcePatterns() {
		return Arrays.asList(sourcePatterns);
	}
	
	/**
	 * @return the test patterns
	 */
	public List<String> getTestPatterns() {
		return Arrays.asList(testPatterns);
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the runJUnit
	 */
	public boolean isRunJUnit() {
		return runJUnit;
	}

	/**
	 * @return the runTestNG
	 */
	public boolean isRunTestNG() {
		return runTestNG;
	}

	/**
	 * @return the arguments
	 */
	public List<String> getArguments() {
		return arguments;
	}

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * @return the logLevel
	 */
	public String getLogLevel() {
		return logLevel;
	}
}
