package com.github.gliptak.jallele;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.objectweb.asm.Label;
import org.objectweb.asm.util.Printer;

public class VisitStatus {

	private int count=0;
	private int lineNumber = 1;
	private int opCode=-1;
	private int operand = 0;
	private String className=null;
	private String methodDesc=null;
	private String methodName=null;
	private Label label=null;
	
	public VisitStatus(String className, String methodName, String methodDesc, int count, int lineNumber) {
		this.className=className;
		this.methodName=methodName;
		this.methodDesc=methodDesc;
		this.count=count;
		this.setLineNumber(lineNumber);
	}

	public VisitStatus(VisitStatus vs) {
		super();
		this.className=vs.getClassName();
		this.methodName=vs.getMethodName();
		this.methodDesc=vs.getMethodDesc();
		this.opCode=vs.getOpCode();
		this.operand=vs.getOperand();
		this.label=vs.getLabel();
		this.count=vs.getCount();
		this.lineNumber=vs.getLineNumber();
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
		   return ToStringBuilder.reflectionToString(this, new OpcodeFormatter());
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

	public void setLabel(Label label) {
		this.label=label;
	}

	/**
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getOperand() {
		return operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
	}

	public class OpcodeFormatter extends ToStringStyle {
		private static final long serialVersionUID = -1448916649019145034L;

		protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
			if ("opCode".equals(fieldName)) {
				value = Printer.OPCODES[(int)value];
			}
			buffer.append(value);
		}
	}
}