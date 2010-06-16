package com.github.gliptak.jallele;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.tools.attach.VirtualMachine;

public class Agent {

	private static Logger logger = Logger.getLogger(Agent.class.getName());

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
		logger.fine("Agent.premain() was called.");
		start(agentArgs, _inst);
	}

	public static void agentmain(String agentArgs, Instrumentation _inst) {
		logger.fine("Agent.agentmain() was called.");
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
		if (inst != null) {
			return;
		}
		File jarFile = File.createTempFile("agent", ".jar");
		jarFile.deleteOnExit();

		Manifest manifest = new Manifest();
		Attributes mainAttributes = manifest.getMainAttributes();
		mainAttributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
		mainAttributes.put(new Attributes.Name("Agent-Class"), Agent.class
				.getName());
		// mainAttributes.put(new Attributes.Name("Premain-Class"),
		// Agent.class.getName());
		mainAttributes.put(new Attributes.Name("Can-Retransform-Classes"),
				"true");
		mainAttributes.put(new Attributes.Name("Can-Redefine-Classes"), "true");

		JarOutputStream jos = new JarOutputStream(
				new FileOutputStream(jarFile), manifest);
		JarEntry agent = new JarEntry(Agent.class.getName().replace('.', '/')
				+ ".class");
		jos.putNextEntry(agent);
		jos.write(getClassBytes(Agent.class));
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
	 * 
	 * @param cft
	 * @param canRefactor
	 */
	public static void addTransformer(ClassFileTransformer cft,	boolean canRefactor) {
		inst.addTransformer(cft, canRefactor);
	}

	/**
	 * Remove transformer
	 * 
	 * @param cft
	 */
	public static void removeTransformer(ClassFileTransformer cft) {
		inst.removeTransformer(cft);
	}

	/**
	 * Force retransform of classes
	 * 
	 * @param classes
	 * @throws UnmodifiableClassException
	 */
	public static void restransform(Class<?>... classes)
			throws UnmodifiableClassException {
		if (logger.isLoggable(Level.FINE)) {
			for (Class<?> clazz : classes) {
				logger.fine(clazz.toString() + " modifiable "
						+ inst.isModifiableClass(clazz));
			}
		}
		inst.retransformClasses(classes);
	}

	protected static byte[] getClassBytes(Class<?> clazz) throws IOException {
		String name = clazz.getName().replace('.', '/') + ".class";
		InputStream iStream = clazz.getClassLoader().getResourceAsStream(name);
		try {
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (true) {
				int len = iStream.read(buffer);
				if (len < 0) {
					break;
				}
				oStream.write(buffer, 0, len);
			}
			return oStream.toByteArray();
		} finally {
			iStream.close();
		}
	}

}