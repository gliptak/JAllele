package com.github.gliptak.jallele.testLLoad;

public class LLoadTestClass {
	public long getConstantZero() {
		return 0L; // This will generate lconst_0
	}

	public long getConstantOne() {
		return 1L; // This will generate lconst_1
	}
	
	public long loadLongFromParameter(long value) {
		return value; // This will generate lload_1
	}
	
	public long loadLongFromLocal() {
		long localVar = 42L; // Local variable assignment
		return localVar; // This will generate lload for the local variable
	}
	
	public long complexLongOperation() {
		long a = 0L; // lconst_0
		long b = 1L; // lconst_1
		long c = a + b; // Uses lload for a and b
		return c; // lload for c
	}
	
	public long multipleParameters(long param1, long param2, long param3) {
		// This will use lload_1, lload_3, lload for different parameter slots
		// (param2 takes slots 3-4 since longs are 2 slots, param3 starts at slot 5)
		return param1 + param2 + param3;
	}
	
	public long testLongWithLocalVariables() {
		long var1 = 100L;   // Local slot assignment
		long var2 = 200L;   // Local slot assignment  
		long var3 = 300L;   // Local slot assignment
		// These loads will use lload_0, lload_2, lload for different local slots
		return var1 + var2 + var3;
	}
}