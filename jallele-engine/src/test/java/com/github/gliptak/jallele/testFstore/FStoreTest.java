package com.github.gliptak.jallele.testFstore;

public class FStoreTest {
	public float storeFloatToVariable(float value) {
		float localVar = value; // This will generate fstore for the local variable
		return localVar;
	}
	
	public float storeFloatMultipleVariables(float a, float b) {
		float var0 = a; // This should generate fstore_0 or fstore (depending on local variable index)
		float var1 = b; // This should generate fstore_1 or fstore
		float var2 = a + b; // This should generate fstore_2 or fstore
		float var3 = a * b; // This should generate fstore_3 or fstore
		return var0 + var1 + var2 + var3;
	}
	
	public void storeFloatWithNoReturn(float value) {
		float unused = value; // This will generate fstore and the value will be stored but not used
		// The mutation to POP should discard the value instead
	}
	
	public float complexStoreOperation() {
		float a = 10.0f; // fconst, fstore
		float b = 20.0f; // fconst, fstore  
		float c = a + b; // fload, fload, fadd, fstore
		float d = c * 2.0f; // fload, fconst, fmul, fstore
		return d; // fload
	}
	
	public float storeParameterToLocal(float param1, float param2) {
		float local1 = param1; // fload_1, fstore (or fstore_0/1/2/3 depending on index)
		float local2 = param2; // fload_2, fstore
		return local1 + local2;
	}
}