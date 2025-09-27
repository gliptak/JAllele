package com.github.gliptak.jallele.testLong;

public class LongOperations {
	public long addTwo(long l){
		return 2L + l;
	}
	
	public long multiplyBy(long l, long multiplier){
		return l * multiplier;
	}
	
	public long divideBy(long l, long divisor){
		return l / divisor;
	}
	
	public long subtractFrom(long l, long subtrahend){
		return l - subtrahend;
	}
	
	public long remainder(long l, long divisor){
		return l % divisor;
	}
	
	public long bitwiseAnd(long l, long mask){
		return l & mask;
	}
	
	public long bitwiseOr(long l, long mask){
		return l | mask;
	}
	
	public long bitwiseXor(long l, long mask){
		return l ^ mask;
	}
}