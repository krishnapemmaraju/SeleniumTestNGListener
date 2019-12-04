package com.testng.listner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import com.testng.listner.Base.BaseClass;

public class TestUtils extends BaseClass {
	
	public static Workbook workbook;
	public static Sheet sheet;
	
	public TestUtils() throws IOException {
		super();
	}

	public static int PAGE_LOAD_TIMEOUT = 30;
	public static int IMPLICT_PAGE_TIMEOUT = 30;
	
	public static void selectDropdownText(WebElement element,String optionType,String textValue) {
		Select selByText = new Select(element);
		if (optionType.equals("value")) {
			selByText.selectByValue(textValue);
		}
		if(optionType.equals("text")) {
			selByText.selectByVisibleText(textValue);
		}
		if(optionType.equals("index")) {
			selByText.selectByIndex(Integer.parseInt(textValue));
		}
	}
	
	public static Object[][] getExcelData(String sheetName) throws EncryptedDocumentException, IOException {
		System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\CustomerAddressData.xlsx");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\CustomerAddressData.xlsx");
		workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetName);
	
		
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for ( int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
	   return data;
	}	
  
	public static String takeScreenShot(String ScreenshotName) throws IOException {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("DDMMYYYYHHMMSS");
		String dateScreenShot = sdf.format(dt);
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		String dest = System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+ScreenshotName+"_"+dateScreenShot+".png";
		File destination = new File(dest);
		File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,destination);
		return dest;
	}

}
