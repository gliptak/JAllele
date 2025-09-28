package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class LStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.LSTORE, 0x3f, 0x40, 0x41, 0x42}; // lstore, lstore_0, lstore_1, lstore_2, lstore_3

	public LStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any lstore instruction to pop2 to discard the long value instead of storing it
			newVs.setOpCode(Opcodes.POP2);
		}
		
		return newVs;
	}
}