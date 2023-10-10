package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.CommonActions;

public class HeaderPage {
	
	public WebDriver driver;
	public CommonActions ca;
	
	public HeaderPage(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		ca = new CommonActions(driver, logger);
		
	}
	
	@FindBy(linkText = "Logout")
	WebElement lnk_logout;
	
	@FindBy (linkText = "New Lead")
	WebElement lnk_newlead;
	
	@FindBy (linkText = "Leads")
	WebElement lnk_leads;
	
	@FindBy (linkText = "New Account")
	WebElement lnk_newaccount;
	
	
	
	public void verifyLogout()
	{
		ca.elementExist(lnk_logout, "Logout link exists on Home page");
	}
	
	public void clickLogout()
	{
		ca.clickElement(lnk_logout, "Logout link clicked");
	}
	
	public void clickNewLead()
	{
		ca.clickElement(lnk_newlead, "New Lead link clicked");
	}
	
	public void clickLeads()
	{
		ca.clickElement(lnk_leads, "Leads link clicked");
	}
	
	public void clickNewAccount()
	{
		ca.clickElement(lnk_newaccount, "New Account link clicked");
	}
	



}
