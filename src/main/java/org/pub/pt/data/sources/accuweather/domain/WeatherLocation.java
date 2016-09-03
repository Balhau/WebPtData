package org.pub.pt.data.sources.accuweather.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.pub.pt.data.sources.domain.AbstractData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="WeatherLocation")
public class WeatherLocation extends AbstractData{
	String location;
	String name;
	
	public WeatherLocation(){}
	
	public WeatherLocation(String location,String name){
		this.location=location;
		this.name=name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
