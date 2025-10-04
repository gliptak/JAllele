package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ILoadInstructionVisitor extends InstructionVisitor {

	// Opcodes for iload_0, iload_1, iload_2, iload_3 (ASM doesn't provide these constants)
	private static final int ILOAD_0 = 0x1a;
	private static final int ILOAD_1 = 0x1b;
	private static final int ILOAD_2 = 0x1c;
	private static final int ILOAD_3 = 0x1d;

	protected int[] values={Opcodes.ILOAD, ILOAD_0, ILOAD_1, ILOAD_2, ILOAD_3}; // iload, iload_0, iload_1, iload_2, iload_3

	public ILoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any iload instruction to iconst_* randomly
			int[] constValues = {Opcodes.ICONST_0, Opcodes.ICONST_1, Opcodes.ICONST_2, 
								Opcodes.ICONST_3, Opcodes.ICONST_4, Opcodes.ICONST_5, Opcodes.ICONST_M1};
			int selected = (int)Math.floor(random.nextDouble() * constValues.length);
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(constValues[selected]);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(constValues[selected])));
		}
		
		return newVs;
	}
}