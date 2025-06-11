package com.naukri.testclasses;
import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.naukri.pageobjects.HomePage;
import com.naukri.pageobjects.LoginPage;
import com.naukri.pageobjects.ProfilePage;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TC_ProfilePageTest 
{
	WebDriver driver;
	
	@BeforeClass
	public void launchDriver () throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		// Initialize ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        options.addArguments("--disable-gpu"); // Applicable to Windows OS only
        options.addArguments("--window-size=1920,1080");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
                
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://www.naukri.com/");
		System.out.println("Url opened");
		Thread.sleep(4000);
	}
	
	
	@BeforeMethod
	@Parameters({"emailID", "password"})
	public void login()
	{
		LoginPage loginPg = new LoginPage(driver);
		
		loginPg.clickLoginLink();
		System.out.println("clicked on login link");
		loginPg.enterEmail("lengaresuraj@gmail.com");
		loginPg.enterPassword("Suraj@123");
		loginPg.clickLoginBtn();
		
		System.out.println("login successfully");
	}
	
	
	@Test(priority = 0)
	public void updateCareerProfile() throws InterruptedException
	{
		System.out.println("<-----Update Career Profile----->");

		HomePage homePg = new HomePage(driver);
		homePg.clickViewProfileLink();
		System.out.println("clicked on view profile");
				
		ProfilePage profilePg = new ProfilePage(driver);
		profilePg.clickCareerProfileListItem();
		System.out.println("clicked on Career profile list item");
		profilePg.clickCareerProfileEditIcon();
		System.out.println("clicked on edit icon");

		Thread.sleep(2000);
		
		if (profilePg.isCareerProfileFormPresent())
		{
			profilePg.clickContractualCheckB();
			System.out.println("clicked on contractual checkbx");
			Thread.sleep(2000);
			profilePg.clickSaveBtn();
			System.out.println("clicked on save btn");
			
			Thread.sleep(2000);
			
			String profileStatus = profilePg.getProfileUpdatedStatus();
			System.out.println("profile updated -> " + profileStatus);
			
			Assert.assertEquals(profileStatus, "Today");
		}
		else
		{
			System.out.println("career profile form is not present");
			Assert.assertTrue(false);		// intensionally failling the testcase 
		}
	}
	
	
	@Test(priority = 1)
	@Parameters({"resumePath"})
	public void updateResume(String resPath) throws AWTException, InterruptedException
	{
		System.out.println("<---------Update Resume--------->");
		
		HomePage homePg = new HomePage(driver);
		homePg.clickViewProfileLink();
		System.out.println("clicked on view profile");
		
		ProfilePage profilePg = new ProfilePage(driver);
		profilePg.clickResumeListItem();
		System.out.println("clicked on Resume list item");
		profilePg.uploadResume(resPath);
		System.out.println("resume uploaded");
		
		String resumeName = profilePg.getResumeName();
		System.out.println("resume name -> " + resumeName);
		
		Assert.assertTrue(resPath.contains(resumeName));
				
		String resumeStatus = profilePg.getResumeUpatedOnDate();
		String resumeDate = resumeStatus.substring(12);
		System.out.println("resume updated -> " + resumeDate);
		
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = currdate.format(formatter);
        
        Assert.assertEquals(resumeDate, formattedDate);
	}
	
	
	@AfterMethod
	public void logout() throws InterruptedException 
	{
		ProfilePage profilePg = new ProfilePage(driver);
		profilePg.clickLogoutImg();
		profilePg.clickLogoutLink();
		
		System.out.println("logout successfully");
		Thread.sleep(2000);
	}
	
	
	@AfterClass
	public void quitDriver()
	{
		driver.quit();
		System.out.println("driver quit");
	}
	
}
