package com.naukri.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage
{

	WebDriver ldriver;
	
	// Constructor
	public ProfilePage(WebDriver rdriver) 
	{
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
		
	@FindBy(xpath = "//span[@class='text' and text()='Career profile']")
	WebElement career_profile_listItem;
	
    @FindBy(xpath = "//span[text()='Career profile']//following-sibling::span[1]")
	WebElement career_profile_edit_icon;
    
    @FindBy(xpath = "//form[@id='desiredProfileForm']")
	WebElement career_profile_form;
    	
	@FindBy(xpath = "//label[normalize-space()='Contractual']")
	WebElement contractual_checkB;
		
	@FindBy(xpath = "//button[@id='saveDesiredProfile']")
	WebElement save_btn;
	
	@FindBy(xpath = "//span[@class='typ-14Medium mod-date-val']")
	WebElement profile_updated_status;
	
	@FindBy(xpath = "//span[@class='text' and text()='Resume']")
	WebElement resume_listItem;
	
	@FindBy(xpath = "//input[@type='file' and @id='attachCV']")
	WebElement update_resume_btn;
	
	@FindBy(xpath = "//div[@class='truncate exten']")
	WebElement resume_name_txt;
	
	@FindBy(xpath = "//div[@class='updateOn typ-14Regular']")
	WebElement resume_updated_date_txt; 
	
	@FindBy(xpath = "//div[@class='nI-gNb-drawer__icon']")
	WebElement logout_img;
	
	@FindBy(xpath = "//a[@title='Logout']")
	WebElement logout_link;

	
	public void clickCareerProfileListItem()
	{
		career_profile_listItem.click();
	}
	
	public void clickCareerProfileEditIcon()
	{
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", career_profile_edit_icon);
		career_profile_edit_icon.click();
	}
		
	public boolean isCareerProfileFormPresent()
	{
		return (career_profile_form.isDisplayed());
	}
		
	public void clickContractualCheckB()
	{
		contractual_checkB.click();
	}
	
	public void clickSaveBtn()
	{
		save_btn.click();
	}
		
	public String getProfileUpdatedStatus()
	{
		return (profile_updated_status.getText());
	}
	
	public void clickResumeListItem()
	{
		resume_listItem.click();
	}
	
	public void uploadResume(String filePath) throws AWTException, InterruptedException
	{
		//Converting relative file path to absolute path 
		File file = new File(filePath);
		String absolutePath = file.getAbsolutePath();
		System.out.println("Absolute path = " + absolutePath);
		
		Thread.sleep(3000);
		update_resume_btn.sendKeys(absolutePath);
		Thread.sleep(3000);
	}
	
	public String getResumeName() 
	{
		return (resume_name_txt.getText());  
	}
	
	public String getResumeUpatedOnDate()
	{
		return (resume_updated_date_txt.getText());
	} 
		
	public void clickLogoutImg()
	{
		logout_img.click();
	}
	
	public void clickLogoutLink()
	{
		logout_link.click();
	}
}
