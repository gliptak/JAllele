package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class CommandLineArgs {
	
	@SuppressWarnings("unchecked")
	@Option(name="-sources",aliases={"--sources"},required=true,
			handler=StringArrayOptionHandler.class, usage="list of source files")
	private String[] sources=new String[0];
	
	@SuppressWarnings("unchecked")
	@Option(name="-tests",aliases={"--tests"},required=true,
			handler=StringArrayOptionHandler.class, usage="list of test files")
	private String[] tests=new String[0];
	
	@Option(name="-count", aliases={"--count"}, required=true,
			usage="number of test runs")
	private int count=0;
	
	@SuppressWarnings("unused")
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
	 * @return the arguments
	 */
	public List<String> getArguments() {
		return arguments;
	}
}
