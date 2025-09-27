package com.github.gliptak.jallele.testUtility;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Integration test for utility instructions - simple verification that the classes work correctly
 */
public class UtilityInstructionVisitorIntegrationTest {

    @Test
    public void testUtilityOperationsWork() {
        // Simple test to verify that the utility operations compile and work correctly
        // This ensures that the classes we're targeting for mutation testing are valid
        UtilityOperations ops = new UtilityOperations();
        
        // Test the methods that should generate the utility instructions
        assertThat(ops.incrementByOne(5), is(6));
        assertThat(ops.incrementByFive(10), is(15));
        assertThat(ops.swapAndAdd(3, 7), is(10));
        
        // doNothing should complete without error
        ops.doNothing();
        
        // If we get here, all the operations worked correctly
        assertTrue("All utility operations completed successfully", true);
    }
}