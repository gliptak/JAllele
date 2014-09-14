package com.github.gliptak.jallele.testJump;

public class IfIntegerCompare {

	public String ifIntegerCompareEqual(int left, int right){
		if (left==right){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifIntegerCompareNotEqual(int left, int right){
		if (left!=right){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifIntegerCompareLess(int left, int right){
		if (left<right){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifIntegerCompareLessEqual(int left, int right){
		if (left<=right){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifIntegerCompareGreater(int left, int right){
		if (left>right){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifIntegerCompareGreaterEqual(int left, int right){
		if (left>=right){
			return "true";
		} else {
			return "false";
		}
	}

}
