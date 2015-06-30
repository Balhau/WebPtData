package org.pt.pub.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UvWeather")
public class UvWeather extends GeoWeather<Uv>{
	private Uv uv;
	public UvWeather(){
		
	}
	@Override
	public Uv getData() {
		// TODO Auto-generated method stub
		return uv;
	}
	@Override
	public void setData(Uv props) {
		this.uv=props;
	}
}
