package com.FlightBooking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WeatherInformationFromWebsite {
	
	ReadDataFromExcel readDataFromExcel;
	static List<String> list;
	static WebDriver driver;
	static String city;
	
	@FindBy(id = "h_sub_menu")
	private WebElement subMenu;
	
	@FindBy(linkText = "WEATHER")
	private WebElement weatherMenu;
	
	@FindBy(id="searchBox")
	private WebElement citySearchBox;
			
	@FindBy(className="cityText")
	private static List<WebElement> listOfShownCity;
	
	
	@FindBy(className ="heading")
	private static List<WebElement> weatherInformation;
	
	private static WebDriverWait wait;
	static Double cityTemperatureInDegree;

	
	static WebElement  selectedCityWeatherInformation;
	WebElement cityCheckbox;

	public WeatherInformationFromWebsite(String testcaseName) throws IOException{
		this.driver = TestBase.getDriver();
		PageFactory.initElements(driver, this);
		list = ReadDataFromExcel.getData(testcaseName);
		city=list.get(1);
	
	}
	
	public  void navigateToWeatherMenu(){
		subMenu.click();
		weatherMenu.click();
	}
	
	public void selectCity()
	{

		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(citySearchBox));
	
		citySearchBox.sendKeys(city);	
		citySearchBox.click();
		cityCheckbox = driver.findElement(By.id(city));
		if(cityCheckbox.isSelected())
		{
			System.out.println("City checkbox is already selected");
		}
		
		else
			
		{
			cityCheckbox.click();
		}
		
	}
	
		public static Double getCityWeatherTemperatureFromWeb() throws Exception
		{
			int listSize=listOfShownCity.size();
		
			for(int i=0;i<listSize;i++)
			{
			if(listOfShownCity.get(i).getText().equalsIgnoreCase(city) && listOfShownCity.get(i).isDisplayed())
			{
				System.out.println("City shown on map is:" +listOfShownCity.get(i).getText());
				selectedCityWeatherInformation= driver.findElement(By.xpath("//div[@title='"+city+"']/div/span"));
				wait.until(ExpectedConditions.visibilityOf(selectedCityWeatherInformation));
				selectedCityWeatherInformation.click();
				String temperatureValueInDegre = weatherInformation.get(3).getText();
				String [] cityTemperature=temperatureValueInDegre.split(":");
				cityTemperatureInDegree = Double.parseDouble(cityTemperature[1]);
				System.out.println("The city's temperature from NDTV website is: " +cityTemperatureInDegree);
				break;
			}
			}
					
			return cityTemperatureInDegree;
			
	}
		
}

	
	
	
	
	
	
	


