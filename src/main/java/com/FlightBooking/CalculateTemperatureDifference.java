package com.FlightBooking;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class CalculateTemperatureDifference extends TestBase {
	static Double tempVariation;
	public static Double getTemperatureDifference() throws Exception
	{
		tempVariation = WeatherInformationFromWebsite.getCityWeatherTemperatureFromWeb() - GetTemperatureDataFromApi.getCityWeatherTemperatureFromApi();
		file = new File(projectPath+"/config.properties");
		fileinputStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(fileinputStream);
		if(tempVariation <= Double.parseDouble(prop.getProperty("MaximumTempVariation")))
		{
			System.out.println("Website and Api temperature difference for the same city is:" +tempVariation);
		}
		else
		{
			throw new Exception("Website and Api temperature difference for the same city is more than 2");
		}
		return tempVariation;
	}

}

	