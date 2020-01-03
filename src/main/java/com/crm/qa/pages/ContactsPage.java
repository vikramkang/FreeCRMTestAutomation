package com.crm.qa.pages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	//PageFactory
	@FindBy(xpath="//button[contains(text(),'Export')]")
	WebElement exportButton;
	
	@FindBy(xpath="//button[contains(text(),'Show Filters')]")
	WebElement showFiltersButton;
	
	@FindBy(xpath="//button[contains(text(),'New')]")
	WebElement newButton;
	
	@FindBy(xpath="//input[@name='first_name']")
	WebElement firstNameField;
	
	@FindBy(xpath="//input[@name='last_name']")
	WebElement lastNameField;
	
	@FindBy(xpath="//div[@name='company']")
	WebElement companyField;
	
	@FindBy(xpath="//div[@name='company']//input[@class='search']")
	WebElement companyFieldInput;
	
	@FindBy(xpath="//button[@class='ui small fluid positive toggle button']")
	WebElement accessField;
	
	@FindBy(xpath="//div[contains(text(),'Select users allowed access')]")
	WebElement selectUsersField;
	
	@FindBy(xpath="//span[contains(text(),'vikram kang')][@class='text']")
	WebElement selectUserFieldName;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	@FindBy(xpath="//i[@class='large user red icon']")
	WebElement redIconButton;
	
	//Constructor to initialize WebElements
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	public boolean validateExportButton(){
		return exportButton.isDisplayed();
	}
	
	public boolean validateShowFiltersButton(){
		return showFiltersButton.isDisplayed();
	}
	public boolean validateNewButton(){
		return newButton.isDisplayed();
	}
	
	public boolean createNewContact(String firstName, String lastName, String company){
		newButton.click();
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		companyField.click();
		companyFieldInput.sendKeys(company);
		try {accessField.click();
		selectUsersField.click();
		selectUserFieldName.click();} 
		catch (ElementNotVisibleException e) {
			System.out.println("Private Field already Selected");
		}
		saveButton.click();
		return(redIconButton.isDisplayed());	
	}
	

}
