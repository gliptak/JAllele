package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ALoadInstructionVisitor extends InstructionVisitor {

	// Opcodes for aload_0, aload_1, aload_2, aload_3 (ASM doesn't provide these constants)
	private static final int ALOAD_0 = 0x2a;
	private static final int ALOAD_1 = 0x2b;
	private static final int ALOAD_2 = 0x2c;
	private static final int ALOAD_3 = 0x2d;

	protected int[] values={Opcodes.ALOAD, ALOAD_0, ALOAD_1, ALOAD_2, ALOAD_3}; // aload, aload_0, aload_1, aload_2, aload_3

	public ALoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any aload instruction to aconst_null
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(Opcodes.ACONST_NULL);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(Opcodes.ACONST_NULL)));
		}
		
		return newVs;
	}
}