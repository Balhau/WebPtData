package org.pt.pub.data.sources.ipma.domain;

import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LandWeather extends GeoWeather<Land>{
	
	public LandWeather(){
	}

	public Land getData() {
		return data;
	}

	public void setData(Land data) {
		this.data=data;
	}
	
}
