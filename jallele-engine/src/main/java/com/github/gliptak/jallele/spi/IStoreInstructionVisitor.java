package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class IStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.ISTORE, 0x3b, 0x3c, 0x3d, 0x3e}; // istore, istore_0, istore_1, istore_2, istore_3

	public IStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any istore instruction to pop to discard the value instead of storing it
			newVs.setOpCode(Opcodes.POP);
		}
		
		return newVs;
	}
}