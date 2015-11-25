package com.github.gliptak.jallele;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.gliptak.jallele.Main.ExitException;
import com.github.gliptak.jallele.Main.NoExitSecurityManager;

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
	public final void testMain() throws Exception {
		String[] args={"--count", "10", "--sources", "com.github.gliptak.jallele.SimpleClass",
				"--tests", "com.github.gliptak.jallele.SimpleClassJUnitTest"};
		SecurityManager securityManager = System.getSecurityManager();
	    System.setSecurityManager(new NoExitSecurityManager());
	    try {
			Main.main(args);	    	
	    } catch (ExitException ee) {
			assertThat(ee.status, Is.is(0));
		}
	    System.setSecurityManager(securityManager);
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
		String[] args={"--count", "1", "--sources", "Main", "--tests", "MainTest Main1Test"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(0));
		assertThat(bean.getCount(), Is.is(1));
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
		String[] args={"--count", "2", "--sources", "Main Main1", "--tests", "MainTest"};
		int rc=m.parseArguments(args, bean);
		assertThat(rc, Is.is(0));
		assertThat(bean.getCount(), Is.is(2));
		assertThat(bean.getSources().size(), Is.is(2));
		assertThat(bean.getTests().size(), Is.is(1));
	}
}
