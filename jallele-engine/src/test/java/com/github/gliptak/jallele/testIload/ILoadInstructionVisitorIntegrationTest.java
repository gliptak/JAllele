package com.github.gliptak.jallele.testIload;

import com.github.gliptak.jallele.Helper;
import org.junit.Test;

/**
 * Integration test for ILoadInstructionVisitor using actual bytecode mutation
 */
public class ILoadInstructionVisitorIntegrationTest {

    /**
     * Integration test that runs randomized mutations on ILoadTestClass
     * to verify that ILoadInstructionVisitor properly mutates iload instructions
     * and can detect failures when int variables are replaced with constant values.
     */
    @Test
    public final void testILoadMutationIntegration() throws Exception {
        Helper.runRandomized(
            "com.github.gliptak.jallele.testIload.ILoadTestClassTest",
            "com.github.gliptak.jallele.testIload.ILoadTestClass",
            10
        );
    }
}