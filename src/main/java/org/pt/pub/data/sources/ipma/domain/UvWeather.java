package org.pt.pub.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UvWeather")
public class UvWeather extends GeoWeather<Uv>{
	public UvWeather(){
		
	}

	public Uv getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	public void setData(Uv data) {
		this.data=data;
	}
}
