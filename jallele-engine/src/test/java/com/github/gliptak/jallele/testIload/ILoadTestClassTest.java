package com.github.gliptak.jallele.testIload;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for ILoadTestClass to verify basic functionality
 */
public class ILoadTestClassTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testILoad0() {
        ILoadTestClass instance = new ILoadTestClass();
        int result = instance.testILoad0();
        assertThat(result, equalTo(42));
    }

    @Test
    public final void testILoadParam() {
        int input = 100;
        int result = ILoadTestClass.testILoadParam(input);
        assertThat(result, equalTo(input));
    }

    @Test
    public final void testILoadLocal() {
        ILoadTestClass instance = new ILoadTestClass();
        int result = instance.testILoadLocal(10, 20);
        assertThat(result, equalTo(20));
        
        // Test the other branch
        int result2 = instance.testILoadLocal(-5, 20);
        assertThat(result2, equalTo(-5));
    }

    @Test
    public final void testILoadMultiple() {
        ILoadTestClass instance = new ILoadTestClass();
        
        // Test first non-zero
        int result1 = instance.testILoadMultiple(10, 20, 30);
        assertThat(result1, equalTo(10));
        
        // Test second non-zero
        int result2 = instance.testILoadMultiple(0, 20, 30);
        assertThat(result2, equalTo(20));
        
        // Test third non-zero
        int result3 = instance.testILoadMultiple(0, 0, 30);
        assertThat(result3, equalTo(30));
        
        // Test all zero
        int result4 = instance.testILoadMultiple(0, 0, 0);
        assertThat(result4, equalTo(0));
    }

    @Test
    public final void testILoadArithmetic() {
        ILoadTestClass instance = new ILoadTestClass();
        int result = instance.testILoadArithmetic(3, 4);
        // sum = 3 + 4 = 7, product = 3 * 4 = 12, total = 7 + 12 = 19
        assertThat(result, equalTo(19));
    }

    @Test
    public final void testILoadConditional() {
        ILoadTestClass instance = new ILoadTestClass();
        
        // Test input > 5
        int result1 = instance.testILoadConditional(10);
        assertThat(result1, equalTo(20));
        
        // Test input < 0
        int result2 = instance.testILoadConditional(-3);
        assertThat(result2, equalTo(3));
        
        // Test 0 <= input <= 5
        int result3 = instance.testILoadConditional(3);
        assertThat(result3, equalTo(3));
    }

    @Test
    public final void testILoadComparison() {
        ILoadTestClass instance = new ILoadTestClass();
        
        boolean result1 = instance.testILoadComparison(5, 5);
        assertThat(result1, equalTo(true));
        
        boolean result2 = instance.testILoadComparison(5, 10);
        assertThat(result2, equalTo(false));
    }

    @Test
    public final void testILoadLoop() {
        ILoadTestClass instance = new ILoadTestClass();
        int result = instance.testILoadLoop();
        // Loop: i = 0,1,2,3,4 -> counter = 0+0+1+2+3+4 = 10
        assertThat(result, equalTo(10));
    }
}