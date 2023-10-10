package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.CommonActions;

public class HomePage extends HeaderPage{
	
	
	
	
	public HomePage(WebDriver driver, ExtentTest logger)
	{
		super(driver,logger);
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(name = "pipeline")
	WebElement dashboard_pipeline;
	
	public void verifyLogout()
	{
		ca.elementExist(dashboard_pipeline, "Dashboard pipeline exists on Home page");
	}
	
	


}
