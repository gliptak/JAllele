package com.github.gliptak.jallele;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import com.sun.tools.attach.VirtualMachine;

import javassist.ClassPool;
import javassist.CtClass;

public class Agent {
	/**
	 * A reference to the {@link java.lang.instrument.Instrumentation} instance
	 * passed to this agent's {@link #premain} method. This way we can keep
	 * using the Instrumentation functionality!
	 **/
	static private Instrumentation inst = null;

	/**
	 * This method is called before the application’s main-method is called,
	 * when this agent is specified to the Java VM.
	 **/
	public static void premain(String agentArgs, Instrumentation _inst) {
		//System.out.println("Agent.premain() was called.");
		start(agentArgs, _inst);
	}

	public static void agentmain(String agentArgs, Instrumentation _inst) {
		//System.out.println("Agent.agentmain() was called.");
		start(agentArgs, _inst);
	}

	protected static void start(String agentArgs, Instrumentation _inst) {
		// Initialize the static variables we use to track information.
		inst = _inst;
	}
	
	/**
	 * Dynamically attach this agent
	 * 
	 * @throws Exception
	 */
	public static void attach() throws Exception {
		// prevent duplicate loading ...
		if (inst!=null){
			return;
		}
		File jarFile = File.createTempFile("agent", ".jar");
		jarFile.deleteOnExit();

		Manifest manifest = new Manifest();
		Attributes mainAttributes = manifest.getMainAttributes();
		mainAttributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
		mainAttributes.put(new Attributes.Name("Agent-Class"), Agent.class.getName());
		//mainAttributes.put(new Attributes.Name("Premain-Class"), Agent.class.getName());
		mainAttributes.put(new Attributes.Name("Can-Retransform-Classes"), "true");
		mainAttributes.put(new Attributes.Name("Can-Redefine-Classes"), "true");

		JarOutputStream jos = new JarOutputStream(
				new FileOutputStream(jarFile), manifest);
		JarEntry agent = new JarEntry(Agent.class.getName().replace('.', '/')
				+ ".class");
		jos.putNextEntry(agent);
		ClassPool pool = ClassPool.getDefault();
		CtClass ctClass = pool.get(Agent.class.getName());
		jos.write(ctClass.toBytecode());
		jos.closeEntry();
		jos.close();

		String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
		int p = nameOfRunningVM.indexOf('@');

		String processIdOfJvm = nameOfRunningVM.substring(0, p);

		// Attach to VM
		VirtualMachine vm = VirtualMachine.attach(processIdOfJvm);

		// Load Agent
		vm.loadAgent(jarFile.getAbsolutePath());
		vm.detach();
	}

	/**
	 * Add transformer
	 * @param cft
	 * @param canRefactor TODO
	 */
	public static void addTransformer(ClassFileTransformer cft, boolean canRefactor){
		inst.addTransformer(cft, canRefactor);
	}

	/**
	 * Remove transformer
	 * @param cft
	 */
	public static void removeTransformer(ClassFileTransformer cft) {
		inst.removeTransformer(cft);
	}
	
	/**
	 * Force retransform of classes
	 * @param classes
	 * @throws UnmodifiableClassException
	 */
	public static void restransform(Class<?> ... classes) throws UnmodifiableClassException {
		//System.out.println("retransform: "+inst.isRetransformClassesSupported());
		//for (Class<?> clazz: classes){
		//	System.out.println(clazz.toString()+" modifiable "+inst.isModifiableClass(clazz));			
		//}
		inst.retransformClasses(classes);
	}
}