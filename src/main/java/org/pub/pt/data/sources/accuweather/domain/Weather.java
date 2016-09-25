package org.pub.pt.data.sources.accuweather.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pub.pt.data.sources.domain.AbstractData;

@XmlRootElement(name="Weather")
public class Weather extends AbstractData{
	
	private int humidity;
	private int wind;
	private int visibility;
	private float pressure;
	private int uvindex;
	private int cloudCover;
	private int ceiling;
	private int dewPoint;
	private int temperature;
	private String status;


	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public int getWind() {
		return wind;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}

	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public int getUvindex() {
		return uvindex;
	}
	public void setUvindex(int uvindex) {
		this.uvindex = uvindex;
	}
	public int getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(int cloudCover) {
		this.cloudCover = cloudCover;
	}
	public int getCeiling() {
		return ceiling;
	}
	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
	}
	public int getDewPoint() {
		return dewPoint;
	}
	public void setDewPoint(int dewPoint) {
		this.dewPoint = dewPoint;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
