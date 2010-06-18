package com.github.gliptak.jallele.spi;

import org.apache.commons.lang.ArrayUtils;
import org.objectweb.asm.Opcodes;

import com.github.gliptak.jallele.VisitStatus;

public class IfNullInstructionVisitor implements InstructionVisitor {

	protected int[] values={Opcodes.IFNULL, Opcodes.IFNONNULL};
	
	@Override
	public VisitStatus isMatch(VisitStatus vs) {
		VisitStatus newVs=new VisitStatus(vs);
		if (ArrayUtils.contains(values, vs.getOpCode())){
			int which=ArrayUtils.indexOf(values, vs.getOpCode());
			int selected=-1;
			do {
				selected=(int)Math.floor(Math.random()*values.length);
			} while (which==selected);
			newVs.setOpCode(values[selected]);
		}
		
		return newVs;
	}

}