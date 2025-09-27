package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class StackInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.DUP, Opcodes.DUP_X1, Opcodes.DUP_X2, 
			Opcodes.DUP2, Opcodes.DUP2_X1, Opcodes.DUP2_X2, 
			Opcodes.POP, Opcodes.POP2, Opcodes.SWAP};

	public StackInstructionVisitor(Random random) {
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
			newVs.setOpCode(values[selected]);
		}
		
		return newVs;
	}

}