/**
 * 
 */
package com.github.gliptak.jallele;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.regex.Pattern;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;

/**
 * @author gliptak
 *
 */
public class ClassRandomizer implements ClassFileTransformer {

	private String regex;

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
    	//System.out.println("Transforming all " + className);
	    byte[] transformed = null;

	    if (Pattern.matches(regex, className)){
	    	try {
				randomize(classfileBuffer);
			} catch (Exception e) {
				throw new IllegalClassFormatException(e.getMessage());
			}
	    }

	    return transformed;
	}

	private void randomize(byte[] classfileBuffer) throws IOException, RuntimeException, BadBytecode {
		ClassPool cp = ClassPool.getDefault();
		ByteArrayInputStream is=new ByteArrayInputStream(classfileBuffer);
		CtClass ctClass=cp.makeClass(is, false);
		ClassFile cf=ctClass.getClassFile();
		ConstPool cop=cf.getConstPool();
		int size=cop.getSize();
		while(size>0){
			size--;
		}
//		System.out.println("pool: "+cop.getIntegerInfo(2));
		MethodInfo mi=cf.getMethod("twoTimes");
		CodeAttribute ca=mi.getCodeAttribute();
		CodeIterator ci=ca.iterator();
		while (ci.hasNext()) {
		    int index = ci.next();
		    int op = ci.byteAt(index);
		    System.out.println(Mnemonic.OPCODE[op]);
		}
	}

	private void randomize1(byte[] classfileBuffer) throws IOException, RuntimeException {
		ClassPool cp = ClassPool.getDefault();
		ByteArrayInputStream is=new ByteArrayInputStream(classfileBuffer);
		CtClass ctClass=cp.makeClass(is, false);
		CtMethod[] methods=ctClass.getDeclaredMethods();
		for(CtMethod method: methods){
			System.out.println(method.getName());
		}
//		CtMethod twoTimes=ctClass.getDeclaredMethod("twoTimes");
//		CodeIterator ci = ;
//		while (ci.hasNext()) {
//		    int index = ci.next();
//		    int op = ci.byteAt(index);
//		    System.out.println(Mnemonic.OPCODE[op]);
//		}
	}

	public void setFilter(String regex) {
		this.regex=regex;
	}
}
