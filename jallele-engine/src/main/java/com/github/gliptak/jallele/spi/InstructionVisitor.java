package com.github.gliptak.jallele.spi;

import com.github.gliptak.jallele.VisitStatus;

public interface InstructionVisitor {

	/** Process this status and return new one
	 * 
	 * @param vs describes status
	 * @return describes new status
	 */
	VisitStatus isMatch(VisitStatus vs);

}
