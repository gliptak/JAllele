package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ArrayStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56}; // iastore, lastore, fastore, dastore, aastore, bastore, castore, sastore

	public ArrayStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			switch (vs.getOpCode()) {
				case 0x50: // lastore - arrayref, index, long_value → (4 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving arrayref + index on stack
					break;
				case 0x52: // dastore - arrayref, index, double_value → (4 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving arrayref + index on stack
					break;
				case 0x4f: // iastore - arrayref, index, int_value → (3 stack slots total)
				case 0x51: // fastore - arrayref, index, float_value → (3 stack slots total)
				case 0x53: // aastore - arrayref, index, ref_value → (3 stack slots total)
				case 0x54: // bastore - arrayref, index, byte_value → (3 stack slots total)
				case 0x55: // castore - arrayref, index, char_value → (3 stack slots total)
				case 0x56: // sastore - arrayref, index, short_value → (3 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving either arrayref or index on stack
					break;
				default:
					throw new IllegalArgumentException("opcode "+vs.getOpCode()+" not processed");
			}
		}
		
		return newVs;
	}
}