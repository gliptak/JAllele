package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class LongShiftInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.LSHL, Opcodes.LSHR, Opcodes.LUSHR};

	public LongShiftInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			int which=ArrayUtils.indexOf(values, vs.getOpCode());
			int selected=-1;
			do {
				selected=(int)Math.floor(random.nextDouble()*values.length);
			} while (which==selected);
			int fromOpCode = vs.getOpCode();
			newVs.setOpCode(values[selected]);
			logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(values[selected])));
		}
		
		return newVs;
	}
}