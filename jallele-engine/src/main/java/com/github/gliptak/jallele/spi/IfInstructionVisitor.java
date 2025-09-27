package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class IfInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.IFEQ, Opcodes.IFNE, Opcodes.IFLT, Opcodes.IFGE,
			Opcodes.IFGT, Opcodes.IFLE};

	public IfInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			switch (vs.getOpCode()) {
				case Opcodes.IFEQ:
					newVs.setOpCode(Opcodes.IFNE);
					break;
				case Opcodes.IFNE:
					newVs.setOpCode(Opcodes.IFEQ);
					break;
				case Opcodes.IFLT:
					newVs.setOpCode(Opcodes.IFGE);
					break;
				case Opcodes.IFGE:
					newVs.setOpCode(Opcodes.IFLT);
					break;
				case Opcodes.IFGT:
					newVs.setOpCode(Opcodes.IFLE);
					break;
				case Opcodes.IFLE:
					newVs.setOpCode(Opcodes.IFGT);
					break;
				default:
					throw new IllegalArgumentException("opcode "+vs.getOpCode()+" not processed");
			}
		}
		
		return newVs;
	}
}