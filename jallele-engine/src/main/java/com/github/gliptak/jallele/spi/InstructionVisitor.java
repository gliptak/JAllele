package com.github.gliptak.jallele.spi;

import com.github.gliptak.jallele.VisitStatus;

import java.util.Random;

public abstract class InstructionVisitor {

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

}
