package com.github.gliptak.jallele.testJump;

public class IfReferenceCompare {
	
	public String ifReferenceCompare(String s1, String s2){
		if (s1==s2){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifNotReferenceCompare(String s1, String s2){
		if (s1!=s2){
			return "true";
		} else {
			return "false";
		}
	}
}
