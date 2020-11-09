package com.FlightBooking;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class CalculateTemperatureDifference extends TestBase {
	Double tempVariation;
	public Double getTemperatureDifference() throws Exception
	{
		tempVariation = WeatherInformationFromWebsite.getCityWeatherTemperatureFromWeb() - GetTemperatureDataFromApi.getCityWeatherTemperatureFromApi();
		System.out.println("this.tempVariation:" +this.tempVariation);
		System.out.println("tempVariation:" +tempVariation);
		file = new File(projectPath+"/config.properties");
		fileinputStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(fileinputStream);
		if(tempVariation >= 0 && tempVariation <= Double.parseDouble(prop.getProperty("MaximumTempVariation")))
		{
			System.out.println("Website and Api temperature difference for the same city is:" +tempVariation);
		}
		else
		{
			throw new Exception("Website and Api temperature difference for the same city is not between 0 and 2");
		}
		return tempVariation;
	}

}

	