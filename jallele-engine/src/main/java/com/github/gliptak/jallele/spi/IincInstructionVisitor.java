package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

/**
 * Handles the iinc instruction (increment local variable)
 * Mutates the increment operand to test different increment values
 */
public class IincInstructionVisitor extends InstructionVisitor {

	protected int[] values = {Opcodes.IINC};

	public IincInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs = new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())) {
			// IINC increments a local variable by a constant
			// We can mutate the increment constant (operand)
			int currentOperand = vs.getOperand();
			// Generate a different increment value (between -128 and 127 for byte const)
			int newOperand;
			do {
				newOperand = random.nextInt(256) - 128; // Generate -128 to 127
			} while (newOperand == currentOperand);
			newVs.setOperand(newOperand);
		}
		
		return newVs;
	}
}