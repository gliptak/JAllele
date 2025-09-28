package com.github.gliptak.jallele.testAstore;

public class AStoreTest {
	public String storeStringToVariable(String value) {
		String localVar = value; // This will generate astore for the local variable
		return localVar;
	}
	
	public String storeStringMultipleVariables(String a, String b) {
		String var0 = a; // This should generate astore_0 or astore (depending on local variable index)
		String var1 = b; // This should generate astore_1 or astore
		String var2 = a + b; // This should generate astore_2 or astore
		String var3 = a + b + "!"; // This should generate astore_3 or astore
		return var0 + var1 + var2 + var3;
	}
	
	public void storeStringWithNoReturn(String value) {
		String unused = value; // This will generate astore and the value will be stored but not used
		// The mutation to POP should discard the value instead
	}
	
	public String complexStoreOperation() {
		String a = "Hello"; // ldc, astore
		String b = "World"; // ldc, astore  
		String c = a + " " + b; // string concatenation, astore
		String d = c + "!"; // string concatenation, astore
		return d; // aload
	}
	
	public String storeParameterToLocal(String param1, String param2) {
		String local1 = param1; // aload_1, astore (or astore_0/1/2/3 depending on index)
		String local2 = param2; // aload_2, astore
		return local1 + local2;
	}
	
	public Object[] storeArrayToVariable(Object[] value) {
		Object[] localVar = value; // This will generate astore for the array reference
		return localVar;
	}
}