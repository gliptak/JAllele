package com.github.gliptak.jallele.testLstore;

public class LStoreTest {
	public long storeLongToVariable(long value) {
		long localVar = value; // This will generate lstore for the local variable
		return localVar;
	}
	
	public long storeLongMultipleVariables(long a, long b) {
		long var0 = a; // This should generate lstore_0 or lstore (depending on local variable index)
		long var1 = b; // This should generate lstore_2 or lstore (longs take 2 slots)
		long var2 = a + b; // This should generate lstore or lstore_0/1/2/3
		long var3 = a * b; // This should generate lstore
		return var0 + var1 + var2 + var3;
	}
	
	public void storeLongWithNoReturn(long value) {
		long unused = value; // This will generate lstore and the value will be stored but not used
		// The mutation to POP2 should discard the value instead
	}
	
	public long complexStoreOperation() {
		long a = 10L; // lconst, lstore
		long b = 20L; // lconst, lstore  
		long c = a + b; // lload, lload, ladd, lstore
		long d = c * 2L; // lload, lconst, lmul, lstore
		return d; // lload
	}
	
	public long storeParameterToLocal(long param1, long param2) {
		long local1 = param1; // lload_1, lstore (longs take 2 slots)
		long local2 = param2; // lload_3, lstore
		return local1 + local2;
	}
}