package com.naukri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	
	WebDriver ldriver;
	
	// Constructor
	public LoginPage(WebDriver rdriver) 
	{
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(id = "login_Layer")
	WebElement login_link;

	@FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
	WebElement email_field;
	
	@FindBy(xpath = "//input[@type='password']")
	WebElement password_field;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_btn;
	
	
	public void clickLoginLink() 
	{
		login_link.click();
	}
	
	public void enterEmail(String email) 
	{
		email_field.sendKeys(email);
	}
	
	public void enterPassword(String pwd) 
	{
		password_field.sendKeys(pwd);
	}
	
	public void clickLoginBtn() 
	{
		login_btn.click();
	}
}
