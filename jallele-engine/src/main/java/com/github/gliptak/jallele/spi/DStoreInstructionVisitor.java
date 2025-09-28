package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class DStoreInstructionVisitor extends InstructionVisitor {

	// Opcodes for dstore_0, dstore_1, dstore_2, dstore_3 (ASM doesn't provide these constants)
	private static final int DSTORE_0 = 0x47;
	private static final int DSTORE_1 = 0x48;
	private static final int DSTORE_2 = 0x49;
	private static final int DSTORE_3 = 0x4a;

	protected int[] values={Opcodes.DSTORE, DSTORE_0, DSTORE_1, DSTORE_2, DSTORE_3}; // dstore, dstore_0, dstore_1, dstore_2, dstore_3

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