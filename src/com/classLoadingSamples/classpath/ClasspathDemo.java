package com.classLoadingSamples.classpath;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClasspathDemo {
	private static void loadWithCurrentLoader() throws Exception {
		// ClassLoader currentLoader = ClasspathDemo.class.getClassLoader();
		Class.forName("com.classLoadingSamples.classpath.Alice"); // Class.forName("Alice", true, currentLoader)
		try {
			Class.forName("com.classLoadingSamples.classpath.Bob"); // Class.forName("Bob", true, currentLoader)
		} catch (ClassNotFoundException e) {
			System.out.println("class Bob cannot be found by the current loader");
		}
	}

	private static void loadWithCustomLoader() throws Exception {
		ClassLoader loader = new URLClassLoader(new URL[] { new File("com.classLoadingSamples.classpath.bob").toURI().toURL() });
		try {
			Class.forName("com.classLoadingSamples.classpath.Bob", true, loader);
		} catch (ClassNotFoundException e) {
			System.out.println("class Bob cannot be found by the custom loader");
		}
	}

	public static void main(String[] args) throws Exception {
		loadWithCurrentLoader();
		loadWithCustomLoader();
		System.in.read();
	}
}

/*
 * java -classpath ".;alice" -verbose ClasspathDemo
 * 
 * [Loaded ClasspathDemo from file:/D:/test/ClassLoadingSamples/1_Classpath/]
 * [Loaded Alice from file:/D:/test/ClassLoadingSamples/1_Classpath/alice/]
 * class Bob cannot be found by the current loader ... [Loaded Bob from
 * file:/D:/test/ClassLoadingSamples/1_Classpath/bob/]
 */
