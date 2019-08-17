package org.isb.training.selenium;

import java.io.IOException;

import org.apache.log4j.Logger;
//import org.zia.training.selenium.keyword.Automation;
//import org.zia.training.selenium.keyword.Driver;
//import org.zia.training.selenium.keyword.ExcelSheetDriver;
import org.openqa.selenium.WebDriver;
import org.isb.training.selenium.CommonFunctionsLib;
import org.isb.training.selenium.ExcelSheetDriver;

import jxl.Sheet;
import jxl.read.biff.BiffException;

public class Automation2 {

	final static Logger logger = Logger.getLogger(Automation2.class);
	
	public static void main(String[] args) throws BiffException, IOException, NumberFormatException, InterruptedException {

		Driver d = new Driver();
		ConfigFileReader configFileReader = new ConfigFileReader();
		ExcelSheetDriver excelSheetDriver = new ExcelSheetDriver();
		ExcelSheetDriver excelSheetDriver1 = new ExcelSheetDriver();
		ExcelSheetDriver excelSheetDriver2 = new ExcelSheetDriver();
		
		Sheet testSuitesheet = excelSheetDriver.getWorksheet(configFileReader.getTestSuite(), configFileReader.getTestSuiteSheet());
		int c = excelSheetDriver.columnCount();
		int r = excelSheetDriver.rowCount();
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
				int rTestcase = excelSheetDriver1.rowCount();
				int cTestCase = excelSheetDriver1.columnCount();

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
						int rowTestSteps = excelSheetDriver2.rowCount();
						int columnTestSteps = excelSheetDriver2.columnCount();
						WebDriver driver = d.InitateDriver();
						CommonFunctionsLib comlib = new CommonFunctionsLib(driver);
						for(int w = 1; w < rowTestSteps; w++)
						{
							String snoTestSteps = excelSheetDriver.readCell(testStepsheet,0, w);
							String testStepcaseNumber = excelSheetDriver.readCell(testStepsheet,1, w);
							String desTestSteps = excelSheetDriver.readCell(testStepsheet,2, w);
							String xpathTestSteps = excelSheetDriver.readCell(testStepsheet,3, w);
							String value = excelSheetDriver.readCell(testStepsheet,4, w);
							String keywordTestSteps = excelSheetDriver.readCell(testStepsheet,5, w);
							if(testCaseNumber.equalsIgnoreCase(testStepcaseNumber)){
								logger.info("snoTestSteps:" + snoTestSteps);
								logger.info("desTestSteps:" + desTestSteps);
								logger.info("xpathTestSteps:" + xpathTestSteps);
								logger.info("value:" + value);
								logger.info("keywordTestSteps:" + keywordTestSteps);
								
//								logger.info("Executing performActions Method with the three arguments -" + keywordTestSteps + " " + value + " "+  xpathTestSteps);
								System.out.println("Executing performActions Method with the three arguments -" + "Keyword :" + keywordTestSteps + " " + "Value: " + value + " "+ "Xpathexpression: " +xpathTestSteps);
								comlib.performActions(keywordTestSteps, value, xpathTestSteps);
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

