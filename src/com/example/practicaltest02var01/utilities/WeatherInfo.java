package com.example.practicaltest02var01.utilities;

import com.example.practicaltest02var01.utilities.Constants;

public class WeatherInfo {

	private String temperature;
	private String humidity;

	public WeatherInfo() {
		this.temperature = null;
		this.humidity    = null;
	}

	public WeatherInfo(
			String temperature,
			String humidity) {
		this.temperature = temperature;
		this.humidity    = humidity;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	
	
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	public String getHumidity() {
		return humidity;
	}
	
	@Override
	public String toString() {
		return Constants.TEMP + ": " + temperature + ", " +
			   Constants.HUMIDITY + ": " + humidity;
	}

}
