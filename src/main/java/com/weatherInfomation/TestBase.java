package com.weatherInfomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestBase {
	
	public static  WebDriver driver;
	static Properties prop;
	static File file;
	static FileInputStream fileinputStream;
	static String projectPath;
	

	public static WebDriver getDriver() throws IOException
	{
		projectPath = System.getProperty("user.dir");
		file = new File(projectPath+"/config.properties");
		fileinputStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(fileinputStream);
		
		System.setProperty("webdriver.chrome.driver", projectPath+"/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.get("URL").toString());
		return driver;
	}

}
