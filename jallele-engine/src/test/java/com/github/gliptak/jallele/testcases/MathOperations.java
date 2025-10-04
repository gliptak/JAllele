package com.github.gliptak.jallele.testcases;

/**
 * Math operations class that uses Calculator
 * This demonstrates one project class importing another
 */
public class MathOperations {
    
    private Calculator calculator;
    
    public MathOperations() {
        this.calculator = new Calculator();
    }
    
    public int performAddition(int x, int y) {
        return calculator.add(x, y);
    }
    
    public int performSubtraction(int x, int y) {
        return calculator.subtract(x, y);
    }
    
    public int square(int n) {
        return calculator.multiply(n, n);
    }
    
    public boolean isResultPositive(int a, int b) {
        int result = calculator.add(a, b);
        return calculator.isPositive(result);
    }
    
    public int computeExpression(int a, int b, int c) {
        // (a + b) * c
        int sum = calculator.add(a, b);
        return calculator.multiply(sum, c);
    }
}
