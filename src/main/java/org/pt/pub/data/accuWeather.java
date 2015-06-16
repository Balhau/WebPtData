package org.pt.pub.data;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.accuweather.AccuWeather;
import org.pt.pub.data.sources.accuweather.domain.Weather;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocation;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocationList;
import org.pt.pub.global.configs.GlobalConfigs;

public class accuWeather {
	
	public static final String USER_AGENT="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/43.0.2357.81 Chrome/43.0.2357.81 Safari/537.36";
	
	public static void main(String[] args) throws Exception{
		final AccuWeather accW=new AccuWeather();
		WeatherLocationList weatherList=accW.getLocations("porto");
		ExecutorService executor = Executors.newFixedThreadPool(weatherList.getWeatherLocationList().size());
		Set<Future<Weather>> weathers=new HashSet<Future<Weather>>();

		for(final WeatherLocation wl:weatherList.getWeatherLocationList()){
			weathers.add(executor.submit(new Callable<Weather>() {
				public Weather call() throws Exception {
					return accW.getLocation(wl.getLocation());
				}
			}));
		}
		int i=0;
		for(Future<Weather> weather : weathers){
			System.out.println(weatherList.getWeatherLocationList().get(i).getName());
			System.out.println(weather.get().toJSON());
			i++;
		}
		
		executor.shutdown();
		
		//System.out.println("A humidade em mirandela é: "+w.getHumidity());
		//System.out.println(w.toJSON());
	}
}
