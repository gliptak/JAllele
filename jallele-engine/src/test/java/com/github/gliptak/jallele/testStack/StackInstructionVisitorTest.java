package com.github.gliptak.jallele.testStack;

import com.github.gliptak.jallele.Helper;
import org.junit.Test;

public class StackInstructionVisitorTest {
    @Test
    public void testRunIStore() throws Exception{
        Helper.runRandomized(
                "com.github.gliptak.jallele.testStack.IStoreTest",
                "com.github.gliptak.jallele.testStack.IStore",
                10
        );
    }

}
