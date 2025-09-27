package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ALoadInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.ALOAD, 0x2a, 0x2b, 0x2c, 0x2d}; // aload, aload_0, aload_1, aload_2, aload_3

	public ALoadInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// Mutate any aload instruction to aconst_null
			newVs.setOpCode(Opcodes.ACONST_NULL);
		}
		
		return newVs;
	}
}