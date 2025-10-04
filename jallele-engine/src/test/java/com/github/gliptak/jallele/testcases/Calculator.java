package com.github.gliptak.jallele.testcases;

/**
 * Simple calculator class for testing bytecode transformation
 * This class is designed to be simple enough for ASM5 to process
 */
public class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public boolean isPositive(int value) {
        return value > 0;
    }
    
    public int getConstant() {
        return 42;
    }
}
