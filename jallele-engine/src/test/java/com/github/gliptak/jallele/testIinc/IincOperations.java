package com.github.gliptak.jallele.testIinc;

public class IincOperations {
	public int incrementByOne(int value) {
		// This generates IINC instruction with operand 1
		value++;
		return value;
	}
	
	public int incrementByFive(int value) {
		// This generates IINC instruction with operand 5
		value += 5;
		return value;
	}
	
	public int decrementByOne(int value) {
		// This generates IINC instruction with operand -1
		value--;
		return value;
	}
	
	public int incrementByTen(int value) {
		// This generates IINC instruction with operand 10
		value += 10;
		return value;
	}
	
	public int decrementByThree(int value) {
		// This generates IINC instruction with operand -3
		value -= 3;
		return value;
	}
}