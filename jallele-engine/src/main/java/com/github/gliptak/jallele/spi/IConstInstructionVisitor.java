package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class IConstInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.ICONST_0, Opcodes.ICONST_1, Opcodes.ICONST_2,
			Opcodes.ICONST_3, Opcodes.ICONST_4, Opcodes.ICONST_5, Opcodes.ICONST_M1};

	public IConstInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			int fromOpCode = vs.getOpCode();
			if (Opcodes.ICONST_1 == vs.getOpCode()){
				// TODO: is there a better way to process booleans?
				newVs.setOpCode(Opcodes.ICONST_0);
				logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(Opcodes.ICONST_0)));
			} else {
				int which=ArrayUtils.indexOf(values, vs.getOpCode());
				int selected=-1;
				do {
					selected=(int)Math.floor(random.nextDouble()*values.length);
				} while (which==selected);
				newVs.setOpCode(values[selected]);
				logger.fine(String.format("Transform: %s -> %s", getOpcodeName(fromOpCode), getOpcodeName(values[selected])));
			}
		}
		
		return newVs;
	}

}