package com.vtiger.test;

import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	
	
	@Test
	public void TC01_LoginWithInvalidCredentials()
	{
		
		String TCName = "TC01_InvalidLogin";
		logger = extent.createTest(TCName); // for report generation
		
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(td.get(TCName).get("UserID"), td.get(TCName).get("Password"));
		lp.verifyErrorMsg();
		extent.flush();
	}
	
	@Test
	public void TC01_LoginWithValidCredentials()
	{
		String TCName = "TC02_ValidLogin";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver, logger);
		lp.login(prop.getProperty("UserID"), prop.getProperty("Password"));
		HomePage hp = new HomePage(driver, logger);
		hp.verifyLogout();
		hp.clickLogout();
		extent.flush();
		
	}

}
