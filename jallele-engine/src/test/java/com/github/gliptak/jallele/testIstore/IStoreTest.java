package com.github.gliptak.jallele.testIstore;

public class IStoreTest {
	public int storeIntToVariable(int value) {
		int localVar = value; // This will generate istore for the local variable
		return localVar;
	}
	
	public int storeIntMultipleVariables(int a, int b) {
		int var0 = a; // This should generate istore_0 or istore (depending on local variable index)
		int var1 = b; // This should generate istore_1 or istore
		int var2 = a + b; // This should generate istore_2 or istore
		int var3 = a * b; // This should generate istore_3 or istore
		return var0 + var1 + var2 + var3;
	}
	
	public void storeIntWithNoReturn(int value) {
		int unused = value; // This will generate istore and the value will be stored but not used
		// The mutation to POP should discard the value instead
	}
	
	public int complexStoreOperation() {
		int a = 10; // iconst, istore
		int b = 20; // iconst, istore  
		int c = a + b; // iload, iload, iadd, istore
		int d = c * 2; // iload, iconst, imul, istore
		return d; // iload
	}
	
	public int storeParameterToLocal(int param1, int param2) {
		int local1 = param1; // iload_1, istore (or istore_0/1/2/3 depending on index)
		int local2 = param2; // iload_2, istore
		return local1 + local2;
	}
}