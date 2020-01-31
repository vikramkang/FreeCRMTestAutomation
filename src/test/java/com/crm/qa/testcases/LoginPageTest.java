package com.crm.qa.testcases;

import java.io.IOException;
import java.nio.file.Path;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	// Parameter Instantiation
	LoginPage loginPage;
	HomePage homePage = new HomePage();
	Path path;
	
	// Constructor with Super keyword to instantiate parent class attributes
	// This is required to call parent class constructor which will initialize properties "prop" keyword
	// which is used in initialization method and also later in method loginTest
	public LoginPageTest() {
		super();
	}
		
	//Test cases
	@BeforeMethod
	public void setUp(){
		log.info("Logging Browser");
		initialization();
		loginPage = new LoginPage();// This creation of class object is required to avoid null pointer exception when login page is called later 
		log.info("Login to application");
	}
	@Test
	public void loginPageTitleTest(){
	
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM #1 cloud software for any business large or small");
		
	}
	@Test 
	public void crmLogoImageTest(){
		
		boolean status = loginPage.validateCRMImage();
		Assert.assertTrue(status);
		
	}
	@Test
	public void loginTest(){
		
		homePage= loginPage.login(prop.getProperty("username"),  prop.getProperty("password"));
		Assert.assertEquals(driver.getTitle(), "Cogmento CRM");
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException, IOException{
		
		if (result.getStatus()==ITestResult.FAILURE) {
			path =captureScreenshot(driver, "LoginPageTest");
		}
		driver.quit();
	}
	
}
