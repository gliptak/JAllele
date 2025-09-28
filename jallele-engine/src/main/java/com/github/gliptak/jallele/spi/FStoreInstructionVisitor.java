package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class FStoreInstructionVisitor extends InstructionVisitor {

	// Opcodes for fstore_0, fstore_1, fstore_2, fstore_3 (ASM doesn't provide these constants)
	private static final int FSTORE_0 = 0x43;
	private static final int FSTORE_1 = 0x44;
	private static final int FSTORE_2 = 0x45;
	private static final int FSTORE_3 = 0x46;

	protected int[] values={Opcodes.FSTORE, FSTORE_0, FSTORE_1, FSTORE_2, FSTORE_3}; // fstore, fstore_0, fstore_1, fstore_2, fstore_3

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