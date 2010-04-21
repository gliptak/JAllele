package com.github.gliptak.jallele;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author gliptak
 *
 */
public class RandomizingClassloader extends ClassLoader {

	/**
	 * Default constructor
	 */
	public RandomizingClassloader() {
	}

	/**
	 * @param parent parent classloader
	 */
	public RandomizingClassloader(ClassLoader parent) {
		super(parent);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String, boolean)
	 */
	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)	throws ClassNotFoundException {
		System.out.println("load class: "+name+" resolve: "+resolve);
		return super.loadClass(name, resolve);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("load class: "+name);
		return super.loadClass(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("find class: "+name);
		return super.findClass(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findLibrary(java.lang.String)
	 */
	@Override
	protected String findLibrary(String libname) {
		System.out.println("find library: "+libname);
		return super.findLibrary(libname);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findResource(java.lang.String)
	 */
	@Override
	protected URL findResource(String name) {
		System.out.println("find resource: "+name);
		return super.findResource(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findResources(java.lang.String)
	 */
	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		System.out.println("find resource: "+name);
		return super.findResources(name);
	}
}