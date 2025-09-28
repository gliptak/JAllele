package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class LLoadInstructionVisitor extends InstructionVisitor {

	// Opcodes for lload_0, lload_1, lload_2, lload_3 (ASM doesn't provide these constants)
	private static final int LLOAD_0 = 0x1e;
	private static final int LLOAD_1 = 0x1f;
	private static final int LLOAD_2 = 0x20;
	private static final int LLOAD_3 = 0x21;

	protected int[] values={Opcodes.LLOAD, LLOAD_0, LLOAD_1, LLOAD_2, LLOAD_3}; // lload, lload_0, lload_1, lload_2, lload_3

	public LLoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any lload instruction to lconst_0 or lconst_1 randomly
			int[] constValues = {Opcodes.LCONST_0, Opcodes.LCONST_1};
			int selected = (int)Math.floor(random.nextDouble() * constValues.length);
			newVs.setOpCode(constValues[selected]);
		}
		
		return newVs;
	}
}