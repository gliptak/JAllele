package com.github.gliptak.jallele.testLong;

public class LConstLLoadTest {
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
}