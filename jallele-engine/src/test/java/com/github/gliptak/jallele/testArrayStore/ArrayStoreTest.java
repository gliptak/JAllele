package com.github.gliptak.jallele.testArrayStore;

public class ArrayStoreTest {
	
	public void storeIntInArray() {
		int[] array = new int[5];
		array[0] = 42; // This will generate iastore bytecode
		array[1] = 100;
		array[2] = array[0] + array[1];
	}
	
	public void storeLongInArray() {
		long[] array = new long[3];
		array[0] = 123456789L; // This will generate lastore bytecode
		array[1] = 987654321L;
		array[2] = array[0] + array[1];
	}
	
	public void storeFloatInArray() {
		float[] array = new float[3];
		array[0] = 3.14f; // This will generate fastore bytecode
		array[1] = 2.71f;
		array[2] = array[0] + array[1];
	}
	
	public void storeDoubleInArray() {
		double[] array = new double[3];
		array[0] = 3.14159; // This will generate dastore bytecode
		array[1] = 2.71828;
		array[2] = array[0] + array[1];
	}
	
	public void storeStringInArray() {
		String[] array = new String[3];
		array[0] = "Hello"; // This will generate aastore bytecode
		array[1] = "World";
		array[2] = array[0] + " " + array[1];
	}
	
	public void storeByteInArray() {
		byte[] array = new byte[3];
		array[0] = (byte) 0x42; // This will generate bastore bytecode
		array[1] = (byte) 0xFF;
		array[2] = (byte) (array[0] + array[1]);
	}
	
	public void storeCharInArray() {
		char[] array = new char[3];
		array[0] = 'A'; // This will generate castore bytecode
		array[1] = 'B';
		array[2] = (char) (array[0] + 1);
	}
	
	public void storeShortInArray() {
		short[] array = new short[3];
		array[0] = (short) 1000; // This will generate sastore bytecode
		array[1] = (short) 2000;
		array[2] = (short) (array[0] + array[1]);
	}
	
	public int[] returnIntArray() {
		int[] result = new int[2];
		result[0] = 10; // iastore
		result[1] = 20; // iastore
		return result;
	}
	
	public long[] returnLongArray() {
		long[] result = new long[2];
		result[0] = 123L; // lastore
		result[1] = 456L; // lastore
		return result;
	}
	
	public double[] returnDoubleArray() {
		double[] result = new double[2];
		result[0] = 1.5; // dastore
		result[1] = 2.5; // dastore
		return result;
	}
	
	public String[] returnStringArray() {
		String[] result = new String[2];
		result[0] = "first"; // aastore
		result[1] = "second"; // aastore
		return result;
	}
	
	public void complexArrayOperations() {
		// Mix different array store operations
		int[] ints = new int[2];
		ints[0] = 1; // iastore
		ints[1] = 2; // iastore
		
		long[] longs = new long[2];
		longs[0] = 100L; // lastore
		longs[1] = 200L; // lastore
		
		String[] strings = new String[2];
		strings[0] = "test"; // aastore
		strings[1] = "mutation"; // aastore
	}
}