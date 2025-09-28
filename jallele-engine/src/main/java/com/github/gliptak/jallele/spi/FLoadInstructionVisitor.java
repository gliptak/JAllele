package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class FLoadInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.FLOAD, 0x22, 0x23, 0x24, 0x25}; // fload, fload_0, fload_1, fload_2, fload_3

	public FLoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any fload instruction to fconst_0, fconst_1 or fconst_2 randomly
			int[] constValues = {Opcodes.FCONST_0, Opcodes.FCONST_1, Opcodes.FCONST_2};
			int selected = (int)Math.floor(random.nextDouble() * constValues.length);
			newVs.setOpCode(constValues[selected]);
		}
		
		return newVs;
	}
}