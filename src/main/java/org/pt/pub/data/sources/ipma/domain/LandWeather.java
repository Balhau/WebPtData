package org.pt.pub.data.sources.ipma.domain;

import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
