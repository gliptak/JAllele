package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class DStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.DSTORE, 0x47, 0x48, 0x49, 0x4a}; // dstore, dstore_0, dstore_1, dstore_2, dstore_3

	public DStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any dstore instruction to pop2 to discard the double value instead of storing it
			newVs.setOpCode(Opcodes.POP2);
		}
		
		return newVs;
	}
}