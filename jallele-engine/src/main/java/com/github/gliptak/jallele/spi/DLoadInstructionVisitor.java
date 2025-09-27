package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class DLoadInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.DLOAD, 0x26, 0x27, 0x28, 0x29}; // dload, dload_0, dload_1, dload_2, dload_3

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