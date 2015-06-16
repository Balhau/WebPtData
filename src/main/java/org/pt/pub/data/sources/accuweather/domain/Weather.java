package org.pt.pub.data.sources.accuweather.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;
import org.pt.pub.data.sources.ine.datatypes.INEDataRow;

@XmlRootElement(name="Weather")
public class Weather extends AbstractData{
	
	private int humidity;
	private float pressure;
	private int uvindex;
	private int cloudCover;
	private int ceiling;
	private int dewPoint;
	private int temperature;
	private String status;
	
	
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
