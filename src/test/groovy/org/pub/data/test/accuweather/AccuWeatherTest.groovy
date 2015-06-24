package org.pub.data.test.accuweather

import org.pt.pub.data.accuWeather;
import org.pt.pub.data.sources.accuweather.AccuWeather;
import org.pt.pub.data.sources.accuweather.domain.Weather;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocation;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocationList;

import spock.lang.*;

class AccuWeatherTest extends Specification{
	
	private AccuWeather weather
	
	def setup() {
		weather = new AccuWeather()  
	}
	
	def "Find countries like porto"(){
		when:
			WeatherLocationList weatherList=weather.getLocations("porto")
		then:
			weatherList.getWeatherLocationList().size() == 35
	}
	
	def "Find weather for countries like porto"(){
		when:
			WeatherLocationList weatherList = weather.getLocations("porto")
			WeatherLocation loc = weatherList.getWeatherLocationList().get(0);
		then:
			weather.getLocation(loc.getLocation()) != null
	}
}
