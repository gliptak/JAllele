package com.github.gliptak.jallele.testAload;

/**
 * Test class that uses various aload instructions for integration testing
 */
public class ALoadTestClass {
    
    private String field = "test";
    
    public String testALoad0() {
        return this.field; // Uses aload_0 to load 'this'
    }
    
    public static Object testALoadParam(String param) {
        return param; // Uses aload_0 to load param (in static method, param is at index 0)
    }
    
    public String testALoadLocal(String param1, String param2) {
        String local1 = param1; // aload_1 (param1) -> astore
        String local2 = param2; // aload_2 (param2) -> astore  
        if (local1 != null) {   // aload for local1
            return local2;      // aload for local2
        }
        return local1;          // aload for local1
    }
    
    public Object testALoadMultiple(Object obj1, Object obj2, Object obj3) {
        Object result = obj1;   // aload_1 -> astore
        if (result == null) {   // aload
            result = obj2;      // aload_2 -> astore
        }
        if (result == null) {   // aload
            result = obj3;      // aload_3 -> astore
        }
        return result;          // aload
    }
    
    public String testALoadArray(String[] array) {
        if (array != null && array.length > 0) {  // aload_1 for array
            return array[0];                       // aload_1 for array access
        }
        return null;
    }
    
    // Method that should fail when aload is mutated to aconst_null
    public String testNonNullReturn(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return input.toUpperCase(); // aload_1 -> if mutated to null, will cause NPE
    }
}