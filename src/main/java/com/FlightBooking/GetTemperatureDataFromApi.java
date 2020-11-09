package com.FlightBooking;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;

import com.google.common.collect.HashMultimap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import io.restassured.specification.RequestSpecification;




public class GetTemperatureDataFromApi {
	
	private static JsonPath js;
	private static Double temperatureInKelvin;
	static Double convertTempIntoCelsius;
	private ReadDataFromExcel readDataFromExcel;
	List<String> list;
	
	public void getWeatherDataFromApi(String testcaseName) throws IOException
		
	{
		list = ReadDataFromExcel.getData(testcaseName);
		String apiKey="7fe67bf08c80ded756e598d6f8fedaea";
		String city=list.get(1);
		String countryCode=list.get(2);
		String ApiEndUrl="http://api.openweathermap.org/data/2.5/weather";
		
		RequestSpecification requestSpec = RestAssured.given();
		String responseBody = requestSpec.queryParam("q", city+","+countryCode).queryParam("appid", apiKey).when().get(ApiEndUrl).then().extract().response().body().asString();
						
		js = new JsonPath(responseBody);
		
		
			
	}
		public static Double getCityWeatherTemperatureFromApi()
		{
			temperatureInKelvin=Double.parseDouble(js.getString("main.temp"));
			convertTempIntoCelsius = temperatureInKelvin - 273.15;
			System.out.println("The city's temperature from api response is: " +convertTempIntoCelsius);
			return convertTempIntoCelsius;

		}
		

	}
