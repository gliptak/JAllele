/**
 * 
 */
package com.github.gliptak.jallele;

import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;

import static java.lang.String.format;

/**
 * @author gliptak
 * 
 */
public class MethodRandomizerVisitor extends MethodVisitor {

	private static Logger logger = Logger.getLogger(MethodRandomizerVisitor.class.getName());

	private String className;

	private ClassRandomizer cr;

	private int count=0;

	private String methodName;

	private String methodDesc;

	private int currentLine=1;

	public MethodRandomizerVisitor(String className, String methodName, String methodDesc,
			MethodVisitor mv, ClassRandomizer cr) {
		super(Opcodes.ASM5, mv);
		this.className=className;
		this.methodName=methodName;
		this.methodDesc=methodDesc;
		this.cr=cr;
	}

	/**
	 * Get human-readable name for an opcode
	 * @param opCode the opcode value
	 * @return the opcode name
	 */
	private String getOpcodeName(int opCode) {
		if (opCode >= 0 && opCode < Printer.OPCODES.length) {
			return Printer.OPCODES[opCode];
		}
		return "UNKNOWN(" + opCode + ")";
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitInsn(int)
	 */
	@Override
	public void visitInsn(int opCode) {
		logger.finer(format("visitInsn %s", getOpcodeName(opCode)));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitInsn(newVs.getOpCode());			
	}

	@Override
	public void visitIntInsn(int opCode, int operand) {
		logger.finer(format("visitIntInsn %s %d", getOpcodeName(opCode), operand));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		vs.setOperand(operand);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitIntInsn(newVs.getOpCode(), newVs.getOperand());
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitJumpInsn(int, org.objectweb.asm.Label)
	 */
	@Override
	public void visitJumpInsn(int opCode, Label label) {
		logger.finer(format("visitJumpInsn %s %s", getOpcodeName(opCode), label));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		vs.setLabel(label);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitJumpInsn(newVs.getOpCode(), label);
	}

	@Override
	public void visitVarInsn(int opCode, int var) {
		logger.finer(format("visitVarInsn %s %d", getOpcodeName(opCode), var));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		vs.setOperand(var);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitVarInsn(newVs.getOpCode(), newVs.getOperand());
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		currentLine = line;
		super.visitLineNumber(line, start);
	}
}
