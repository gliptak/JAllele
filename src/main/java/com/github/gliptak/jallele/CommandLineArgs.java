package com.github.gliptak.jallele;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class CommandLineArgs {
	
	@SuppressWarnings("unchecked")
	@Option(name="-sources",aliases={"--sources"},required=true,
			handler=StringArrayOptionHandler.class, usage="list source files")
	private List<String> sources=new ArrayList<String>();
	
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
		return sources;
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
