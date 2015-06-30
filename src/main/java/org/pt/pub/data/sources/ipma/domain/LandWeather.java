package org.pt.pub.data.sources.ipma.domain;

import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LandWeather")
public class LandWeather extends GeoWeather<Land>{
	private Land properties;
	
	public LandWeather(){
	}

	@Override
	public Land getData() {
		// TODO Auto-generated method stub
		return properties;
	}

	@Override
	public void setData(Land properties) {
		this.properties=properties;
	}
}
