package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class AStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.ASTORE, 0x4b, 0x4c, 0x4d, 0x4e}; // astore, astore_0, astore_1, astore_2, astore_3

	public AStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any astore instruction to pop to discard the reference value instead of storing it
			newVs.setOpCode(Opcodes.POP);
		}
		
		return newVs;
	}
}