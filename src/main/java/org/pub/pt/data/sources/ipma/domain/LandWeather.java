package org.pub.pt.data.sources.ipma.domain;

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
