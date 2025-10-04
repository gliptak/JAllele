package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class CommandLineArgs {
	
	@Option(name="-sources",aliases={"--sources"},required=true,
			handler=StringArrayOptionHandler.class, usage="list of source files")
	private String[] sources=new String[0];
	
	@Option(name="-tests",aliases={"--tests"},required=true,
			handler=StringArrayOptionHandler.class, usage="list of test files")
	private String[] tests=new String[0];
	
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
	 * @return the sources
	 */
	public List<String> getSources() {
		return Arrays.asList(sources);
	}

	/**
	 * @return the tests
	 */
	public List<String> getTests() {
		return Arrays.asList(tests);
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
