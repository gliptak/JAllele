package com.github.gliptak.jallele.testLong;

public class LongShiftOperations {
	public long leftShift(long l, int positions){
		return l << positions;
	}
	
	public long rightShift(long l, int positions){
		return l >> positions;
	}
	
	public long unsignedRightShift(long l, int positions){
		return l >>> positions;
	}
}