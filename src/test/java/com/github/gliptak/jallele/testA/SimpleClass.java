package com.github.gliptak.jallele.testA;

public class SimpleClass {

	public int twoTimes(int i){
		System.out.println("classhash: "+this.getClass().hashCode());
		return 2*i;
	}
}