package org.pub.pt.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeaWeather extends GeoWeather<Sea>{
	public SeaWeather(){
		
	}

	public Sea getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	public void setData(Sea data) {
		this.data=data;
	}	
	
	
}
