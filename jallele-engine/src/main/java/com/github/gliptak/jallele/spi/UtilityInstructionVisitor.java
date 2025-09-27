package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

/**
 * Handles utility instructions: nop, swap, and iinc
 * These are non-arithmetic instructions that perform utility operations
 */
public class UtilityInstructionVisitor extends InstructionVisitor {

	protected int[] values = {Opcodes.NOP, Opcodes.SWAP, Opcodes.IINC};

	public UtilityInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs = new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())) {
			switch (vs.getOpCode()) {
				case Opcodes.NOP:
					// NOP is a no-operation, leave as-is since mutations don't make semantic sense
					break;
					
				case Opcodes.SWAP:
					// SWAP swaps two stack values, leave as-is since stack integrity must be maintained
					break;
					
				case Opcodes.IINC:
					// IINC increments a local variable by a constant
					// We can mutate the increment constant (operand)
					int currentOperand = vs.getOperand();
					// Generate a different increment value (between -128 and 127 for byte const)
					int newOperand;
					do {
						newOperand = random.nextInt(256) - 128; // Generate -128 to 127
					} while (newOperand == currentOperand);
					newVs.setOperand(newOperand);
					break;
					
				default:
					// Should not happen given our values array
					break;
			}
		}
		
		return newVs;
	}
}