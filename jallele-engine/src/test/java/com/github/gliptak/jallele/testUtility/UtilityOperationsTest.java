package com.github.gliptak.jallele.testUtility;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UtilityOperationsTest {

    @Test
    public void testIncrementByOne() {
        UtilityOperations ops = new UtilityOperations();
        assertThat(ops.incrementByOne(5), is(6));
        assertThat(ops.incrementByOne(0), is(1));
        assertThat(ops.incrementByOne(-1), is(0));
    }

    @Test
    public void testIncrementByFive() {
        UtilityOperations ops = new UtilityOperations();
        assertThat(ops.incrementByFive(10), is(15));
        assertThat(ops.incrementByFive(0), is(5));
        assertThat(ops.incrementByFive(-5), is(0));
    }
    
    @Test
    public void testDoNothing() {
        UtilityOperations ops = new UtilityOperations();
        // Should not throw exception and complete successfully
        ops.doNothing();
    }
    
    @Test
    public void testSwapAndAdd() {
        UtilityOperations ops = new UtilityOperations();
        assertThat(ops.swapAndAdd(3, 7), is(10));
        assertThat(ops.swapAndAdd(0, 5), is(5));
        assertThat(ops.swapAndAdd(-2, 8), is(6));
    }
}