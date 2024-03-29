package com.perficient.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestData {
	private static Properties resource = null;

	// it is used to load the test data file
	public static void load(String filename) {
		resource = new Properties();
		try {
			File file = new File("./testdata/" + filename);
			InputStream data_input = new FileInputStream(file);
			resource.load(data_input);
			System.out.println("Read test data file " + filename + "...");
		} catch (Exception e) {
			System.out.println("Warning: Not found data file " + filename);
		}

	}

	/**
	 * Objective: Get the test data, simple use TestData.get("args name");
	 * @param argName
	 */
	public static String get(String argName) {
		return resource.getProperty(argName).trim();
	}

	/**
	 * This method sets the property in properties file.
	 * @param argName
	 * @return
	 */
	public static void set(String key, String value) {
		 resource.setProperty(key, value);
	}
}
