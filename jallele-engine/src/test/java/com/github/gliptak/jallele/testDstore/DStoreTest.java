package com.github.gliptak.jallele.testDstore;

public class DStoreTest {
	public double storeDoubleToVariable(double value) {
		double localVar = value; // This will generate dstore for the local variable
		return localVar;
	}
	
	public double storeDoubleMultipleVariables(double a, double b) {
		double var0 = a; // This should generate dstore_0 or dstore (depending on local variable index)
		double var1 = b; // This should generate dstore_1 or dstore
		double var2 = a + b; // This should generate dstore_2 or dstore
		double var3 = a * b; // This should generate dstore_3 or dstore
		return var0 + var1 + var2 + var3;
	}
	
	public void storeDoubleWithNoReturn(double value) {
		double unused = value; // This will generate dstore and the value will be stored but not used
		// The mutation to POP2 should discard the value instead
	}
	
	public double complexStoreOperation() {
		double a = 10.0; // dconst, dstore
		double b = 20.0; // dconst, dstore  
		double c = a + b; // dload, dload, dadd, dstore
		double d = c * 2.0; // dload, dconst, dmul, dstore
		return d; // dload
	}
	
	public double storeParameterToLocal(double param1, double param2) {
		double local1 = param1; // dload_1, dstore (or dstore_0/1/2/3 depending on index)
		double local2 = param2; // dload_3, dstore (doubles take 2 slots)
		return local1 + local2;
	}
}