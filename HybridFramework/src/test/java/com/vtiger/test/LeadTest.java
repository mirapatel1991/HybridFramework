package com.vtiger.test;

import org.testng.annotations.Test;

import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class LeadTest extends BaseTest {
	
	@Test
	public void TC03_CreateLeadWithMandatoryFields()
	{
		String TCName = "TC03_CreateLeadWithMandatoryFields";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver, logger);
		lp.login(td.get(TCName).get("UserID"), td.get(TCName).get("Password"));
		LeadPage lead = new LeadPage(driver,logger);
		lead.clickNewLead();
		lead.createLeadWithMAndatoryFields(td.get(TCName).get("Lname"),td.get(TCName).get("Company"));
		lead.verifyLeadCreation(td.get(TCName).get("Lname"),td.get(TCName).get("Company"));
		lead.clickLogout();
		extent.flush();
	}


}
