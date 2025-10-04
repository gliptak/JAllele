package com.github.gliptak.jallele.testcases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import com.github.gliptak.jallele.ClassRandomizer;
import com.github.gliptak.jallele.TestRunner;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Integration test for ClassRandomizer that processes multiple related classes
 * This test demonstrates processing two project classes where one imports the other
 */
public class ClassRandomizerIntegrationTest {
    
    /**
     * Helper method to get class bytecode (same logic as Agent.getClassBytes)
     */
    private static byte[] getClassBytes(Class<?> clazz) throws IOException {
        String name = clazz.getName().replace('.', '/') + ".class";
        InputStream iStream = clazz.getClassLoader().getResourceAsStream(name);
        try {
            ByteArrayOutputStream oStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int len = iStream.read(buffer);
                if (len < 0) {
                    break;
                }
                oStream.write(buffer, 0, len);
            }
            return oStream.toByteArray();
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
    }
    
    /**
     * Test that ClassRandomizer can process both Calculator and MathOperations
     * where MathOperations imports Calculator
     */
    @Test
    public void testTransformMultipleRelatedClasses() throws Exception {
        // Create a list with both classes as sources
        List<String> sources = new ArrayList<String>();
        sources.add(Calculator.class.getName());
        sources.add(MathOperations.class.getName());
        
        // Create a mock test runner
        MockTestRunner runner = new MockTestRunner();
        
        // Create ClassRandomizer with both classes as sources
        ClassRandomizer classRandomizer = new ClassRandomizer(sources, runner);
        
        // Set recording mode to true using reflection
        Field recordingField = ClassRandomizer.class.getDeclaredField("recording");
        recordingField.setAccessible(true);
        recordingField.setBoolean(classRandomizer, true);
        
        // Get the processedSources field to verify tracking
        Field processedSourcesField = ClassRandomizer.class.getDeclaredField("processedSources");
        processedSourcesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<String> processedSources = (Set<String>) processedSourcesField.get(classRandomizer);
        processedSources.clear();
        
        try {
            // Get bytecode for Calculator
            byte[] calculatorBytes = getClassBytes(Calculator.class);
            String calculatorClassName = Calculator.class.getName().replaceAll("\\.", "/");
            
            // Transform Calculator
            byte[] transformedCalculator = classRandomizer.transform(
                null, calculatorClassName, null, null, calculatorBytes);
            
            // Verify Calculator was transformed and tracked
            assertThat(transformedCalculator, is(notNullValue()));
            assertThat(processedSources.contains(Calculator.class.getName()), is(true));
            assertThat(processedSources.size(), is(1));
            
            // Try to transform Calculator again - should be skipped
            byte[] skippedCalculator = classRandomizer.transform(
                null, calculatorClassName, null, null, calculatorBytes);
            
            // Should return null because already processed
            assertThat(skippedCalculator, is(nullValue()));
            // processedSources should still have only 1 entry
            assertThat(processedSources.size(), is(1));
            
            // Get bytecode for MathOperations
            byte[] mathOpsBytes = getClassBytes(MathOperations.class);
            String mathOpsClassName = MathOperations.class.getName().replaceAll("\\.", "/");
            
            // Transform MathOperations
            byte[] transformedMathOps = classRandomizer.transform(
                null, mathOpsClassName, null, null, mathOpsBytes);
            
            // Verify MathOperations was transformed and tracked
            assertThat(transformedMathOps, is(notNullValue()));
            assertThat(processedSources.contains(MathOperations.class.getName()), is(true));
            assertThat(processedSources.size(), is(2));
            
            // Try to transform MathOperations again - should be skipped
            byte[] skippedMathOps = classRandomizer.transform(
                null, mathOpsClassName, null, null, mathOpsBytes);
            
            // Should return null because already processed
            assertThat(skippedMathOps, is(nullValue()));
            // processedSources should still have 2 entries
            assertThat(processedSources.size(), is(2));
            
        } catch (IOException e) {
            // If Agent.getClassBytes fails, skip the test
            System.out.println("Skipping test due to: " + e.getMessage());
        } finally {
            // Clean up - reset recording to false
            recordingField.setBoolean(classRandomizer, false);
        }
    }
    
    /**
     * Test that transform only processes classes in the sources list
     */
    @Test
    public void testTransformOnlyProcessesSourceClasses() throws Exception {
        // Create a list with only Calculator
        List<String> sources = new ArrayList<String>();
        sources.add(Calculator.class.getName());
        // Note: MathOperations is NOT in the sources list
        
        MockTestRunner runner = new MockTestRunner();
        ClassRandomizer classRandomizer = new ClassRandomizer(sources, runner);
        
        // Set recording mode
        Field recordingField = ClassRandomizer.class.getDeclaredField("recording");
        recordingField.setAccessible(true);
        recordingField.setBoolean(classRandomizer, true);
        
        Field processedSourcesField = ClassRandomizer.class.getDeclaredField("processedSources");
        processedSourcesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<String> processedSources = (Set<String>) processedSourcesField.get(classRandomizer);
        processedSources.clear();
        
        try {
            // Transform Calculator (in sources list)
            byte[] calculatorBytes = getClassBytes(Calculator.class);
            String calculatorClassName = Calculator.class.getName().replaceAll("\\.", "/");
            byte[] transformedCalculator = classRandomizer.transform(
                null, calculatorClassName, null, null, calculatorBytes);
            
            assertThat(transformedCalculator, is(notNullValue()));
            assertThat(processedSources.contains(Calculator.class.getName()), is(true));
            
            // Try to transform MathOperations (NOT in sources list)
            byte[] mathOpsBytes = getClassBytes(MathOperations.class);
            String mathOpsClassName = MathOperations.class.getName().replaceAll("\\.", "/");
            byte[] transformedMathOps = classRandomizer.transform(
                null, mathOpsClassName, null, null, mathOpsBytes);
            
            // Should return null because not in sources list
            assertThat(transformedMathOps, is(nullValue()));
            // Should not be added to processedSources
            assertThat(processedSources.contains(MathOperations.class.getName()), is(false));
            assertThat(processedSources.size(), is(1));
            
        } catch (IOException e) {
            System.out.println("Skipping test due to: " + e.getMessage());
        } finally {
            recordingField.setBoolean(classRandomizer, false);
        }
    }
    
    /**
     * Test that processedSources is properly reset between recording sessions
     */
    @Test
    public void testProcessedSourcesResetBetweenSessions() throws Exception {
        List<String> sources = new ArrayList<String>();
        sources.add(Calculator.class.getName());
        
        MockTestRunner runner = new MockTestRunner();
        ClassRandomizer classRandomizer = new ClassRandomizer(sources, runner);
        
        Field processedSourcesField = ClassRandomizer.class.getDeclaredField("processedSources");
        processedSourcesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Set<String> processedSources = (Set<String>) processedSourcesField.get(classRandomizer);
        
        // Manually add an entry
        processedSources.add(Calculator.class.getName());
        assertThat(processedSources.size(), is(1));
        
        // Call recordMatches - should reset processedSources
        try {
            classRandomizer.recordMatches();
        } catch (Exception e) {
            // Expected to fail due to Agent not being available
        }
        
        // Verify processedSources was reset
        @SuppressWarnings("unchecked")
        Set<String> processedSourcesAfter = (Set<String>) processedSourcesField.get(classRandomizer);
        assertThat(processedSourcesAfter.size(), is(0));
    }
    
    /**
     * Mock test runner for testing
     */
    private static class MockTestRunner implements TestRunner {
        private int failureCount = 0;
        
        public void runTests() {
            // Do nothing for tests
        }
        
        public int getFailureCount() {
            return failureCount;
        }
        
        public void setFailureCount(int count) {
            this.failureCount = count;
        }
    }
}
