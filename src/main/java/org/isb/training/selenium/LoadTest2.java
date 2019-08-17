package org.isb.training.selenium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.read.biff.BiffException;

import org.isb.training.selenium.ConfigFileReader;
import org.isb.training.selenium.Driver;
import org.isb.training.selenium.CommonFunctionsLib;


public class LoadTest2 {
	
	final static Logger logger = Logger.getLogger(Automation.class);
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// copy from Test Automation src>driver.java> paste here chrome driver
		// executable file
		System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
		// driver=new FirefoxDriver();
//		driver = new ChromeDriver();
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifySearchButton() throws BiffException, IOException, NumberFormatException, InterruptedException {

		// creating class object Driver  
		Driver d = new Driver();  
		// creating class object for configFileReader 
		ConfigFileReader configFileReader = new ConfigFileReader();
		ExcelSheetDriver excelSheetDriver = new ExcelSheetDriver();
		ExcelSheetDriver excelSheetDriver1 = new ExcelSheetDriver();
		ExcelSheetDriver excelSheetDriver2 = new ExcelSheetDriver();
		
		// variable name testSuitesheet data type is Sheet
		Sheet testSuitesheet = excelSheetDriver.getWorksheet(configFileReader.getTestSuite(), configFileReader.getTestSuiteSheet());
		int c = excelSheetDriver.columnCount();  // 7
		int r = excelSheetDriver.rowCount();  // 44
		System.out.println(c + " " + r);

		for (int i = 1; i < r; i++) {
//			String SNo = excelSheetDriver.readCell(testSuitesheet,0, i);
			String SNo = excelSheetDriver.readCell(testSuitesheet, 0, i);

			String Description = excelSheetDriver.readCell(testSuitesheet, 1, i);
			String ExecutionFlag = excelSheetDriver.readCell(testSuitesheet, 2, i);
//			logger.info("TestSuite:" + SNo);
//			logger.info("TestSuite:" + Description);
//			logger.info("TestSuite:" + ExecutionFlag);
			System.out.println("TestSuite:" + SNo);
			System.out.println("TestSuite:" + Description);
			System.out.println("TestSuite:" + ExecutionFlag);

			if(ExecutionFlag.equalsIgnoreCase("Y")){
				Sheet TestCasesheet =  excelSheetDriver1.getWorksheet(configFileReader.getTestCasePath(), Description);
				System.out.println("TestCasesheet " + TestCasesheet.toString());
				int rTestcase = excelSheetDriver1.rowCount();  // 5 rows
				int cTestCase = excelSheetDriver1.columnCount();  // 4 columns

				for(int k = 1; k < rTestcase; k++)
				{
					String snoTestCase = excelSheetDriver1.readCell(TestCasesheet,0, k);
					String testCaseNumber = excelSheetDriver1.readCell(TestCasesheet,1, k);
					String testcaseDescription = excelSheetDriver1.readCell(TestCasesheet,2, k);
					String testcaseExecutionFlag = excelSheetDriver1.readCell(TestCasesheet,3, k);
					logger.info("TestCases:" + snoTestCase);
					logger.info("TestCases:" + testCaseNumber);
					logger.info("TestCases:" + testcaseDescription);
					logger.info("TestCases:" + testcaseExecutionFlag);


					if(testcaseExecutionFlag.equalsIgnoreCase("y")){

						Sheet testStepsheet = excelSheetDriver2.getWorksheet(configFileReader.getTestStepsPath(), Description);
						int rowTestSteps = excelSheetDriver2.rowCount();   // row count =44
						int columnTestSteps = excelSheetDriver2.columnCount();  // column counts=6
						WebDriver driver = d.InitateDriver();
						CommonFunctionsLib comlib = new CommonFunctionsLib(driver);
						for(int w = 1; w < rowTestSteps; w++)
						{
							String snoTestSteps = excelSheetDriver.readCell(testStepsheet,0, w);
							String testStepcaseNumber = excelSheetDriver.readCell(testStepsheet,1, w);
							String desTestSteps = excelSheetDriver.readCell(testStepsheet,2, w);
							String element = excelSheetDriver.readCell(testStepsheet,3, w);
							String value = excelSheetDriver.readCell(testStepsheet,4, w);
							String keywordTestSteps = excelSheetDriver.readCell(testStepsheet,5, w);
							if(testCaseNumber.equalsIgnoreCase(testStepcaseNumber)){
								logger.info("snoTestSteps:" + snoTestSteps);
								logger.info("desTestSteps:" + desTestSteps);
								logger.info("element:" + element);
								logger.info("value:" + value);
								logger.info("keywordTestSteps:" + keywordTestSteps);
								
//								logger.info("Executing performActions Method with the three arguments -" + keywordTestSteps + " " + value + " "+  element);
								System.out.println("Executing performActions Method with the three arguments -" + "keyword :" + keywordTestSteps + " " + "Value: " + value + " "+ "element: " + element);
								comlib.performActions(keywordTestSteps, value, element);
							}

						}

					}
				}

			}
		}

		excelSheetDriver.closeworkbook();
		excelSheetDriver2.closeworkbook();
		excelSheetDriver1.closeworkbook();

	}

}