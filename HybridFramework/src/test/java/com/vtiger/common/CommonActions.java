package com.vtiger.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonActions {
	
	private WebDriver driver;
	
	public WebDriverWait wait;
	public ExtentTest logger;
	
	public CommonActions(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		this.logger = logger;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	

	public void enterValue(WebElement elm, String value, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(value);
		logger.pass(msg);
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Step failed due to = " + e.getMessage() + "<span class='label end-time><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public void clickElement(WebElement elm, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();
		logger.pass(msg);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Step failed due to = " + e.getMessage() + "<span class='label end-time><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public String getscreenshot()
	{
		Date d = new Date();
		DateFormat f = new SimpleDateFormat("ddMMyyyyhhmmss");
		String str = f.format(d);
		TakesScreenshot scrshot = ((TakesScreenshot) driver); // TakesScreenshot is an interface, Webdriver is also an interface. To use one interface in other, casting is done like this
		
		// Call getScreenshotAs method to create image file
		File srcfile = scrshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") +"/src/test/java/com/vtiger/reports/screenshots/image" + str + ".png";
		//Move image file to new destination
		File DestFile = new File(path);
		//Copy file at destination
		try {
			FileUtils.copyFile(srcfile, DestFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	
	public void elementExist(WebElement elm, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isDisplayed();
		logger.pass(msg);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Step failed due to = " + e.getMessage() + "<span class='label end-time><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public void getTextValidate(WebElement elm, String exp, String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.getText();
		if(exp.equals(elm.getText()))
		{
			logger.pass(msg);
		}
		else
		{
			logger.fail("Comparison failed, expected data was " + exp + " and found " +elm.getText());
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Step failed due to = " + e.getMessage() + "<span class='label end-time><a href='"+getscreenshot()+"'>Screenshot</a></span>");
		}
	}
}
