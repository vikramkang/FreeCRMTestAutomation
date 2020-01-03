package com.crm.qa.testcases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	
	public HomePageTest(){
		super();
		
	}
	//Test Cases
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void userNameLabelTest(){
		Assert.assertTrue(homePage.verifyUserNameLabel());
	}
	
	@Test
	public void clickOnContactsLinkTest(){
		contactsPage=homePage.clickOnContactsLink();
		Assert.assertEquals(driver.getCurrentUrl(), "https://ui.freecrm.com/contacts");
	}
	
	@Test
	public void clickOnDealsLinkTest(){
		dealsPage= homePage.clickOnDealsLink();
		Assert.assertEquals(driver.getCurrentUrl(), "https://ui.freecrm.com/deals");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException, IOException{
		if (result.getStatus()==ITestResult.FAILURE) {
			captureScreenshot(driver, "HomePageTest");			
		}
		driver.quit();
	}
}
