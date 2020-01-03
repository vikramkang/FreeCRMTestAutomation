package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase{
	String nameOfUser=prop.getProperty("nameOfUser");
	TestUtil testUtil;
	
	@FindBy(xpath="//span[contains(text(), nameOfUser)]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//span[contains(text(), 'Contacts')]")
	WebElement contacts;
	
	@FindBy(xpath="//span[contains(text(), 'Deals')]")
	WebElement deals;
	
	// Constructor
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	

	// Actions/Methods 
	public boolean verifyUserNameLabel(){
		return userNameLabel.isDisplayed();
	}
		
	public ContactsPage clickOnContactsLink(){
		contacts.click();
		return new ContactsPage();	
	}
	 public DealsPage clickOnDealsLink(){
		 deals.click();
		 return new DealsPage();
	 }
}