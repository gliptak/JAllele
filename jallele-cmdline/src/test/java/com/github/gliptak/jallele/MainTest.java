package com.github.gliptak.jallele;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {

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

	@Test
	public final void testMainJUnit() throws Exception {
		String[] args={"--count", "10", "--junit", "--sources", SimpleClass.class.getName(),
				"--tests", SimpleClassJUnitTest.class.getName()};
		Main m = new Main();
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(0));
	}

	@Test
	public final void testMainTestNG() throws Exception {
		String[] args={"--count", "10", "--testng", "--sources", SimpleClass.class.getName(),
				"--tests", SimpleClassTestNGTest.class.getName()};
		Main m = new Main();
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(0));
	}

	@Test
	public final void testParseArgumentsNone() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(2));
	}

	@Test
	public final void testParseArgumentsWrong() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"-wrong"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(2));
	}

	@Test
	public final void testParseArgumentsMoreArgs() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"stuff"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(2));
	}

	@Test
	public final void testParseArgumentsEach() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"--count", "1", "--testng", "--sources", "Main", "--tests", "MainTest Main1Test"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(0));
		assertThat(bean.getCount(), Is.is(1));
		assertThat(bean.isRunJUnit(), Is.is(false));
		assertThat(bean.isRunTestNG(), Is.is(true));
		assertThat(bean.getSources().size(), Is.is(1));
		assertThat(bean.getTests().size(), Is.is(2));
	}

	@Test
	public final void testParseArgumentsEachMoreArgs() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"--sources", "Main", "--tests", "MainTest Main1Test", "--count", "1", "stuff"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(2));
	}

	@Test
	public final void testParseArgumentsTwice() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"--count", "2", "--junit", "--sources", "Main Main1", "--tests", "MainTest"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(0));
		assertThat(bean.getCount(), Is.is(2));
		assertThat(bean.getSources().size(), Is.is(2));
		assertThat(bean.getTests().size(), Is.is(1));
	}

	@Test
	public final void testMockSystemExitCode() {
		Main.MockSystem mockSystem = new Main.MockSystem();
		assertThat(mockSystem.getExitCode(), Is.is(0));
		
		mockSystem.exit(42);
		assertThat(mockSystem.getExitCode(), Is.is(42));
	}

	@Test
	public final void testMockSystemOut() {
		Main.MockSystem mockSystem = new Main.MockSystem();
		assertThat(mockSystem.out(), Is.is(System.out));
	}

	@Test
	public final void testParseArgumentsNoFramework() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"--count", "1", "--sources", "Main", "--tests", "MainTest"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(2));
	}

	@Test
	public final void testExecuteWithParseFailure() throws Exception {
		Main m = new Main();
		String[] args={"-wrong"};
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(2));
	}

	@Test
	public final void testExitException() {
		Main.ExitException ex = new Main.ExitException(5);
		assertThat(ex.status, Is.is(5));
		assertThat(ex.getMessage(), Is.is("There is no escape!"));
	}

	@Test
	public final void testNoExitSecurityManager() {
		Main.NoExitSecurityManager sm = new Main.NoExitSecurityManager();
		
		// Test that checkPermission methods don't throw exceptions
		sm.checkPermission(new RuntimePermission("test"));
		sm.checkPermission(new RuntimePermission("test"), this);
		
		// Test that checkExit throws ExitException
		try {
			sm.checkExit(1);
			fail("Expected ExitException to be thrown");
		} catch (Main.ExitException ex) {
			assertThat(ex.status, Is.is(1));
		}
	}

	@Test
	public final void testExecuteWithJUnitFailure() throws Exception {
		Main m = new Main();
		// Using non-existent test class to trigger test failure
		String[] args={"--count", "1", "--junit", "--sources", "NonExistentClass",
				"--tests", "NonExistentTest"};
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(1));
	}

	@Test 
	public final void testExecuteWithTestNGFailure() throws Exception {
		Main m = new Main();
		// Using non-existent test class to trigger test failure
		String[] args={"--count", "1", "--testng", "--sources", "NonExistentClass",
				"--tests", "NonExistentTest"};
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(1));
	}
}
