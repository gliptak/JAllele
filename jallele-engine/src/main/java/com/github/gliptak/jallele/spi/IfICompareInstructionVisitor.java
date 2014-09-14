package com.github.gliptak.jallele.spi;

import org.apache.commons.lang.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

public class IfICompareInstructionVisitor implements InstructionVisitor {

	protected int[] values={Opcodes.IF_ICMPEQ, Opcodes.IF_ICMPNE, Opcodes.IF_ICMPLE, Opcodes.IF_ICMPLT,
			Opcodes.IF_ICMPGE, Opcodes.IF_ICMPGT};
	
	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			switch (vs.getOpCode()) {
				case Opcodes.IF_ICMPEQ:
					newVs.setOpCode(Opcodes.IF_ICMPNE);
					break;
				case Opcodes.IF_ICMPNE:
					newVs.setOpCode(Opcodes.IF_ICMPEQ);
					break;
				case Opcodes.IF_ICMPLE:
					newVs.setOpCode(Opcodes.IF_ICMPGT);
					break;
				case Opcodes.IF_ICMPLT:
					newVs.setOpCode(Opcodes.IF_ICMPGE);
					break;
				case Opcodes.IF_ICMPGT:
					newVs.setOpCode(Opcodes.IF_ICMPLE);
					break;
				case Opcodes.IF_ICMPGE:
					newVs.setOpCode(Opcodes.IF_ICMPLT);
					break;
				default:
					throw new IllegalArgumentException("opcode "+vs.getOpCode()+" not processed");
			}
		}
		
		return newVs;
	}
}