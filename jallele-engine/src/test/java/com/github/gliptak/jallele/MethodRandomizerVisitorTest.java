/**
 * Test to verify logging functionality in MethodRandomizerVisitor
 */
package com.github.gliptak.jallele;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gliptak
 * Test class to verify that logging is working correctly in MethodRandomizerVisitor
 */
public class MethodRandomizerVisitorTest {

	private List<LogRecord> logRecords;
	private Handler testHandler;
	private Logger logger;
	
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
		testHandler.setLevel(Level.FINER);
		
		// Set up logger
		logger = Logger.getLogger(MethodRandomizerVisitor.class.getName());
		logger.addHandler(testHandler);
		logger.setLevel(Level.FINER);
	}
	
	@After
	public void tearDown() throws Exception {
		if (logger != null && testHandler != null) {
			logger.removeHandler(testHandler);
		}
		if (testHandler != null) {
			testHandler.close();
		}
	}
	
	/**
	 * Test method to verify visitInsn logs with human-readable opcode names
	 */
	@Test
	public final void testVisitInsnLogsHumanReadableNames() {
		TestRunner runner = new EngineJUnit4Runner(new ArrayList<String>());
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>(), runner);
		MethodVisitor mv = new MethodVisitor(Opcodes.ASM5) {};
		MethodRandomizerVisitor visitor = new MethodRandomizerVisitor(
			"TestClass", "testMethod", "()V", mv, cr);
		
		// Execute visitInsn with RETURN opcode
		visitor.visitInsn(Opcodes.RETURN);
		
		// Verify logging occurred with human-readable name
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINER));
		assertThat(record.getMessage(), containsString("visitInsn"));
		assertThat(record.getMessage(), containsString("RETURN"));
	}
	
	/**
	 * Test method to verify visitIntInsn logs with human-readable opcode names
	 */
	@Test
	public final void testVisitIntInsnLogsHumanReadableNames() {
		TestRunner runner = new EngineJUnit4Runner(new ArrayList<String>());
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>(), runner);
		MethodVisitor mv = new MethodVisitor(Opcodes.ASM5) {};
		MethodRandomizerVisitor visitor = new MethodRandomizerVisitor(
			"TestClass", "testMethod", "()V", mv, cr);
		
		// Execute visitIntInsn with BIPUSH opcode
		visitor.visitIntInsn(Opcodes.BIPUSH, 10);
		
		// Verify logging occurred with human-readable name
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINER));
		assertThat(record.getMessage(), containsString("visitIntInsn"));
		assertThat(record.getMessage(), containsString("BIPUSH"));
		assertThat(record.getMessage(), containsString("10"));
	}
	
	/**
	 * Test method to verify visitJumpInsn logs with human-readable opcode names
	 */
	@Test
	public final void testVisitJumpInsnLogsHumanReadableNames() {
		TestRunner runner = new EngineJUnit4Runner(new ArrayList<String>());
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>(), runner);
		MethodVisitor mv = new MethodVisitor(Opcodes.ASM5) {};
		MethodRandomizerVisitor visitor = new MethodRandomizerVisitor(
			"TestClass", "testMethod", "()V", mv, cr);
		
		Label label = new Label();
		// Execute visitJumpInsn with IFEQ opcode
		visitor.visitJumpInsn(Opcodes.IFEQ, label);
		
		// Verify logging occurred with human-readable name
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINER));
		assertThat(record.getMessage(), containsString("visitJumpInsn"));
		assertThat(record.getMessage(), containsString("IFEQ"));
	}
	
	/**
	 * Test method to verify visitVarInsn logs with human-readable opcode names
	 */
	@Test
	public final void testVisitVarInsnLogsHumanReadableNames() {
		TestRunner runner = new EngineJUnit4Runner(new ArrayList<String>());
		ClassRandomizer cr = new ClassRandomizer(new ArrayList<String>(), runner);
		MethodVisitor mv = new MethodVisitor(Opcodes.ASM5) {};
		MethodRandomizerVisitor visitor = new MethodRandomizerVisitor(
			"TestClass", "testMethod", "()V", mv, cr);
		
		// Execute visitVarInsn with ILOAD opcode
		visitor.visitVarInsn(Opcodes.ILOAD, 1);
		
		// Verify logging occurred with human-readable name
		assertThat(logRecords.size(), is(1));
		LogRecord record = logRecords.get(0);
		assertThat(record.getLevel(), is(Level.FINER));
		assertThat(record.getMessage(), containsString("visitVarInsn"));
		assertThat(record.getMessage(), containsString("ILOAD"));
		assertThat(record.getMessage(), containsString("1"));
	}
}
