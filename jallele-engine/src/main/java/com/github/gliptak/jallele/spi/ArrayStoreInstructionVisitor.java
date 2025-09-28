package com.github.gliptak.jallele.spi;

import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public class ArrayStoreInstructionVisitor extends InstructionVisitor {

	protected int[] values={Opcodes.IASTORE, Opcodes.LASTORE, Opcodes.FASTORE, Opcodes.DASTORE, Opcodes.AASTORE, Opcodes.BASTORE, Opcodes.CASTORE, Opcodes.SASTORE};

	public ArrayStoreInstructionVisitor(Random random) {
		super(random);
	}

	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			switch (vs.getOpCode()) {
				case Opcodes.LASTORE: // lastore - arrayref, index, long_value → (4 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving arrayref + index on stack
					break;
				case Opcodes.DASTORE: // dastore - arrayref, index, double_value → (4 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving arrayref + index on stack
					break;
				case Opcodes.IASTORE: // iastore - arrayref, index, int_value → (3 stack slots total)
				case Opcodes.FASTORE: // fastore - arrayref, index, float_value → (3 stack slots total)
				case Opcodes.AASTORE: // aastore - arrayref, index, ref_value → (3 stack slots total)
				case Opcodes.BASTORE: // bastore - arrayref, index, byte_value → (3 stack slots total)
				case Opcodes.CASTORE: // castore - arrayref, index, char_value → (3 stack slots total)
				case Opcodes.SASTORE: // sastore - arrayref, index, short_value → (3 stack slots total)
					newVs.setOpCode(Opcodes.POP2); // Remove 2 slots, leaving either arrayref or index on stack
					break;
				default:
					throw new IllegalArgumentException("opcode "+vs.getOpCode()+" not processed");
			}
		}
		
		return newVs;
	}
}