package org.pub.pt.data.sources.accuweather;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.pub.pt.data.sources.accuweather.domain.Weather;
import org.pub.pt.data.sources.accuweather.domain.WeatherLocation;
import org.pub.pt.data.sources.accuweather.domain.WeatherLocationList;
import org.pub.global.utils.DomUtils;

/**
 * Implements the <a href="http://www.accuweather.com/" target="_blank">AccuWeather</a> 
 * that give us information about the weather. <br>
 * This is not a Portuguese service 
 * but it is used by many portuguese people and so we decided to include this service
 * in this package.<br><br>
 * <b>Tutorial:</b><br><br>
 * 
 * <pre>
 * final {@link AccuWeather} accW=new {@link AccuWeather#AccuWeather() AccuWeather()};
 * {@link WeatherLocationList} weatherList=accW.{@link #getLocations(String) getLocations("porto")};
 * {@link ExecutorService} executor = {@link Executors#newFixedThreadPool(int) Executors.newFixedThreadPool(weatherList.getWeatherLocationList().size())};
 * {@link Set} weathers=new {@link HashSet}();
 * for(final {@link WeatherLocation} wl:weatherList.getWeatherLocationList()){
 *    weathers.add(executor.submit(new Callable&lt;Weather&gt;() {
 *      public Weather call() throws Exception {
 * 			return accW.getLocation(wl.getLocation());
 * 			}
 * 	}));
 * }
 * int i=0;
 * for(Future&lt;Weather&gt; weather : weathers){
 * 	System.out.println(weatherList.getWeatherLocationList().get(i).getName());
 * 	System.out.println(weather.get().toJSON());
 * 	i++;
 * }
 * executor.shutdown();
 * </pre>
 * 
 * @author balhau
 *
 */
public class AccuWeather extends AbstractDataSource {

	public static final String SEARCH_WEATHER_URL = "http://www.accuweather.com/en/search-locations";

	/**
	 * Method that returns a WeatherLocation object. This holds a list of
	 * resources you can use in getLocation method
	 * 
	 * @param searchPattern
	 *            A pattern with the name of the location
	 * @return {@link WeatherLocation}
	 * @throws Exception Scrapping problems
	 */
	public WeatherLocationList getLocations(String searchPattern)
			throws Exception {
		Connection cn = DomUtils.get(SEARCH_WEATHER_URL);

		Document d = cn.data("s", searchPattern, "rn", "3day").post();
		Elements els = d.getElementsByClass("articles").get(0)
				.getElementsByTag("h6");

		WeatherLocationList wlist = new WeatherLocationList();
		ArrayList<WeatherLocation> weatherList = new ArrayList<WeatherLocation>(
				els.size());
		WeatherLocation location;

		for (Element el : els) {
			location = new WeatherLocation(el.getElementsByTag("a").get(0)
					.attr("href")
					.replaceAll("weather-forecast", "current-weather"), el
					.getElementsByTag("em").get(0).text());
			weatherList.add(location);
		}

		wlist.setWeatherLocationList(weatherList);
		return wlist;
	}

	/**
	 * This method returns a {@link Weather} object representing the weather in
	 * a specific geographic Location. You can find geographic locations by
	 * invoking the getLocations method
	 * 
	 * @param location
	 *            {@link String} Location of the weather to be retrieved
	 * @return {@link Weather} Domain object representing weather
	 * @throws Exception Scrapping problems
	 */
	public Weather getLocation(String location) throws Exception {
		Connection cn = DomUtils.get(location);
		Document doc = cn.get();
		Element statusInfo = doc.getElementsByClass("info").get(5);
		Elements statisticInfo = doc.getElementsByClass("stats").get(0)
				.getElementsByTag("li");
		//statisticInfo.get(0).text().split(":")[1].split("%")[0].trim();

		Weather weather = new Weather();
		weather.setHumidity(Integer.parseInt(statisticInfo.get(1).text()
				.split(":")[1].split("%")[0].trim()));
		weather.setPressure(Float.parseFloat(statisticInfo.get(2).text()
				.split(":")[1].split("mb")[0].trim()));
		weather.setUvindex(Integer.parseInt(statisticInfo.get(3).text()
				.split(":")[1].trim()));

		weather.setCloudCover(Integer.parseInt(statisticInfo.get(4).text()
				.split(":")[1].split("%")[0].trim()));

		weather.setCeiling(Integer.parseInt(statisticInfo.get(5).text()
				.split(":")[1].split("m")[0].trim()));

		weather.setVisibility(Integer.parseInt(statisticInfo.get(7).text()
				.split(":")[1].split("km")[0].trim()));

		weather.setStatus(statusInfo.getElementsByClass("cond").get(0).text());
		weather.setTemperature(Integer.parseInt(statusInfo
				.getElementsByClass("temp").get(0).text().split("°")[0]));

		return weather;

	}
}
