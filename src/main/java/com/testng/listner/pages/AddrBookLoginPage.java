package com.testng.listner.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testng.listner.Base.BaseClass;

public class AddrBookLoginPage extends BaseClass  {
	
	@FindBy(linkText="Sign in")
	WebElement signInLink;
	
	@FindBy(id="session_email")
	WebElement userEmailAddr;
	
	@FindBy(id="session_password")
	WebElement userAddrPwd;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement signInSubmit;
	
	@FindBy(xpath="//div[@class='text-center']/h1")
	WebElement getAddBookHomePageText;
	
	public AddrBookLoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public String getAddrBookHomePageTitle() {
		return driver.getTitle();
	}
	
	public String getAddBookHomePageText() {
		return getAddBookHomePageText.getText();
	}
	
   public AddrBookMyAccountPage loginAddrBookPage(String username , String password) throws IOException {
	   signInLink.click();
	   userEmailAddr.sendKeys(username);
	   userAddrPwd.sendKeys(password);
	   signInSubmit.click();
	   return new AddrBookMyAccountPage();
   }
}
