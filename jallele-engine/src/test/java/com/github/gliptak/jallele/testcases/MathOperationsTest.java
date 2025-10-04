package com.github.gliptak.jallele.testcases;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for MathOperations class
 */
public class MathOperationsTest {
    
    private MathOperations mathOps;
    
    @Before
    public void setUp() {
        mathOps = new MathOperations();
    }
    
    @Test
    public void testPerformAddition() {
        assertThat(mathOps.performAddition(5, 3), is(8));
        assertThat(mathOps.performAddition(-2, 2), is(0));
    }
    
    @Test
    public void testPerformSubtraction() {
        assertThat(mathOps.performSubtraction(10, 4), is(6));
        assertThat(mathOps.performSubtraction(3, 5), is(-2));
    }
    
    @Test
    public void testSquare() {
        assertThat(mathOps.square(5), is(25));
        assertThat(mathOps.square(0), is(0));
        assertThat(mathOps.square(-3), is(9));
    }
    
    @Test
    public void testIsResultPositive() {
        assertTrue(mathOps.isResultPositive(3, 2));
        assertTrue(mathOps.isResultPositive(5, 1));
        assertFalse(mathOps.isResultPositive(-5, 2));
        assertFalse(mathOps.isResultPositive(-3, -3));
    }
    
    @Test
    public void testComputeExpression() {
        // (2 + 3) * 4 = 20
        assertThat(mathOps.computeExpression(2, 3, 4), is(20));
        // (1 + 1) * 5 = 10
        assertThat(mathOps.computeExpression(1, 1, 5), is(10));
        // (0 + 0) * 10 = 0
        assertThat(mathOps.computeExpression(0, 0, 10), is(0));
    }
}
