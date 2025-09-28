package com.github.gliptak.jallele.testIload;

/**
 * Test class that uses various iload instructions for integration testing
 */
public class ILoadTestClass {
    
    public int testILoad0() {
        int localVar = 42;
        return localVar; // Uses iload_1 (localVar at slot 1 after 'this' at slot 0)
    }
    
    public static int testILoadParam(int param) {
        return param; // Uses iload_0 to load param (in static method, param is at index 0)
    }
    
    public int testILoadLocal(int param1, int param2) {
        int local1 = param1; // iload_1 (param1) -> istore
        int local2 = param2; // iload_2 (param2) -> istore  
        if (local1 > 0) {    // iload for local1
            return local2;   // iload for local2
        }
        return local1;       // iload for local1
    }
    
    public int testILoadMultiple(int val1, int val2, int val3) {
        int result = val1;   // iload_1 -> istore
        if (result == 0) {   // iload
            result = val2;   // iload_2 -> istore
        }
        if (result == 0) {   // iload
            result = val3;   // iload_3 -> istore
        }
        return result;       // iload
    }
    
    public int testILoadArithmetic(int a, int b) {
        int sum = a + b;     // iload_1, iload_2, iadd -> istore
        int product = a * b; // iload_1, iload_2, imul -> istore
        return sum + product; // iload (sum), iload (product), iadd
    }
    
    // Method that should behave differently when iload is mutated to iconst_*
    public int testILoadConditional(int input) {
        if (input > 5) {          // iload_1 
            return input * 2;     // iload_1 
        } else if (input < 0) {   // iload_1
            return -input;        // iload_1, ineg
        }
        return input;             // iload_1
    }
    
    public boolean testILoadComparison(int x, int y) {
        return x == y;  // iload_1, iload_2, if_icmpne/if_icmpeq
    }
    
    public int testILoadLoop() {
        int counter = 0;          // iconst_0 -> istore
        for (int i = 0; i < 5; i++) { // iload, iconst_5, if_icmpge, iload, iconst_1, iadd, istore (iinc)
            counter += i;         // iload (counter), iload (i), iadd -> istore (counter)
        }
        return counter;           // iload (counter)
    }
}