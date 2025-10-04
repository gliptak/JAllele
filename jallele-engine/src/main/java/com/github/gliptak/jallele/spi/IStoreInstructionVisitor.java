package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class IStoreInstructionVisitor extends InstructionVisitor {

	// Opcodes for istore_0, istore_1, istore_2, istore_3 (ASM doesn't provide these constants)
	private static final int ISTORE_0 = 0x3b;
	private static final int ISTORE_1 = 0x3c;
	private static final int ISTORE_2 = 0x3d;
	private static final int ISTORE_3 = 0x3e;

	protected int[] values={Opcodes.ISTORE, ISTORE_0, ISTORE_1, ISTORE_2, ISTORE_3}; // istore, istore_0, istore_1, istore_2, istore_3

	public IStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any istore instruction to pop to discard the value instead of storing it
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(Opcodes.POP);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(Opcodes.POP)));
		}
		
		return newVs;
	}
}