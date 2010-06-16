package com.github.gliptak.jallele.spi;

import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

public class IConstInstructionVisitor implements InstructionVisitor {

	@Override
	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (vs.getOpCode() == Opcodes.ICONST_2){
			newVs.setOpCode(Opcodes.ICONST_3);
		}
		return newVs;
	}

}