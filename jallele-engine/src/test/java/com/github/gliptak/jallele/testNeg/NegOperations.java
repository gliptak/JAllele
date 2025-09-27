package com.github.gliptak.jallele.testNeg;

public class NegOperations {
	public double negateDouble(double d){
		return -d;
	}
	
	public float negateFloat(float f){
		return -f;
	}
	
	public int negateInt(int i){
		return -i;
	}
	
	public long negateLong(long l){
		return -l;
	}
	
	// Helper method that uses negation in a more complex context
	public double absoluteDifference(double a, double b) {
		return Math.abs(a - b);
	}
	
	// Method that combines negation with arithmetic
	public int computeBalance(int credits, int debits) {
		return credits + (-debits); // Using explicit negation
	}
	
	// Method using negation in conditional logic
	public boolean isNegative(float value) {
		return value < 0.0f && -value > 0.0f;
	}
}