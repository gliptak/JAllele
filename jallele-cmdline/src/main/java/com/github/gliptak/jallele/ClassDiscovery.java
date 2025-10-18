package com.github.gliptak.jallele;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Discovers classes from classpaths (directories and JAR files) based on patterns
 */
public class ClassDiscovery {
	
	private static Logger logger = Logger.getLogger(ClassDiscovery.class.getName());
	
	/**
	 * Discover classes from the given classpaths that match the given patterns
	 * 
	 * @param classpaths list of classpath entries (jar files or directories)
	 * @param patterns list of patterns (e.g., "com.example.**", "**.Test")
	 * @return list of fully qualified class names
	 */
	public static List<String> discoverClasses(List<String> classpaths, List<String> patterns) {
		List<String> classes = new ArrayList<>();
		
		if (classpaths.isEmpty()) {
			return classes;
		}
		
		List<Pattern> regexPatterns = convertPatternsToRegex(patterns);
		
		for (String classpath : classpaths) {
			File file = new File(classpath);
			if (!file.exists()) {
				logger.warning("Classpath does not exist: " + classpath);
				continue;
			}
			
			if (file.isDirectory()) {
				classes.addAll(discoverFromDirectory(file, regexPatterns));
			} else if (file.getName().endsWith(".jar")) {
				classes.addAll(discoverFromJar(file, regexPatterns));
			} else {
				logger.warning("Unsupported classpath entry: " + classpath);
			}
		}
		
		logger.fine("Discovered " + classes.size() + " classes from " + classpaths.size() + " classpath entries");
		return classes;
	}
	
	/**
	 * Convert glob-style patterns to regex patterns
	 * Supports:
	 * - ** for any number of package segments
	 * - * for any characters within a segment
	 * 
	 * @param patterns list of glob patterns
	 * @return list of regex patterns
	 */
	private static List<Pattern> convertPatternsToRegex(List<String> patterns) {
		List<Pattern> regexPatterns = new ArrayList<>();
		
		for (String pattern : patterns) {
			// Convert glob pattern to regex
			// ** matches any number of package segments (including none)
			// * matches any characters within a segment
			String regex = pattern
				.replace(".", "\\.")  // Escape dots
				.replace("**", "###DOUBLE_STAR###")  // Temporarily replace **
				.replace("*", "[^.]*")  // * matches any chars except dot
				.replace("###DOUBLE_STAR###", ".*");  // ** matches anything
			
			// Add anchors
			regex = "^" + regex + "$";
			
			regexPatterns.add(Pattern.compile(regex));
			logger.fine("Pattern '" + pattern + "' converted to regex '" + regex + "'");
		}
		
		return regexPatterns;
	}
	
	/**
	 * Check if a class name matches any of the patterns
	 * 
	 * @param className fully qualified class name
	 * @param patterns list of regex patterns
	 * @return true if the class matches at least one pattern, or if patterns is empty
	 */
	private static boolean matchesPattern(String className, List<Pattern> patterns) {
		if (patterns.isEmpty()) {
			return true;  // No patterns means include all
		}
		
		for (Pattern pattern : patterns) {
			if (pattern.matcher(className).matches()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Discover classes from a directory
	 * 
	 * @param directory root directory to search
	 * @param patterns list of regex patterns to match
	 * @return list of fully qualified class names
	 */
	private static List<String> discoverFromDirectory(File directory, List<Pattern> patterns) {
		List<String> classes = new ArrayList<>();
		Path rootPath = directory.toPath();
		
		try (Stream<Path> paths = Files.walk(rootPath)) {
			List<Path> classFiles = paths
				.filter(Files::isRegularFile)
				.filter(p -> p.toString().endsWith(".class"))
				.collect(Collectors.toList());
			
			for (Path classFile : classFiles) {
				Path relativePath = rootPath.relativize(classFile);
				String className = pathToClassName(relativePath.toString());
				
				if (matchesPattern(className, patterns)) {
					classes.add(className);
					logger.fine("Discovered class from directory: " + className);
				}
			}
		} catch (IOException e) {
			logger.severe("Error reading directory " + directory + ": " + e.getMessage());
		}
		
		return classes;
	}
	
	/**
	 * Discover classes from a JAR file
	 * 
	 * @param jarFile JAR file to search
	 * @param patterns list of regex patterns to match
	 * @return list of fully qualified class names
	 */
	private static List<String> discoverFromJar(File jarFile, List<Pattern> patterns) {
		List<String> classes = new ArrayList<>();
		
		try (JarFile jar = new JarFile(jarFile)) {
			Enumeration<JarEntry> entries = jar.entries();
			
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				
				if (name.endsWith(".class")) {
					String className = pathToClassName(name);
					
					if (matchesPattern(className, patterns)) {
						classes.add(className);
						logger.fine("Discovered class from JAR: " + className);
					}
				}
			}
		} catch (IOException e) {
			logger.severe("Error reading JAR file " + jarFile + ": " + e.getMessage());
		}
		
		return classes;
	}
	
	/**
	 * Convert a file path to a class name
	 * 
	 * @param path file path (e.g., "com/example/MyClass.class")
	 * @return fully qualified class name (e.g., "com.example.MyClass")
	 */
	private static String pathToClassName(String path) {
		// Remove .class extension
		String className = path.substring(0, path.length() - 6);
		
		// Replace file separators with dots
		className = className.replace('/', '.').replace('\\', '.');
		
		return className;
	}
}
