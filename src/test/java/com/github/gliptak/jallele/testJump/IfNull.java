package com.github.gliptak.jallele.testJump;

public class IfNull {
	
	public String ifNull(String s){
		if (s==null){
			return "true";
		} else {
			return "false";
		}
	}

	public String ifNotNull(String s){
		if (s!=null){
			return "true";
		} else {
			return "false";
		}
	}
}
