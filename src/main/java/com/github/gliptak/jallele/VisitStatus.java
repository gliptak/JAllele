package com.github.gliptak.jallele;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class VisitStatus {

	private int count=0;
	private int opCode=-1;
	private String className=null;
	private String methodDesc=null;
	private String methodName=null;
	
	public VisitStatus(String className, String methodName, String methodDesc, int count) {
		this.className=className;
		this.methodName=methodName;
		this.methodDesc=methodDesc;
		this.count=count;
	}

	public VisitStatus(VisitStatus vs) {
		super();
		this.className=vs.getClassName();
		this.methodName=vs.getMethodName();
		this.methodDesc=vs.getMethodDesc();
		this.count=vs.getCount();
		this.opCode=vs.getOpCode();
	}

	public void setOpCode(int opCode) {
		this.opCode=opCode;
	}

	public int getOpCode() {
		return opCode;
	}

	public int getCount() {
		return count;
	}
	
	public boolean equals(Object obj) {
		   return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode() {
		   return HashCodeBuilder.reflectionHashCode(this);
    }
	
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
    }

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the methodDesc
	 */
	public String getMethodDesc() {
		return methodDesc;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
}
