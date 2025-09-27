package com.github.gliptak.jallele.testIf;

public class IfOperations {
	public boolean isZero(int value) {
		if (value == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isNotZero(int value) {
		if (value != 0) {
			return true;
		}
		return false;
	}
	
	public boolean isNegative(int value) {
		if (value < 0) {
			return true;
		}
		return false;
	}
	
	public boolean isPositiveOrZero(int value) {
		if (value >= 0) {
			return true;
		}
		return false;
	}
	
	public boolean isPositive(int value) {
		if (value > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isNegativeOrZero(int value) {
		if (value <= 0) {
			return true;
		}
		return false;
	}
}