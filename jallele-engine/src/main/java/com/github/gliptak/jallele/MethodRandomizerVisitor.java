/**
 * 
 */
package com.github.gliptak.jallele;

import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitInsn(int)
	 */
	@Override
	public void visitInsn(int opCode) {
		logger.fine(format("visitInsn %x/%d", opCode, opCode));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitInsn(newVs.getOpCode());			
	}

	@Override
	public void visitIntInsn(int opCode, int operand) {
		logger.fine(format("visitIntInsn %x/%d %d", opCode, opCode, operand));
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
		logger.fine(format("visitInsn %x/%d %s", opCode, opCode, label));
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count, currentLine);
		vs.setOpCode(opCode);
		vs.setLabel(label);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitJumpInsn(newVs.getOpCode(), label);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		currentLine = line;
		super.visitLineNumber(line, start);
	}
}
