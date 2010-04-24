/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author gliptak
 *
 */
public class ClassRandomizer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
		    byte[] classfileBuffer)	throws IllegalClassFormatException {
	    byte[] transformed = null;

	    System.out.println("Transforming " + className);

	    return transformed;
	}
}
