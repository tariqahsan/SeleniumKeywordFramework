package org.isb.training.selenium;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// creates an object which is read the configuration properties files under Config folder
public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "config//configuration.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");// name has to be the same as in configuration properties files
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the configuration.properties file.");		
	}
	
	public String getTestSuite(){
		String driverPath = properties.getProperty("testSuitePath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("testSuitePath not specified in the configuration.properties file.");		
	}
	
	public String getTestSuiteSheet(){
		String testSuiteSheetName = properties.getProperty("testSuiteSheetName");
		if(testSuiteSheetName!= null) return testSuiteSheetName;
		else throw new RuntimeException("testSuiteSheetName not specified in the configuration.properties file.");		
	}
	public String getTestCasePath(){
		String testCasePath = properties.getProperty("testCasePath");
		if(testCasePath!= null) return testCasePath;
		else throw new RuntimeException("testCasePath not specified in the configuration.properties file.");		
	}
	
	public String getTestStepsPath(){
		String testCaseStepsPath = properties.getProperty("testCaseStepsPath");
		if(testCaseStepsPath!= null) return testCaseStepsPath;
		else throw new RuntimeException("testCaseStepsPath not specified in the configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the configuration.properties file.");
	}

}
