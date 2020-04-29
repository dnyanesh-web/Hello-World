package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName)
	{
		if(browserName.equals("browser"))
		{
			System.setProperty("webdriver.gecko.driver","D:\\driver\\geckodriver.exe");
			
			if(prop.getProperty("headless").equals("yes"))
			{
				FirefoxOptions option = new FirefoxOptions();
				option.addArguments("--headless");
				driver = new FirefoxDriver(option);
				
				
			}else
			{
			
			WebDriver driver = new FirefoxDriver();
			}
		}
		return driver;
	}
	
	public Properties init_properties()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\DELL-PC\\eclipse-workspace\\KeywordDrivenTesting\\src\\main\\java\\com"
					       + "\\qa\\hubspot\\keyword\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
