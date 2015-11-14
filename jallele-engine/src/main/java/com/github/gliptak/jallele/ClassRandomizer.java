/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import com.github.gliptak.jallele.spi.DoubleOpInstructionVisitor;
import com.github.gliptak.jallele.spi.IConstInstructionVisitor;
import com.github.gliptak.jallele.spi.IfACompareInstructionVisitor;
import com.github.gliptak.jallele.spi.IfICompareInstructionVisitor;
import com.github.gliptak.jallele.spi.IfNullInstructionVisitor;
import com.github.gliptak.jallele.spi.InstructionVisitor;
import com.github.gliptak.jallele.spi.LConstInstructionVisitor;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizer implements ClassFileTransformer {

	private static Logger logger = Logger.getLogger(ClassRandomizer.class.getName());

	private List<String> sources=new ArrayList<String>();
	
	private List<InstructionVisitor> visitors=new ArrayList<InstructionVisitor>();

	private boolean recording=false;

	private List<VisitStatus[]> matches=new ArrayList<VisitStatus[]>();

	private VisitStatus[] currentStatusPair=null;

	private final TestRunner runner;
	
	public ClassRandomizer(List<String> sources, TestRunner runner){
		this.sources=sources;
		this.runner=runner;
		initVisitors();
	}

	protected void initVisitors() {
		visitors.add(new IConstInstructionVisitor());
		visitors.add(new IfNullInstructionVisitor());
		visitors.add(new LConstInstructionVisitor());
		visitors.add(new IfACompareInstructionVisitor());
		visitors.add(new IfICompareInstructionVisitor());
		visitors.add(new DoubleOpInstructionVisitor());
	}

	public void recordMatches() throws Exception {
		// reset ...
		matches=new ArrayList<VisitStatus[]>();
		recording=true;
		Agent.addTransformer(this, true);
		for (String source: sources){
			Agent.restransform(Class.forName(source));
		}
		recording=false;
	}
	
	public void randomizeRun() throws Exception {
		String classNameWithDots=null;
		if (matches.size()>0){
			int selected=(int)Math.floor(Math.random()*matches.size());
			currentStatusPair=matches.get(selected);			
			classNameWithDots=currentStatusPair[0].getClassName().replaceAll("/", ".");
			Agent.restransform(Class.forName(classNameWithDots));
		}
		runner.runTests();
		if (matches.size()>0){
			currentStatusPair=null;
			Agent.restransform(Class.forName(classNameWithDots));			
		}
	}
	
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		logger.fine("Transform called for " + className);
		byte[] transformed = null;
		
		String classNameWithDots=className.replaceAll("/", ".");
		if (sources.contains(classNameWithDots)){
			try {
			    transformed = process(className, classfileBuffer);
			} catch (Exception e) {
				throw new IllegalClassFormatException(e.getMessage());
			}			
		}

		return transformed;
	}

	protected byte[] process(String className, byte[] classfileBuffer) {
	    final String className1=className;
	    final ClassRandomizer cr1=this;
		ClassWriter cw = new ClassWriter(0) {
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				MethodVisitor mv=super.visitMethod(access, name, desc, signature,
						exceptions);
				return new MethodRandomizerVisitor(className1, name, desc, mv, cr1);
			};
		};
		ClassReader cr = new ClassReader(classfileBuffer);
		cr.accept(cw, 0);
		return cw.toByteArray();
	}

	public VisitStatus visit(VisitStatus vs) {
		if (recording){
			for (InstructionVisitor visitor: visitors){
				VisitStatus newVs=visitor.isMatch(vs);
				if (!vs.equals(newVs)){
					recordMatch(vs, newVs);
				}
			}
			return vs;
		} else {
			if (currentStatusPair==null){
				return vs;				
			} else {
				currentStatusPair[0].setLabel(vs.getLabel());
				if (vs.equals(currentStatusPair[0])){
					currentStatusPair[1].setLabel(vs.getLabel());
					return currentStatusPair[1];					
				} else {
					return vs;
				}
			}
		}
	}

	protected void recordMatch(VisitStatus vs, VisitStatus newVs) {
		VisitStatus[] pair=new VisitStatus[2];
		pair[0]=vs;
		pair[1]=newVs;
		matches.add(pair);
	}

}
