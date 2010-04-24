/**
 * 
 */
package com.github.gliptak.jallele;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.regex.Pattern;

/**
 * @author gliptak
 *
 */
public class ClassRandomizer implements ClassFileTransformer {

	private String regex;

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
    	System.out.println("Transforming new " + className);
	    byte[] transformed = null;

	    if (Pattern.matches(regex, className)){
	    	System.out.println("Transforming " + className);
	    }

	    return transformed;
	}

	public void setFilter(String regex) {
		this.regex=regex;
	}
}
