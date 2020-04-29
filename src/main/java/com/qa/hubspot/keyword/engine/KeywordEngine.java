package com.qa.hubspot.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public Base base;
	public WebElement element;
	
	
	public final String SCENARIO_SHEET_PATH = "C:\\Users\\DELL-PC\\eclipse-workspace\\KeywordDrivenTesting\\src\\main\\java\\com\\"
			+ "qa\\hubspot\\keyword\\scenarios\\hubspot_scenario.xlsx";
	
	
	public void startExecuttion(String sheetName)
	{
		String locatorName = null;
		String locatorValue = null;		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		int k = 0;
		for(int i = 0; i<sheet.getLastRowNum();i++)
		{
			try {
			String locatorColValue=sheet.getRow(i+1).getCell(k+1).toString().trim();//locators column
			
			if(!locatorColValue.equalsIgnoreCase("NA"))
			{
				locatorName = locatorColValue.split("=")[0].trim();//id-
				locatorValue = locatorColValue.split("=")[1].trim();//username
			}
			
			String action =sheet.getRow(i+1).getCell(k+2).toString().trim();//action column
			String value =sheet.getRow(i+1).getCell(k+3).toString().trim();//value columb
			
		switch (action) {
		case "open browser":
			base = new Base();
			base.init_properties();
				if(value.isEmpty() || value.equals("NA"))
				{
					driver = base.init_driver(prop.getProperty("browser"));
				}else
				{
					driver = base.init_driver(value);
				}
			
			break;
			
		case "enter url":
			if(value.isEmpty() || value.equals("NA"))
			{
				driver.get(prop.getProperty("url"));
			}
			else
			{
				driver.get(value);
			}
			break;
		case "quit":
			driver.quit();
			break;
			
			

		default:
			break;
		}
		
		switch (locatorName) {
		case "id":
			 element = driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys"))
				{
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				locatorName = null;
			break;
			
		case "linkText":
			element = driver.findElement(By.linkText(locatorValue));
			element.click();
			locatorName = null;
		default:
			break;
		}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
	}

}
