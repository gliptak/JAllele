package com.github.gliptak.jallele.spi;

import com.github.gliptak.jallele.VisitStatus;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IPushInstructionVisitor implements InstructionVisitor {

	protected int[] values={Opcodes.BIPUSH, Opcodes.SIPUSH};
	
	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			if (newVs.getOpCode() == Opcodes.BIPUSH) {
				byte[] b = new byte[1];
				ThreadLocalRandom.current().nextBytes(b);
				newVs.setOperand(b[0]);
			} else if (newVs.getOpCode() == Opcodes.SIPUSH) {
				newVs.setOperand((short)ThreadLocalRandom.current().nextInt(1 << 16));
			} else {
				// TODO NEWARRAY
			}
		}
		return newVs;
	}
}
