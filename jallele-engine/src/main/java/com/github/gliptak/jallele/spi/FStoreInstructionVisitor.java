package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class FStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.FSTORE, 0x43, 0x44, 0x45, 0x46}; // fstore, fstore_0, fstore_1, fstore_2, fstore_3

	public FStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any fstore instruction to pop to discard the value instead of storing it
			newVs.setOpCode(Opcodes.POP);
		}
		
		return newVs;
	}
}