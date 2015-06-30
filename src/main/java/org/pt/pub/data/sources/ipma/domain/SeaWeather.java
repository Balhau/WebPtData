package org.pt.pub.data.sources.ipma.domain;

public class SeaWeather extends GeoWeather<Sea>{
	private Sea properties;
	public SeaWeather(){
		
	}
	@Override
	public Sea getData() {
		// TODO Auto-generated method stub
		return properties;
	}
	@Override
	public void setData(Sea properties) {
		this.properties=properties;
	}
	
	
}
