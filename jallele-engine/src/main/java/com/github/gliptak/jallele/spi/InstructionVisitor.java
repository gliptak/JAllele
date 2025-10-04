package com.github.gliptak.jallele.spi;

import com.github.gliptak.jallele.VisitStatus;
import org.objectweb.asm.util.Printer;

import java.util.Random;
import java.util.logging.Logger;

public abstract class InstructionVisitor {

	protected final Logger logger = Logger.getLogger(this.getClass().getName());
	protected final Random random;

	public InstructionVisitor(Random random) {
		this.random = random;
	}

	/** Process this status and return new one
	 * 
	 * @param vs describes status
	 * @return describes new status
	 */
	abstract public VisitStatus isMatch(VisitStatus vs);

	/**
	 * Get human-readable name for an opcode
	 * @param opCode the opcode value
	 * @return the opcode name
	 */
	protected String getOpcodeName(int opCode) {
		if (opCode >= 0 && opCode < Printer.OPCODES.length) {
			return Printer.OPCODES[opCode];
		}
		return "UNKNOWN(" + opCode + ")";
	}

}
