package com.github.gliptak.jallele.spi;

import com.github.gliptak.jallele.VisitStatus;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import java.util.Random;

public class IPushInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.BIPUSH, Opcodes.SIPUSH};

	public IPushInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			if (newVs.getOpCode() == Opcodes.BIPUSH) {
				byte[] b = new byte[1];
				random.nextBytes(b);
				newVs.setOperand(b[0]);
			} else if (newVs.getOpCode() == Opcodes.SIPUSH) {
				newVs.setOperand((short)random.nextInt(1 << 16));
			} else {
				// TODO NEWARRAY
			}
		}
		return newVs;
	}
}
