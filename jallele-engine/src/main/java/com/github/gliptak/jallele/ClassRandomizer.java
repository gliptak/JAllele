/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import com.github.gliptak.jallele.spi.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizer implements ClassFileTransformer {

	private static final Logger logger = Logger.getLogger(ClassRandomizer.class.getName());

	private final Random random;

	private List<String> sources=new ArrayList<String>();
	
	private final List<InstructionVisitor> visitors=new ArrayList<InstructionVisitor>();

	private boolean recording=false;

	private List<VisitStatus[]> matches=new ArrayList<VisitStatus[]>();

	private VisitStatus[] currentStatusPair=null;

	private final TestRunner runner;
	
	private Set<String> processedSources=new HashSet<String>();
	
	public ClassRandomizer(List<String> sources, TestRunner runner){
		this.sources=sources;
		this.runner=runner;
		random = new Random();
		initVisitors(random);
	}

	protected void initVisitors(Random random) {
		visitors.add(new IConstInstructionVisitor(random));
		visitors.add(new IfInstructionVisitor(random));
		visitors.add(new IfNullInstructionVisitor(random));
		visitors.add(new LConstInstructionVisitor(random));
		visitors.add(new DConstInstructionVisitor(random));
		visitors.add(new FConstInstructionVisitor(random));
		visitors.add(new IfACompareInstructionVisitor(random));
		visitors.add(new IfICompareInstructionVisitor(random));
		visitors.add(new DoubleOpInstructionVisitor(random));
		visitors.add(new FloatOpInstructionVisitor(random));
		visitors.add(new IntegerOpInstructionVisitor(random));
		visitors.add(new LongOpInstructionVisitor(random));
		visitors.add(new LongShiftInstructionVisitor(random));
		visitors.add(new NegInstructionVisitor(random));
		visitors.add(new IPushInstructionVisitor(random));
		visitors.add(new IincInstructionVisitor(random));
		// ALoadInstructionVisitor removed - causes VerifyError by replacing ALOAD with ACONST_NULL
		visitors.add(new DLoadInstructionVisitor(random));
		visitors.add(new LLoadInstructionVisitor(random));
		visitors.add(new FLoadInstructionVisitor(random));
		// ILoadInstructionVisitor removed - causes VerifyError by replacing ILOAD with ICONST
		visitors.add(new IStoreInstructionVisitor(random));
	}

	public void recordMatches() throws Exception {
		// reset ...
		matches=new ArrayList<VisitStatus[]>();
		processedSources=new HashSet<String>();
		recording=true;
		Agent.addTransformer(this, true);
		for (String source: sources){
			Agent.restransform(Class.forName(source));
		}
		recording=false;
	}
	
	public VisitStatus[] randomizeRun() throws Exception {
		String classNameWithDots=null;
		int selected=(int)Math.floor(random.nextDouble()*matches.size());
		if (matches.size()>0){
			currentStatusPair=matches.get(selected);
			classNameWithDots=currentStatusPair[0].getClassName().replaceAll("/", ".");
			Agent.restransform(Class.forName(classNameWithDots));
		}
		runner.runTests();
		if (matches.size()>0){
			currentStatusPair=null;
			Agent.restransform(Class.forName(classNameWithDots));
			return matches.get(selected);
		} else {
			return new VisitStatus[0];
		}
	}
	
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		logger.finer("Transform called for " + className);
		byte[] transformed = null;
		
		String classNameWithDots=className.replaceAll("/", ".");
		if (sources.contains(classNameWithDots)){
			// Prevent reprocessing during recording phase
			if (recording && processedSources.contains(classNameWithDots)){
				logger.fine("Skipping already processed class during recording: " + className);
				return transformed;
			}
			try {
			    logger.fine("Transforming class: " + className);
			    transformed = process(className, classfileBuffer);
			    if (recording){
			    	processedSources.add(classNameWithDots);
			    	logger.fine("Recorded transformation for class: " + className);
			    }
			} catch (Exception e) {
				throw new IllegalClassFormatException(e.getMessage());
			}			
		}

		return transformed;
	}

	protected byte[] process(String className, byte[] classfileBuffer) {
	    final String className1=className;
	    final ClassRandomizer cr1=this;
		ClassWriter cw = new ClassWriter(0);
		ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				MethodVisitor mv=super.visitMethod(access, name, desc, signature,
						exceptions);
				return new MethodRandomizerVisitor(className1, name, desc, mv, cr1);
			}
		};
		ClassReader cr = new ClassReader(classfileBuffer);
		cr.accept(cv, 0);
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
