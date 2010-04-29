/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.regex.Pattern;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizer implements ClassFileTransformer {

	private String regex;

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// System.out.println("Transforming all " + className);
		byte[] transformed = null;

		if (Pattern.matches(regex, className)) {
			try {
				transformed = randomize(classfileBuffer);
			} catch (Exception e) {
				throw new IllegalClassFormatException(e.getMessage());
			}
		}

		return transformed;
	}

	private byte[] randomize(byte[] classfileBuffer) {
		// TraceClassVisitor tcv=new TraceClassVisitor(new
		// PrintWriter(System.out));
		// ClassReader cr1=new ClassReader(classfileBuffer);
		// cr1.accept(tcv, 0);
		// MethodRandomizerVisitor rv=new MethodRandomizerVisitor();
		ClassWriter cw = new ClassWriter(0) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.objectweb.asm.ClassWriter#visitMethod(int,
			 * java.lang.String, java.lang.String, java.lang.String,
			 * java.lang.String[])
			 */
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				MethodVisitor mv=super.visitMethod(access, name, desc, signature,
						exceptions);
				return new MethodRandomizerVisitor(mv);
			};

		};
		ClassReader cr = new ClassReader(classfileBuffer);
		cr.accept(cw, 0);
		return cw.toByteArray();
	}

	public void setFilter(String regex) {
		this.regex = regex;
	}
}
