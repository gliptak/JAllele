package com.github.gliptak.jallele.testcases;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Calculator class
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @Before
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    public void testAdd() {
        assertThat(calculator.add(2, 3), is(5));
        assertThat(calculator.add(-1, 1), is(0));
        assertThat(calculator.add(0, 0), is(0));
    }
    
    @Test
    public void testSubtract() {
        assertThat(calculator.subtract(5, 3), is(2));
        assertThat(calculator.subtract(1, 1), is(0));
        assertThat(calculator.subtract(0, 5), is(-5));
    }
    
    @Test
    public void testMultiply() {
        assertThat(calculator.multiply(3, 4), is(12));
        assertThat(calculator.multiply(0, 100), is(0));
        assertThat(calculator.multiply(-2, 3), is(-6));
    }
    
    @Test
    public void testIsPositive() {
        assertTrue(calculator.isPositive(1));
        assertTrue(calculator.isPositive(100));
        assertFalse(calculator.isPositive(0));
        assertFalse(calculator.isPositive(-1));
    }
    
    @Test
    public void testGetConstant() {
        assertThat(calculator.getConstant(), is(42));
    }
}
