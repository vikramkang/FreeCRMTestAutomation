package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage=new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage=homePage.clickOnContactsLink();
	}
	
	@Test
	public void validateExportButtonTest(){
		Assert.assertTrue(contactsPage.validateExportButton());
		
	}
	@Test
	public void validateShowFiltersButtonTest(){
		Assert.assertTrue(contactsPage.validateShowFiltersButton());
	}
	
	@Test
	public void validateNewButtonTest(){
		Assert.assertTrue(contactsPage.validateNewButton());
	}
	
	@DataProvider
	public Object[][] readTestData(){
		testUtil=new TestUtil();
		Object data[][]=TestUtil.getTestData(prop.getProperty("sheetName"));
		return data;
		
	} 
	
	@Test(dataProvider="readTestData")
	public void createNewContactTest(String firstName, String lastName, String company){
		Assert.assertTrue(contactsPage.createNewContact(firstName, lastName, company));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException, IOException{
		if (result.getStatus()==ITestResult.FAILURE) {
			captureScreenshot(driver, "ContactsPageTest");			
		}
		driver.quit();
	}
}
