package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class LStoreInstructionVisitor extends InstructionVisitor {

	// Opcodes for lstore_0, lstore_1, lstore_2, lstore_3 (ASM doesn't provide these constants)
	private static final int LSTORE_0 = 0x3f;
	private static final int LSTORE_1 = 0x40;
	private static final int LSTORE_2 = 0x41;
	private static final int LSTORE_3 = 0x42;

	protected int[] values={Opcodes.LSTORE, LSTORE_0, LSTORE_1, LSTORE_2, LSTORE_3}; // lstore, lstore_0, lstore_1, lstore_2, lstore_3

	public LStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any lstore instruction to pop2 to discard the long value instead of storing it
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(Opcodes.POP2);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(Opcodes.POP2)));
		}
		
		return newVs;
	}
}