package com.testng.listner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.Document;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openxml4j.document.wordprocessing.Run;

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
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		String dateScreenShot = sdf.format(dt);
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		String dest = System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+ScreenshotName+"_"+dateScreenShot+".png";
		File destination = new File(dest);
		File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,destination);
		return dest;
	}
	
	public static void getIndividualScreenshot(ArrayList<String> imgList,String screenshotName) throws InvalidFormatException, IOException, InterruptedException {
		XWPFDocument docx = new XWPFDocument();
		Date dd = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		String ddFileName = sdf.format(dd);
		
		String path = System.getProperty("user.dir") + "\\test-output\\" + screenshotName;
		File filePath = new File(path);
		
		if (!(filePath.exists())) {
			filePath.mkdir();
		}
		String fileOutputPath = path+"\\"+screenshotName+"_"+ddFileName+".docx";
		FileOutputStream fout = new FileOutputStream(path+"\\"+screenshotName+"_"+ddFileName+".docx");		
		XWPFParagraph para = docx.createParagraph();
		XWPFRun run = para.createRun();
		for (int i=0;i<imgList.size();i++) {
			FileInputStream ins = new FileInputStream(imgList.get(i)); 
			run.addBreak();
			run.setText("Screenshot " + i );
			run.addBreak();
			run.addPicture(ins, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, imgList.get(i), Units.toEMU(450), Units.toEMU(450));
			run.addBreak();
		}
		run.setText("FYI The Document is available under - " +fileOutputPath);
		docx.write(fout);
		Thread.sleep(3000);
	}

}
