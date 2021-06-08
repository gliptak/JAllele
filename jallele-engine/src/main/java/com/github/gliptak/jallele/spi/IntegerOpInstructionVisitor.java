package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class IntegerOpInstructionVisitor extends InstructionVisitor {

	protected int[] values={ Opcodes.IADD, Opcodes.IAND, Opcodes.IDIV, Opcodes.IMUL, Opcodes.IOR,
			Opcodes.IREM, Opcodes.ISHL, Opcodes.ISHR, Opcodes.ISUB, Opcodes.IUSHR, Opcodes.IXOR };

    public IntegerOpInstructionVisitor(Random random) {
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