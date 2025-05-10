package com.naukri.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	
	WebDriver ldriver;
	
	// Constructor
	public HomePage(WebDriver rdriver) 
	{
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(xpath = "//div[@class='crossIcon chatBot chatBot-ic-cross']")
	WebElement chat_close_icon; 
	
	@FindBy(xpath = "//a[normalize-space()='View profile']")
	WebElement view_profile_link;
	

	public void clickChatCloseIcon() 
	{
		if (chat_close_icon.isDisplayed()) 
		{
			chat_close_icon.click();
		}
	}
	
	public void clickViewProfileLink()
	{
		view_profile_link.click();
	}
}
