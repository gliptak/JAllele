package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class AStoreInstructionVisitor extends InstructionVisitor {

	// Opcodes for astore_0, astore_1, astore_2, astore_3 (ASM doesn't provide these constants)
	private static final int ASTORE_0 = 0x4b;
	private static final int ASTORE_1 = 0x4c;
	private static final int ASTORE_2 = 0x4d;
	private static final int ASTORE_3 = 0x4e;

	protected int[] values={Opcodes.ASTORE, ASTORE_0, ASTORE_1, ASTORE_2, ASTORE_3}; // astore, astore_0, astore_1, astore_2, astore_3

	public AStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any astore instruction to pop to discard the reference value instead of storing it
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(Opcodes.POP);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(Opcodes.POP)));
		}
		
		return newVs;
	}
}