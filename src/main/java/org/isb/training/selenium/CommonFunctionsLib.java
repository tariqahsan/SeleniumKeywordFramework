package org.isb.training.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctionsLib {

	final static Logger logger = Logger.getLogger(CommonFunctionsLib.class);

	WebDriver driver;

	CommonFunctionsLib(WebDriver driver) {
		this.driver = driver;
	}

	public boolean performActions(String keyword, String value, String element)
			throws NumberFormatException, InterruptedException {
		logger.info("keyword: " +  keyword + " value: " + value + " element:  " + element);
		//System.out.println("keyword: " + keyword + " value: " + value + " element:  " + element);
		if (keyword.equalsIgnoreCase("Click")) {
			driver.findElement(By.xpath(element)).click();
			logger.info("Click is performed sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("Input")) {
			driver.findElement(By.xpath(element)).sendKeys(value);
			logger.info("Input is performed sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("dropdown")) {
			Select dropDown = new Select(driver.findElement(By.xpath(element)));
			dropDown.selectByVisibleText(value);

		} else if (keyword.equalsIgnoreCase("Radio-Male")) {

			// Identifying Male radio button using its ID as an locator
			WebElement maleRadioBtn = driver.findElement(By.id(element));

			// Checking if the Male Radio button is displayed on the Webpage and printing the status
			boolean radioBtnIsDisplayed = maleRadioBtn.isDisplayed();
			System.out.println("Is Male radio button displayed: "+radioBtnIsDisplayed);

			// Checking if the Male Radio button is enabled on the webpage and printing the status
			boolean radioBtnIsEnabled = maleRadioBtn.isEnabled();
			System.out.println("Is Male radio button enabled: "+radioBtnIsEnabled);

			// Checking the default radio button selection status
			boolean radioBtnIsSelected = maleRadioBtn.isSelected();
			System.out.println("Default Radio button selection Status: "+radioBtnIsSelected);

			// Selecting male radio button
			maleRadioBtn.click();
			// Re-checking the male radio button selection status and printing it..

			boolean radioBtnNewSelectionStatus = maleRadioBtn.isSelected();
			System.out.println("Male radio Selection status after perform click() event: "+radioBtnNewSelectionStatus);
			
			return true;
			
		} else if (keyword.equalsIgnoreCase("Radio-Female")) {

			// Identifying Male radio button using its ID as an locator
			WebElement maleRadioBtn = driver.findElement(By.id(element));

			// Checking if the Male Radio button is displayed on the Webpage and printing the status
			boolean radioBtnIsDisplayed = maleRadioBtn.isDisplayed();
			System.out.println("Is Female radio button displayed: " + radioBtnIsDisplayed);

			// Checking if the Male Radio button is enabled on the webpage and printing the status
			boolean radioBtnIsEnabled = maleRadioBtn.isEnabled();
			System.out.println("Is Female radio button enabled: " + radioBtnIsEnabled);

			// Checking the default radio button selection status
			boolean radioBtnIsSelected = maleRadioBtn.isSelected();
			System.out.println("Default Radio button selection Status: "+radioBtnIsSelected);

			// Selecting male radio button
			maleRadioBtn.click();

			// Re-checking the male radio button selection status and printing it..
			boolean radioBtnNewSelectionStatus = maleRadioBtn.isSelected();
			System.out.println("Male radio Selection status after perform click() event: "+radioBtnNewSelectionStatus);
			
			return true;

		} else if (keyword.equalsIgnoreCase("Radio-exp")) {		

			// To prevent the error occuring during execution - 
			// unknown error: Element <input id="exp-1" name="exp" type="radio" value="2"> is not clickable at point (277, 660)...
			// The remedy to the above problem found in the YouTube video - https://www.youtube.com/watch?v=XNwcGNLk3cE
			// titled - How to Handle Element is Not Clickable at Point Exception in Selenium Webdriver.
			// According to the video it's been suggested to use - org.openqa.selenium.interactions.Actions class methods
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);

			WebElement expRadioBtn = driver.findElement(By.id(element));
			
			// Checking if the Male Radio button is displayed on the Webpage and printing the status
			boolean radioBtnIsDisplayed = expRadioBtn.isDisplayed();
			System.out.println("Is Exp radio button displayed: " + radioBtnIsDisplayed);

			// Checking if the Male Radio button is enabled on the webpage and printing the status
			boolean radioBtnIsEnabled = expRadioBtn.isEnabled();
			System.out.println("Is Exp radio button enabled: " + radioBtnIsEnabled);

			// Checking the default radio button selection status
			boolean radioBtnIsSelected = expRadioBtn.isSelected();

			System.out.println("Default Radio button selection Status: " + radioBtnIsSelected);
			
			// Selecting Exp (Experience) radio button

			// expRadioBtn.click(); // Commented out to execute the click function in the way below
			// Using expRadioBtn wrapped around Actions.moveToElement to prevent the error mentioned above
			actions.moveToElement(expRadioBtn).click().build().perform();
			
			// Re-checking the male radio button selection status and printing it..
			boolean radioBtnNewSelectionStatus = expRadioBtn.isSelected();
			System.out.println("Experience radio Selection status after perform click() event: " + radioBtnNewSelectionStatus);
			
			return true;
			
		} else if (keyword.equalsIgnoreCase("upload")) {
			System.out.println("in upload file ...");
			driver.findElement(By.id(element)).sendKeys(System.getProperty("user.dir") + "/TestCases/Allah.jpg");
			System.out.println("upload is performed sucessfully ...");
			logger.info("upload is performed sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("gettext")) {
			driver.findElement(By.xpath(element)).getText();
			logger.info("getText is performed sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("url")) {
			driver.get(value);  // 
			logger.info("Url is opened sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("CloseBrowser")) {
			driver.quit();
			logger.info("Browser is closed sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("sleep")) {
			if (value.equalsIgnoreCase("level1")) {
				Thread.sleep(3000);
			} else if (value.equalsIgnoreCase("level2")) {
				Thread.sleep(7000);
			} else if (value.equalsIgnoreCase("level3")) {
				Thread.sleep(15000);
			} else if (value.equalsIgnoreCase("level4")) {
				Thread.sleep(20000);
			} else if (value.equalsIgnoreCase("level4")) {
				Thread.sleep(25000);
			} else {
				Thread.sleep(5000);
			}
			logger.info("sleep is done sucessfully");
			return true;
		} else if (keyword.equalsIgnoreCase("VerifyText")) {

			if (value.equalsIgnoreCase(driver.findElement(By.xpath(element)).getText())) {
				logger.info("verify text true");
				return true;
			} else {
				logger.info("verify text failed");
				return false;
			}

		} else if (keyword.equalsIgnoreCase("Pop")) {
			// Do nothing
		}

		return false;
	}

}
