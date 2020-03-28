package org.pub.pt.data.sources.accuweather.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.pub.pt.data.sources.domain.AbstractData;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="WeatherLocation")
public class WeatherLocation extends AbstractData{
	String key;
	String location;
	String name;
	
	public WeatherLocation(){}
	
	public WeatherLocation(String location,String name){
		this.location=location;
		this.name=name;
	}


	public String getKey(){
		return this.key;
	}
	public void setKey(String key){
		this.key=key;
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

	public static List<WeatherLocation> fromJSON(String json){
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray)jsonParser.parse(json);
		List<WeatherLocation> weatherLocations = new ArrayList<>(jsonArray.size());
		for(JsonElement jElment : jsonArray){
			WeatherLocation weatherLocation=new WeatherLocation();
			weatherLocation.key=jElment.getAsJsonObject().get("key").getAsString();
			weatherLocation.name=jElment.getAsJsonObject().get("localizedName").getAsString();
			weatherLocation.location=jElment.getAsJsonObject()
					.get("country")
					.getAsJsonObject()
					.get("localizedName")
					.getAsString();
			weatherLocations.add(weatherLocation);
		}
		return weatherLocations;
	}
}
