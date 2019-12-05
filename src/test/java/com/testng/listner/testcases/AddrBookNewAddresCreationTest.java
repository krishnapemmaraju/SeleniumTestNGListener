package com.testng.listner.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testng.listner.Base.BaseClass;
import com.testng.listner.pages.AddrBookLoginPage;
import com.testng.listner.pages.AddrBookMyAccountPage;
import com.testng.listner.utils.TestUtils;

public class AddrBookNewAddresCreationTest extends BaseClass {
	   AddrBookMyAccountPage myAccountPage;
	   AddrBookLoginPage myAddrBookLoginPage;
	   ExtentReports extReports;
	   ExtentTest extLogger;

	public AddrBookNewAddresCreationTest() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setExtentReports() {
		extReports = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\MyTestReport.html",true);
		extReports.addSystemInfo("UserName", "Krishna");
		extReports.addSystemInfo("Platform","Windows");
		extReports.addSystemInfo("Java Version","1.8");
	}
	
	@AfterTest
	public void endReport() {
		extReports.close();
	}
	
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws IOException {
		initialization(browser);
		myAddrBookLoginPage = new AddrBookLoginPage();
		myAccountPage = myAddrBookLoginPage.loginAddrBookPage(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void validatePageTitle() {
		extLogger=extReports.startTest("My Account Page Title Validation");
		Assert.assertEquals(myAccountPage.getMyAccAddrBookTitle(), "Addressss Book");
	}
	
	@DataProvider
	public Object[][] getDataFromExcel() throws EncryptedDocumentException, IOException {
		Object data[][] =  TestUtils.getExcelData("NewAddressCreation");
		return data;
	}
	
	@Test(priority=2,dataProvider = "getDataFromExcel")
	public void addNewAddressCust(String addrFirstName, String addrLastName,String newAddrStreetAddrData,String newAddrSecondaryAddress,String newAddrCityData,String newAddrStateData,String newAddrzipCode,String newAddrBirthDayData,String newAddrAgeData,String newAddrMobilePhone) throws IOException, InvalidFormatException, InterruptedException {
		extLogger=extReports.startTest("New Customer Address Creation");
		myAccountPage.AddNewAddressData(addrFirstName,addrLastName,newAddrStreetAddrData,newAddrSecondaryAddress,newAddrCityData,newAddrStateData,newAddrzipCode,newAddrBirthDayData,newAddrAgeData,newAddrMobilePhone);
	    Assert.assertEquals(myAccountPage.getSuccesMessageText(), "Address was successfully created.");
	}
	
	/*@Test(priority=3)
	public void SkippingMethod() {
		extLogger= extReports.startTest("Skipping Method");
		throw new SkipException("This method is Skipped");
	}*/
	
	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		if ( result.getStatus() == ITestResult.FAILURE) {
			extLogger.log(LogStatus.FAIL, result.getName() + "Got Failed");
			extLogger.log(LogStatus.FAIL,result.getThrowable());
			extLogger.log(LogStatus.FAIL,extLogger.addScreenCapture(TestUtils.takeScreenShot(result.getName()+"_Failed")));
		} else if ( result.getStatus() == ITestResult.SUCCESS) {
			extLogger.log(LogStatus.PASS, result.getName() + "Got PASSED");
			extLogger.log(LogStatus.PASS,extLogger.addScreenCapture(TestUtils.takeScreenShot(result.getName()+"_Passed")));
		} else if( result.getStatus() == ITestResult.SKIP) {
			extLogger.log(LogStatus.SKIP, "Method Skipped is " + result.getName());
		}
		extReports.endTest(extLogger);
		driver.quit();
	}

}
