/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * @author gliptak
 * 
 */
public class ClassRandomizer implements ClassFileTransformer {

	private static Logger logger = Logger.getLogger(ClassRandomizer.class.getName());

	private String regex;

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		logger.fine("Transform called for " + className);
		byte[] transformed = null;

		if (Pattern.matches(regex, className)) {
			try {
				transformed = randomize(className, classfileBuffer);
			} catch (Exception e) {
				throw new IllegalClassFormatException(e.getMessage());
			}
		}

		return transformed;
	}

	private byte[] randomize(String className, byte[] classfileBuffer) {
	    final String className1=className;
		ClassWriter cw = new ClassWriter(0) {
			@Override
			public MethodVisitor visitMethod(final int access,
					final String name, final String desc,
					final String signature, final String[] exceptions) {
				MethodVisitor mv=super.visitMethod(access, name, desc, signature,
						exceptions);
				return new MethodRandomizerVisitor(className1, mv);
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
