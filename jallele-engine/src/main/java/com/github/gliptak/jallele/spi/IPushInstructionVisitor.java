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
			int currentOperand = vs.getOperand();
			if (newVs.getOpCode() == Opcodes.BIPUSH) {
				byte[] b = new byte[1];
				random.nextBytes(b);
				newVs.setOperand(b[0]);
				logger.fine(String.format("Transform: %s operand %d -> %d", getOpcodeName(vs.getOpCode()), currentOperand, b[0]));
			} else if (newVs.getOpCode() == Opcodes.SIPUSH) {
				short newOperand = (short)random.nextInt(1 << 16);
				newVs.setOperand(newOperand);
				logger.fine(String.format("Transform: %s operand %d -> %d", getOpcodeName(vs.getOpCode()), currentOperand, newOperand));
			} else {
				// TODO NEWARRAY
			}
		}
		return newVs;
	}
}
