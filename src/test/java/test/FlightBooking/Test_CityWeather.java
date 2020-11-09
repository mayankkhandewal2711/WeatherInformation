package test.FlightBooking;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



import com.FlightBooking.*;
public class Test_CityWeather extends TestBase {
	

	@Test(description ="Verify city is selected and selected city weather is shown", priority=1)
	void verifyCitySelecteAndWeatherInfomationDisplayed() throws Exception {
		
	String testcaseName = "CityWeather";
	WeatherInformationFromWebsite weatherInformation = new WeatherInformationFromWebsite(testcaseName);
	weatherInformation.navigateToWeatherMenu();
	weatherInformation.selectCity();
	WeatherInformationFromWebsite.getCityWeatherTemperatureFromWeb();	
		
	}

	@Test(description ="Get weather information for the city from Api",priority=2)
	void verifySelectedCityWeatherFromApi() throws IOException {
		
	String testcaseName = "CityWeather";
	GetTemperatureDataFromApi getDataFromApi = new GetTemperatureDataFromApi();
	getDataFromApi.getWeatherDataFromApi(testcaseName);
	GetTemperatureDataFromApi.getCityWeatherTemperatureFromApi();	
		
	}
	
	@Test(description ="Get temperature difference for the same city recieved from website and API",priority=3)
	void verifyTempreatureDifference() throws Exception {
		
	String testcaseName = "CityWeather";
	WeatherInformationFromWebsite weatherInformation = new WeatherInformationFromWebsite(testcaseName);
	weatherInformation.navigateToWeatherMenu();
	weatherInformation.selectCity();
	WeatherInformationFromWebsite.getCityWeatherTemperatureFromWeb();
		
	GetTemperatureDataFromApi getDataFromApi = new GetTemperatureDataFromApi();
	getDataFromApi.getWeatherDataFromApi(testcaseName);
	GetTemperatureDataFromApi.getCityWeatherTemperatureFromApi();
		
		CalculateTemperatureDifference.getTemperatureDifference();
		
	}
	
	
	@AfterTest
	void closeBrowser()
	{
	    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");   //yyyyMMdd_HHmmss
	    Date date = new Date();  
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           try {
				FileUtils.copyFile(scrFile, new File(formatter.format(date)+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		driver.quit();
		
	}
	
}
