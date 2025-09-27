package com.github.gliptak.jallele.testFloat;

public class FConstFLoadTest {
	public float getConstantZero() {
		return 0.0f; // This will generate fconst_0
	}

	public float getConstantOne() {
		return 1.0f; // This will generate fconst_1
	}

	public float getConstantTwo() {
		return 2.0f; // This will generate fconst_2
	}
	
	public float loadFloatFromParameter(float value) {
		return value; // This will generate fload_1
	}
	
	public float loadFloatFromLocal() {
		float localVar = 42.0f; // Local variable assignment
		return localVar; // This will generate fload for the local variable
	}
	
	public float complexFloatOperation() {
		float a = 0.0f; // fconst_0
		float b = 1.0f; // fconst_1
		float c = 2.0f; // fconst_2
		float d = a + b + c; // Uses fload for a, b, and c
		return d; // fload for d
	}
}