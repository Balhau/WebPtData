package org.pub.pt.data.sources.accuweather.domain;


import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.pub.pt.data.sources.domain.AbstractData;

@XmlRootElement(name="weatherLocationList")
public class WeatherLocationList extends AbstractData {
	
	List<WeatherLocation> weatherLocationList;

	public List<WeatherLocation> getWeatherLocationList() {
		return weatherLocationList;
	}

	public void setWeatherLocationList(List<WeatherLocation> weatherLocationList) {
		this.weatherLocationList = weatherLocationList;
	}
	
	
}
