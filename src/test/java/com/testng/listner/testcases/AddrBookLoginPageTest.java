package com.testng.listner.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testng.listner.Base.BaseClass;
import com.testng.listner.pages.AddrBookLoginPage;
import com.testng.listner.pages.AddrBookMyAccountPage;

import org.testng.*;

public class AddrBookLoginPageTest extends BaseClass {
	AddrBookLoginPage addrBookloginPage;
	AddrBookMyAccountPage addrBookmyAccPage;
	
	public AddrBookLoginPageTest() throws IOException {
		super();
	}
	
	@BeforeTest
	@Parameters("browser")
	public void setUp(String browserName) throws IOException {
		initialization(browserName);
		addrBookloginPage = new AddrBookLoginPage();
		addrBookmyAccPage = addrBookloginPage.loginAddrBookPage(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void getAddrBookHomePageTextData() {
		Assert.assertEquals("Welcome to Address Book", addrBookloginPage.getAddBookHomePageText());
	}
	
	@Test(priority=2)
	public void getAddrBookHomePageTitle() {
		Assert.assertEquals("Address Book", addrBookloginPage.getAddrBookHomePageTitle());
	}
	
	@Test(priority=3)
	public void getMyAccEmailAddrData() {
		System.out.println(addrBookmyAccPage.getMyAccValidEmailAddr());
		Assert.assertEquals(prop.getProperty("username") ,addrBookmyAccPage.getMyAccValidEmailAddr());
	}
	
	@Test(priority=4)
	public void getMyAccPageTitle() {
		Assert.assertEquals("Address Book",addrBookmyAccPage.getMyAccAddrBookTitle());
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
