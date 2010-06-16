/**
 * 
 */
package com.github.gliptak.jallele;

import java.util.logging.Logger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gliptak
 * 
 */
public class MethodRandomizerVisitor extends MethodAdapter {

	private static Logger logger = Logger.getLogger(MethodRandomizerVisitor.class.getName());

	private String className;

	private ClassRandomizer cr;

	private int count=0;

	private String methodName;

	private String methodDesc;

	public MethodRandomizerVisitor(String className, String methodName, String methodDesc,
			MethodVisitor mv, ClassRandomizer cr) {
		super(mv);
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
		logger.finer("count: "+count+" opCode: "+opCode);
		VisitStatus vs=new VisitStatus(className, methodName, methodDesc, count);
		vs.setOpCode(opCode);
		VisitStatus newVs=cr.visit(vs);
		count++;
		super.visitInsn(newVs.getOpCode());			
	}
}
