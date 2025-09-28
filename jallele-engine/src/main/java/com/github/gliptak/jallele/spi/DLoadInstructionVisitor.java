package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class DLoadInstructionVisitor extends InstructionVisitor {

	// Opcodes for dload_0, dload_1, dload_2, dload_3 (ASM doesn't provide these constants)
	private static final int DLOAD_0 = 0x26;
	private static final int DLOAD_1 = 0x27;
	private static final int DLOAD_2 = 0x28;
	private static final int DLOAD_3 = 0x29;

	protected int[] values={Opcodes.DLOAD, DLOAD_0, DLOAD_1, DLOAD_2, DLOAD_3}; // dload, dload_0, dload_1, dload_2, dload_3

	public DLoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any dload instruction to dconst_0 or dconst_1 randomly
			int[] constValues = {Opcodes.DCONST_0, Opcodes.DCONST_1};
			int selected = (int)Math.floor(random.nextDouble() * constValues.length);
			newVs.setOpCode(constValues[selected]);
		}
		
		return newVs;
	}
}