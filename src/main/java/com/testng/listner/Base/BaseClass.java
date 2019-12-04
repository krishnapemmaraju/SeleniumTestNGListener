package com.testng.listner.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.testng.listner.utils.TestUtils;
import com.testng.listner.utils.WebEventListener;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver edriver;
	public static WebEventListener elistener;
	
	public BaseClass() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\testng\\listner\\config\\config.properties");
		prop.load(fis);
	}
	
	public void initialization(String browser) throws IOException {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\KrishnaData\\Selenium\\chromedriver_win3277V\\chromedriver.exe"); 
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICT_PAGE_TIMEOUT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","C:\\KrishnaData\\Selenium\\geckodriver-v0.25.0-win64\\geckodriver.exe"); 
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICT_PAGE_TIMEOUT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
		if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver","C:\\KrishnaData\\Selenium\\edgedriver_win64 (1)\\msedgedriver.exe"); 
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICT_PAGE_TIMEOUT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
		
		edriver = new EventFiringWebDriver(driver);
		elistener = new WebEventListener();
		edriver.register(elistener);
		driver=edriver;
	}
	
}
