/**
 * 
 */
package com.github.gliptak.jallele;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gliptak
 * 
 */
public class MethodRandomizerVisitor extends MethodAdapter {

	private String className;

	public MethodRandomizerVisitor(String className, MethodVisitor mv) {
		super(mv);
		this.className=className;
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitInsn(int)
	 */
	@Override
	public void visitInsn(int opCode) {
		System.out.println(opCode);
		if (opCode == Opcodes.ICONST_2){
			opCode=Opcodes.ICONST_3;
		}
		super.visitInsn(opCode);
	}
}
