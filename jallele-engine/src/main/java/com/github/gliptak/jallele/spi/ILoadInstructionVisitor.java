package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ILoadInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.ILOAD, 0x1a, 0x1b, 0x1c, 0x1d}; // iload, iload_0, iload_1, iload_2, iload_3

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
			newVs.setOpCode(constValues[selected]);
		}
		
		return newVs;
	}
}