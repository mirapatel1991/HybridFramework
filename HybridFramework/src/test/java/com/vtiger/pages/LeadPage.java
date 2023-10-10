package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class LeadPage extends HeaderPage{
	
	
	
	public LeadPage(WebDriver driver, ExtentTest logger)
	{
		super(driver,logger);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (name = "lastname")
	WebElement tb_lastname;
	
	@FindBy (name = "company")
	WebElement tb_company;
	
	@FindBy (xpath = "//*[text()='Last Name:']/following::td[1]")
	WebElement lbl_lastname;
	
	@FindBy (name = "button")
	WebElement btn_button;
	
	@FindBy (xpath = "//*[text()='Company:']/following::td[1]")
	WebElement lbl_company;
	
	public void createLeadWithMAndatoryFields(String lname, String comp)
	{
		ca.enterValue(tb_lastname, lname, lname + " has been entered into last name field.");
		ca.enterValue(tb_company, comp, comp + " has been entered into company field.");
		ca.clickElement(btn_button, "Save button clicked.");

	}
	
	public void verifyLeadCreation(String lname, String comp)
	{
		ca.getTextValidate(lbl_lastname, lname, lname + " has been verified against label lastname.");
		ca.getTextValidate(lbl_company, comp, comp + " has been verified against label company.");
	}
	

}
