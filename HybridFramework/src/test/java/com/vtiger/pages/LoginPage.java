package com.vtiger.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.CommonActions;

public class LoginPage {
	
	private WebDriver driver;
	private CommonActions ca;
	
	
	
	// instead of whole code like driver.findElement(By.name("user_name")).clear();
	// 1. String username = "user_name"; then write driver.findElement(By.name(username)).clear();
	// 2. By username =  By.name("user_name"); then write driver.findElement(username).clear();
	// 3. Page factory concept as follows:
	
	public LoginPage(WebDriver driver, ExtentTest logger)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		ca = new CommonActions(driver,logger);
		
	}
	
	@FindBy(name = "user_name")
	WebElement tb_username;
	
	@FindBy(name = "user_password")
	WebElement tb_userpassword;
	
	@FindBy(name = "Login")
	WebElement btn_login;
	
	@FindBy (xpath = "//img[@src='include/images/vtiger-crm.gif']")
	WebElement img_logo;
	
	@FindBy (xpath = "//*[contains(text(),'You must specify a valid username and password.')]")
	WebElement msg_error;
	
	public void login(String userid, String pwd)
	{
		ca.enterValue(tb_username, userid, userid + " has been entered into Username field");
		ca.enterValue(tb_userpassword, pwd, "Password has been entered in Password field");
		ca.clickElement(btn_login, "Login button clicked");
	}
	
	public void verifyLogo()
	{
		ca.elementExist(img_logo, "Logo is displayed on login page");
	}
	
	public void verifyErrorMsg()
	{
		ca.elementExist(msg_error, "Error message validated successfully");
	}

}
