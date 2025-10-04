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
	public final void testParseArgumentsHelpShort() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"-h"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(1));
		assertThat(bean.isHelp(), Is.is(true));
	}

	@Test
	public final void testParseArgumentsHelpLong() {
		CommandLineArgs bean = new CommandLineArgs();
		Main m=new Main();
		String[] args={"--help"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(1));
		assertThat(bean.isHelp(), Is.is(true));
	}

	@Test
	public final void testExecuteWithHelp() throws Exception {
		Main m = new Main();
		String[] args={"-h"};
		int exitCode = m.execute(args);
		assertThat(exitCode, Is.is(1));
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

	@Test
	public final void testMainMethodWithSuccess() throws Exception {
		// Install our security manager that throws ExitException instead of exiting
		SecurityManager originalSM = System.getSecurityManager();
		System.setSecurityManager(new Main.NoExitSecurityManager());
		
		try {
			String[] args={"--count", "1", "--junit", "--sources", SimpleClass.class.getName(),
					"--tests", SimpleClassJUnitTest.class.getName()};
			Main.main(args);
			fail("Expected ExitException to be thrown");
		} catch (Main.ExitException ex) {
			assertThat(ex.status, Is.is(0));
		} finally {
			System.setSecurityManager(originalSM);
		}
	}

	@Test
	public final void testMainMethodWithFailure() throws Exception {
		// Install our security manager that throws ExitException instead of exiting
		SecurityManager originalSM = System.getSecurityManager();
		System.setSecurityManager(new Main.NoExitSecurityManager());
		
		try {
			String[] args={"-wrong"};
			Main.main(args);
			fail("Expected ExitException to be thrown");
		} catch (Main.ExitException ex) {
			assertThat(ex.status, Is.is(2));
		} finally {
			System.setSecurityManager(originalSM);
		}
	}

	@Test
	public final void testExecuteWithBothJUnitAndTestNG() throws Exception {
		Main m = new Main();
		String[] args={"--count", "1", "--junit", "--testng", "--sources", SimpleClass.class.getName(),
				"--tests", SimpleClassJUnitTest.class.getName()};
		int exitCode = m.execute(args);
		// Should process JUnit first, then TestNG
		assertThat(exitCode, Is.is(0));
	}

	@Test
	public final void testExecuteWithJUnitFailureThenTestNG() throws Exception {
		Main m = new Main();
		// Use non-existent class for JUnit (should fail), but valid class for TestNG
		String[] args={"--count", "1", "--junit", "--testng", "--sources", "NonExistentClass",
				"--tests", "NonExistentTest"};
		int exitCode = m.execute(args);
		// Should fail on JUnit and return early before reaching TestNG
		assertThat(exitCode, Is.is(1));
	}

	// Helper class to create a fake failing test scenario
	public static class FailingTest {
		private static int callCount = 0;
		
		@org.junit.Test
		public void testThatFails() {
			callCount++;
			if (callCount > 1) {
				throw new RuntimeException("Second run failure");
			}
		}
		
		public static void reset() {
			callCount = 0;
		}
	}

	@Test
	public final void testRunTestsWithSecondRunFailure() throws Exception {
		// This test simulates a scenario where the second run fails
		FailingTest.reset();
		
		Main m = new Main();
		String[] args={"--count", "1", "--junit", "--sources", FailingTest.class.getName(),
				"--tests", FailingTest.class.getName()};
		int exitCode = m.execute(args);
		
		// The test should detect the second run failure
		assertThat(exitCode, Is.is(1));
	}

	@Test
	public final void testRunTestsWithRandomizationFailures() throws Exception {
		// Use a simple working test to get through the first two runs,
		// then let randomization potentially cause failures
		Main m = new Main();
		String[] args={"--count", "10", "--junit", "--sources", SimpleClass.class.getName(),
				"--tests", SimpleClassJUnitTest.class.getName()};
		int exitCode = m.execute(args);
		
		// Should complete successfully even if some randomized runs fail
		assertThat(exitCode, Is.is(0));
	}
}
