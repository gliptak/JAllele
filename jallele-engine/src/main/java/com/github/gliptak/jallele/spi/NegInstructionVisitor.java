package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

/**
 * Handles negation instructions (dneg, fneg, ineg, lneg)
 * Mutates by removing the negation operation - effectively testing if code properly 
 * handles both positive and negative values of the same magnitude
 */
public class NegInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.DNEG, Opcodes.FNEG, Opcodes.INEG, Opcodes.LNEG};

	public NegInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			// For negation instructions, the most meaningful mutation is to remove 
			// the negation entirely - this tests whether the code correctly handles
			// both positive and negative values of the same magnitude
			newVs.setOpCode(Opcodes.NOP); // Replace with no-operation
		}
		
		return newVs;
	}
}