package org.isb.training.selenium;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

	WebDriver driver;
	ConfigFileReader configFileReader;
	
	public WebDriver InitateDriver()
	{
		//System.setProperty("webdriver.gecko.driver","./geckodriver/geckodriver.exe");
		configFileReader= new ConfigFileReader();
		System.out.println("Configuration File Reader : Driver Path -> " + configFileReader.getDriverPath());
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		//System.setProperty("webdriver.chrome.driver","./webdriver/chromedriver.exe");
		// driver=new FirefoxDriver();
		driver = new ChromeDriver();
		// System.setProperty("webdriver.chrome.driver","E:\\selenium\\chromedriver\\chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
		//driver=new FirefoxDriver();
		//driver.manage().window().setSize(screenResolution);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}


}
