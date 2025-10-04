/**
 * Test to verify logging functionality in instruction visitors
 */
package com.github.gliptak.jallele.spi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gliptak
 * Test class to verify that logging is working correctly in all instruction visitors
 */
public class LoggingTest {

	private List<LogRecord> logRecords;
	private Handler testHandler;
	
	@Before
	public void setUp() throws Exception {
		// Create a custom handler to capture log records
		logRecords = new ArrayList<>();
		testHandler = new Handler() {
			@Override
			public void publish(LogRecord record) {
				logRecords.add(record);
			}
			
			@Override
			public void flush() {
			}
			
			@Override
			public void close() throws SecurityException {
			}
		};
		testHandler.setLevel(Level.FINE);
	}
	
	@After
	public void tearDown() throws Exception {
		if (testHandler != null) {
			testHandler.close();
		}
	}
	
	/**
	 * Test method to verify FStoreInstructionVisitor logs transformation
	 */
	@Test
	public final void testFStoreInstructionVisitorLogging() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()F", 10, 1);
		vs.setOpCode(Opcodes.FSTORE);
		
		Random random = new Random();
		FStoreInstructionVisitor visitor = new FStoreInstructionVisitor(random);
		
		// Set up logging
		Logger logger = Logger.getLogger(FStoreInstructionVisitor.class.getName());
		logger.addHandler(testHandler);
		logger.setLevel(Level.FINE);
		
		// Execute transformation
		VisitStatus newVs = visitor.isMatch(vs);
		
		// Verify transformation occurred
		assertThat(newVs.getOpCode(), is(Opcodes.POP));
		
		// Verify logging occurred
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINE));
		assertThat(record.getMessage(), containsString("Transform:"));
		assertThat(record.getMessage(), containsString("FSTORE"));
		assertThat(record.getMessage(), containsString("POP"));
		
		logger.removeHandler(testHandler);
	}
	
	/**
	 * Test method to verify IfInstructionVisitor logs transformation
	 */
	@Test
	public final void testIfInstructionVisitorLogging() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()V", 10, 1);
		vs.setOpCode(Opcodes.IFEQ);
		
		Random random = new Random();
		IfInstructionVisitor visitor = new IfInstructionVisitor(random);
		
		// Set up logging
		Logger logger = Logger.getLogger(IfInstructionVisitor.class.getName());
		logger.addHandler(testHandler);
		logger.setLevel(Level.FINE);
		
		// Execute transformation
		VisitStatus newVs = visitor.isMatch(vs);
		
		// Verify transformation occurred
		assertThat(newVs.getOpCode(), is(Opcodes.IFNE));
		
		// Verify logging occurred
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINE));
		assertThat(record.getMessage(), containsString("Transform:"));
		assertThat(record.getMessage(), containsString("IFEQ"));
		assertThat(record.getMessage(), containsString("IFNE"));
		
		logger.removeHandler(testHandler);
	}
	
	/**
	 * Test method to verify IntegerOpInstructionVisitor logs transformation
	 */
	@Test
	public final void testIntegerOpInstructionVisitorLogging() {
		VisitStatus vs = new VisitStatus("TestClass", "testMethod", "()I", 10, 1);
		vs.setOpCode(Opcodes.IADD);
		
		Random random = new Random();
		IntegerOpInstructionVisitor visitor = new IntegerOpInstructionVisitor(random);
		
		// Set up logging
		Logger logger = Logger.getLogger(IntegerOpInstructionVisitor.class.getName());
		logger.addHandler(testHandler);
		logger.setLevel(Level.FINE);
		
		// Execute transformation
		VisitStatus newVs = visitor.isMatch(vs);
		
		// Verify transformation occurred (should be different from IADD)
		assertThat(newVs.getOpCode(), is(not(Opcodes.IADD)));
		
		// Verify logging occurred
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINE));
		assertThat(record.getMessage(), containsString("Transform:"));
		assertThat(record.getMessage(), containsString("IADD"));
		
		logger.removeHandler(testHandler);
	}
}
