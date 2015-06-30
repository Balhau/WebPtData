package org.pt.pub.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

/**
 * Domain object that represents the weather relative to a specific geographic location
 * @author balhau
 *
 */
@XmlRootElement(name="LandWeather")
public class Land extends AbstractData{
	private int min_temperature;
	private int max_temperature;
	private int weatherSymbol;
	private String weatherDescription;
	private String windSymbol;
	private String windDirectionDescription;
	private String windDirection;
	private String windSpeed;
	
	public Land(){
		
	}

	public int getMin_temperature() {
		return min_temperature;
	}

	public void setMin_temperature(int min_temperature) {
		this.min_temperature = min_temperature;
	}

	public int getMax_temperature() {
		return max_temperature;
	}

	public void setMax_temperature(int max_temperature) {
		this.max_temperature = max_temperature;
	}

	public int getWeatherSymbol() {
		return weatherSymbol;
	}

	public void setWeatherSymbol(int weatherSymbol) {
		this.weatherSymbol = weatherSymbol;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public String getWindSymbol() {
		return windSymbol;
	}

	public void setWindSymbol(String windSymbol) {
		this.windSymbol = windSymbol;
	}

	public String getWindDirectionDescription() {
		return windDirectionDescription;
	}

	public void setWindDirectionDescription(String windDirectionDescription) {
		this.windDirectionDescription = windDirectionDescription;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	
}