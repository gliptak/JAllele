package com.github.gliptak.jallele.testAload;

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
 * Test class for ALoadTestClass to verify basic functionality
 */
public class ALoadTestClassTest {

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
    public final void testALoad0() {
        ALoadTestClass instance = new ALoadTestClass();
        String result = instance.testALoad0();
        assertThat(result, equalTo("test"));
    }

    @Test
    public final void testALoadParam() {
        String input = "hello";
        Object result = ALoadTestClass.testALoadParam(input);
        assertThat(result, equalTo(input));
    }

    @Test
    public final void testALoadLocal() {
        ALoadTestClass instance = new ALoadTestClass();
        String result = instance.testALoadLocal("first", "second");
        assertThat(result, equalTo("second"));
        
        // Test the other branch
        String result2 = instance.testALoadLocal(null, "second");
        assertThat(result2, Is.is((String)null));
    }

    @Test
    public final void testALoadMultiple() {
        ALoadTestClass instance = new ALoadTestClass();
        String obj1 = "first";
        String obj2 = "second";
        String obj3 = "third";
        
        // Test first non-null
        Object result1 = instance.testALoadMultiple(obj1, obj2, obj3);
        assertThat(result1, equalTo(obj1));
        
        // Test second non-null
        Object result2 = instance.testALoadMultiple(null, obj2, obj3);
        assertThat(result2, equalTo(obj2));
        
        // Test third non-null
        Object result3 = instance.testALoadMultiple(null, null, obj3);
        assertThat(result3, equalTo(obj3));
        
        // Test all null
        Object result4 = instance.testALoadMultiple(null, null, null);
        assertThat(result4, Is.is((Object)null));
    }

    @Test
    public final void testALoadArray() {
        ALoadTestClass instance = new ALoadTestClass();
        String[] array = {"first", "second"};
        String result = instance.testALoadArray(array);
        assertThat(result, equalTo("first"));
        
        // Test null array
        String result2 = instance.testALoadArray(null);
        assertThat(result2, Is.is((String)null));
        
        // Test empty array
        String result3 = instance.testALoadArray(new String[0]);
        assertThat(result3, Is.is((String)null));
    }

    @Test
    public final void testNonNullReturn() {
        ALoadTestClass instance = new ALoadTestClass();
        String result = instance.testNonNullReturn("hello");
        assertThat(result, equalTo("HELLO"));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testNonNullReturnWithNull() {
        ALoadTestClass instance = new ALoadTestClass();
        instance.testNonNullReturn(null);
    }
}