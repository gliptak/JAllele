package com.github.gliptak.jallele.testDouble;

public class DConstDLoadTest {
	public double getConstantZero() {
		return 0.0; // This will generate dconst_0
	}

	public double getConstantOne() {
		return 1.0; // This will generate dconst_1
	}
	
	public double loadDoubleFromParameter(double value) {
		return value; // This will generate dload_1
	}
	
	public double loadDoubleFromLocal() {
		double localVar = 42.0; // Local variable assignment
		return localVar; // This will generate dload for the local variable
	}
	
	public double complexDoubleOperation() {
		double a = 0.0; // dconst_0
		double b = 1.0; // dconst_1
		double c = a + b; // Uses dload for a and b
		return c; // dload for c
	}
}