package com.github.gliptak.jallele;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassDiscoveryTest {

	private Path tempDir;
	private Path jarFile;
	
	@Before
	public void setUp() throws Exception {
		tempDir = Files.createTempDirectory("jallele-test");
	}

	@After
	public void tearDown() throws Exception {
		// Clean up temp directory
		if (tempDir != null && Files.exists(tempDir)) {
			deleteDirectory(tempDir.toFile());
		}
		if (jarFile != null && Files.exists(jarFile)) {
			Files.delete(jarFile);
		}
	}
	
	private void deleteDirectory(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		}
		directory.delete();
	}

	@Test
	public void testDiscoverFromDirectoryNoPattern() throws Exception {
		// Create test class files
		createClassFile("com/example/MyClass.class");
		createClassFile("com/example/test/MyTest.class");
		createClassFile("org/other/OtherClass.class");
		
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(tempDir.toString()),
			Arrays.asList()
		);
		
		assertThat(classes.size(), Is.is(3));
		assertTrue(classes.contains("com.example.MyClass"));
		assertTrue(classes.contains("com.example.test.MyTest"));
		assertTrue(classes.contains("org.other.OtherClass"));
	}

	@Test
	public void testDiscoverFromDirectoryWithPattern() throws Exception {
		// Create test class files
		createClassFile("com/example/MyClass.class");
		createClassFile("com/example/test/MyTest.class");
		createClassFile("org/other/OtherClass.class");
		
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(tempDir.toString()),
			Arrays.asList("com.example.**")
		);
		
		assertThat(classes.size(), Is.is(2));
		assertTrue(classes.contains("com.example.MyClass"));
		assertTrue(classes.contains("com.example.test.MyTest"));
	}

	@Test
	public void testDiscoverFromDirectoryWithTestPattern() throws Exception {
		// Create test class files
		createClassFile("com/example/MyClass.class");
		createClassFile("com/example/MyTest.class");
		createClassFile("com/example/MyTestHelper.class");
		createClassFile("com/example/test/AnotherTest.class");
		
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(tempDir.toString()),
			Arrays.asList("**Test")
		);
		
		assertThat(classes.size(), Is.is(2));
		assertTrue(classes.contains("com.example.MyTest"));
		assertTrue(classes.contains("com.example.test.AnotherTest"));
	}

	@Test
	public void testDiscoverFromDirectoryWithWildcard() throws Exception {
		// Create test class files
		createClassFile("com/example/MyClass.class");
		createClassFile("com/example/MyTest.class");
		createClassFile("com/other/MyTest.class");
		
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(tempDir.toString()),
			Arrays.asList("com.example.*")
		);
		
		assertThat(classes.size(), Is.is(2));
		assertTrue(classes.contains("com.example.MyClass"));
		assertTrue(classes.contains("com.example.MyTest"));
	}

	@Test
	public void testDiscoverFromJar() throws Exception {
		// Create a JAR file with test classes
		jarFile = Files.createTempFile("jallele-test", ".jar");
		
		try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFile.toFile()))) {
			addJarEntry(jos, "com/example/MyClass.class");
			addJarEntry(jos, "com/example/test/MyTest.class");
			addJarEntry(jos, "org/other/OtherClass.class");
		}
		
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(jarFile.toString()),
			Arrays.asList("com.example.**")
		);
		
		assertThat(classes.size(), Is.is(2));
		assertTrue(classes.contains("com.example.MyClass"));
		assertTrue(classes.contains("com.example.test.MyTest"));
	}

	@Test
	public void testDiscoverFromMultipleClasspaths() throws Exception {
		// Create first directory
		createClassFile("com/example/MyClass.class");
		
		// Create second directory
		Path tempDir2 = Files.createTempDirectory("jallele-test2");
		Path classFile2 = tempDir2.resolve("com/example/test/MyTest.class");
		Files.createDirectories(classFile2.getParent());
		Files.createFile(classFile2);
		
		try {
			List<String> classes = ClassDiscovery.discoverClasses(
				Arrays.asList(tempDir.toString(), tempDir2.toString()),
				Arrays.asList("com.example.**")
			);
			
			assertThat(classes.size(), Is.is(2));
			assertTrue(classes.contains("com.example.MyClass"));
			assertTrue(classes.contains("com.example.test.MyTest"));
		} finally {
			deleteDirectory(tempDir2.toFile());
		}
	}

	@Test
	public void testDiscoverFromNonExistentPath() {
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList("/non/existent/path"),
			Arrays.asList("**")
		);
		
		assertThat(classes.size(), Is.is(0));
	}

	@Test
	public void testDiscoverEmptyClasspaths() {
		List<String> classes = ClassDiscovery.discoverClasses(
			Arrays.asList(),
			Arrays.asList("**")
		);
		
		assertThat(classes.size(), Is.is(0));
	}
	
	private void createClassFile(String path) throws IOException {
		Path classFile = tempDir.resolve(path);
		Files.createDirectories(classFile.getParent());
		Files.createFile(classFile);
	}
	
	private void addJarEntry(JarOutputStream jos, String name) throws IOException {
		JarEntry entry = new JarEntry(name);
		jos.putNextEntry(entry);
		jos.closeEntry();
	}
}
