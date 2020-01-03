package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
		
	
	//Page Factory Model
	// The syntax mentioned below is equivalent to driver.findelement.By.xpath() 
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	WebElement loginBtn;
	
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[contains(@class,'submit button')]")
	WebElement submitBtn;
	
	@FindBy(xpath="//button[contains(text(), 'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//div[@class='rd-navbar-panel']//a[@class='brand-name']")
	WebElement crmLogo;
	
	// Login Page Constructor to initialize the Page Factory Elements
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
	
	
	//Actions: Defining methods/Accessing features of page
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();
	}
	public HomePage login(String un, String pwd){
		loginBtn.click();
		username.sendKeys(un);
		password.sendKeys(pwd);
		submitBtn.click();
		return new HomePage();
	}
	

}
