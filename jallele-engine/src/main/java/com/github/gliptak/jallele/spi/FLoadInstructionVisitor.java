package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class FLoadInstructionVisitor extends InstructionVisitor {

	// Opcodes for fload_0, fload_1, fload_2, fload_3 (ASM doesn't provide these constants)
	private static final int FLOAD_0 = 0x22;
	private static final int FLOAD_1 = 0x23;
	private static final int FLOAD_2 = 0x24;
	private static final int FLOAD_3 = 0x25;

	protected int[] values={Opcodes.FLOAD, FLOAD_0, FLOAD_1, FLOAD_2, FLOAD_3}; // fload, fload_0, fload_1, fload_2, fload_3

	public FLoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any fload instruction to fconst_0, fconst_1 or fconst_2 randomly
			int[] constValues = {Opcodes.FCONST_0, Opcodes.FCONST_1, Opcodes.FCONST_2};
			int selected = (int)Math.floor(random.nextDouble() * constValues.length);
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(constValues[selected]);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(constValues[selected])));
		}
		
		return newVs;
	}
}