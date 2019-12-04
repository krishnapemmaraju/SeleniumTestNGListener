package com.testng.listner.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testng.listner.Base.BaseClass;
import com.testng.listner.utils.TestUtils;

public class AddrBookMyAccountPage extends BaseClass {
	
	@FindBy(xpath="//*[@id='navbar']/div[2]/span")
	WebElement getMyAccAddrBookEmail;
	
	@FindBy(xpath="//*[@class='text-center']/h1")
	WebElement getmyAccAddrBookText;
	
	@FindBy(xpath="//*[@id='navbar']/div[2]/span")
    WebElement getEmailAddrData;
	
	@FindBy(linkText="Addresses")
	WebElement addrBookAddressLink;
	
	@FindBy(linkText="New Address")
	static
	WebElement addrBookNewAddrLink;
	
	@FindBy(id="address_first_name")
	WebElement newAddrFirstName;
	
	@FindBy(id="address_last_name")
	WebElement newAddrLastName;
	
	@FindBy(id="address_street_address")
	WebElement newAddrStreetAddr;
	
	@FindBy(id="address_secondary_address")
	WebElement newAddrSecondaryAddr;
	
	@FindBy(id="address_city")
	WebElement newAddrCity;
	
	@FindBy(id="address_state")
	WebElement newAddrState;
	
	@FindBy(id="address_zip_code")
	WebElement newAddrZipCode;
	
	@FindBy(id="address_country_us")
	WebElement newAddrCntry;
	
	@FindBy(id="address_birthday")
	WebElement newAddrBirthDay;
	
	@FindBy(id="address_age")
	WebElement newAddrAge;
	
	@FindBy(id="address_phone")
	WebElement newAddrPhone;
	
	@FindBy(id="address_interest_dance")
	WebElement newAddrCommInterest;
	
	@FindBy(xpath="//input[@value='Create Address']")
	WebElement newAddressCreate;
	
	@FindBy(xpath="//div[@class='alert alert-notice']")
	WebElement newAddrSuccessMessage;
	
	// Click on the Address Link Again and perform assertions
	
	@FindBy(xpath="//table[@class='table']/tbody/tr/td[1]")
	WebElement getFirstNameAddrBook;
	
	@FindBy(xpath="//table[@class='table']/tbody/tr/td[2]")
	WebElement getLastNameAddrBook;
	
	
	public AddrBookMyAccountPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public String getMyAccAddrBookTitle() {
		return driver.getTitle();
	}

	public String getMyAccValidEmailAddr() {
		return getMyAccAddrBookEmail.getText();
	}
	
	public String getMyAddrBookPageText() {
		return getmyAccAddrBookText.getText();
	}
	
	//Method to Click on New Address 
	
	public void AddNewAddressData(String addrFirstName,String addrLastName,String newAddrStreetAddrdata,String newAddrSecondaryAddress,String newAddrCityData,String newAddrStateData,String newAddrzipCode,String newAddrBirthDayData,String newAddrAgeData,String newAddrMobilePhone ) throws IOException {
		addrBookAddressLink.click();
		addrBookNewAddrLink.click();
		newAddrFirstName.sendKeys(addrFirstName);
		newAddrLastName.sendKeys(addrLastName);
		newAddrStreetAddr.sendKeys(newAddrStreetAddrdata);
		newAddrSecondaryAddr.sendKeys(newAddrSecondaryAddress);
		newAddrCity.sendKeys(newAddrCityData);
		TestUtils.selectDropdownText(newAddrState, "value", newAddrStateData);
		newAddrZipCode.sendKeys(newAddrzipCode);
		newAddrCntry.click();
		newAddrBirthDay.sendKeys(newAddrBirthDayData);
		newAddrAge.sendKeys(newAddrAgeData);
		newAddrPhone.sendKeys(newAddrMobilePhone);
		newAddrCommInterest.click();
		TestUtils.takeScreenShot("AddingNewAddress");
		newAddressCreate.click();
	}
	
	public String getSuccesMessageText() {
		return newAddrSuccessMessage.getText();
	}
}
