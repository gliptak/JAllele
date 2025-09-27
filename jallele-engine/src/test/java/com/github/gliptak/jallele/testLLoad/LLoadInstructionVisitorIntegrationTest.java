package com.github.gliptak.jallele.testLLoad;

import com.github.gliptak.jallele.Helper;
import org.junit.Test;

/**
 * Integration test for LLoadInstructionVisitor using actual bytecode mutation
 */
public class LLoadInstructionVisitorIntegrationTest {

    /**
     * Integration test that runs randomized mutations on LLoadTestClass
     * to verify that LLoadInstructionVisitor properly mutates lload instructions
     * and can detect failures when long variables are replaced with constants.
     */
    @Test
    public final void testLLoadMutationIntegration() throws Exception {
        Helper.runRandomized(
            "com.github.gliptak.jallele.testLLoad.LLoadTestClassTest",
            "com.github.gliptak.jallele.testLLoad.LLoadTestClass",
            10
        );
    }
}