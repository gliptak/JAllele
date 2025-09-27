package com.github.gliptak.jallele.testAload;

import com.github.gliptak.jallele.Helper;
import org.junit.Test;

/**
 * Integration test for ALoadInstructionVisitor using actual bytecode mutation
 */
public class ALoadInstructionVisitorIntegrationTest {

    /**
     * Integration test that runs randomized mutations on ALoadTestClass
     * to verify that ALoadInstructionVisitor properly mutates aload instructions
     * and can detect failures when object references are replaced with null.
     */
    @Test
    public final void testALoadMutationIntegration() throws Exception {
        Helper.runRandomized(
            "com.github.gliptak.jallele.testAload.ALoadTestClassTest",
            "com.github.gliptak.jallele.testAload.ALoadTestClass",
            10
        );
    }
}