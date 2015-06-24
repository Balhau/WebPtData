package org.pt.pub.data.sources.accuweather;

import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.AbstractDataSource;
import org.pt.pub.data.sources.accuweather.domain.Weather;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocation;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocationList;
import org.pt.pub.global.configs.GlobalConfigs;

/**
 * Implements the AccuWeather webservice
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
		Connection cn = Jsoup.connect(SEARCH_WEATHER_URL).userAgent(
				GlobalConfigs.USER_AGENT);

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
		Connection cn = Jsoup.connect(location).userAgent(
				GlobalConfigs.USER_AGENT);
		Document doc = cn.get();
		Element statusInfo = doc.getElementsByClass("info").get(5);
		Elements statisticInfo = doc.getElementsByClass("stats").get(0)
				.getElementsByTag("li");
		statisticInfo.get(0).text().split(":")[1].split("%")[0].trim();

		Weather weather = new Weather();
		weather.setHumidity(Integer.parseInt(statisticInfo.get(0).text()
				.split(":")[1].split("%")[0].trim()));
		weather.setPressure(Float.parseFloat(statisticInfo.get(1).text()
				.split(":")[1].split("mb")[0].trim()));
		weather.setUvindex(Integer.parseInt(statisticInfo.get(2).text()
				.split(":")[1].trim()));

		weather.setCloudCover(Integer.parseInt(statisticInfo.get(3).text()
				.split(":")[1].split("%")[0].trim()));

		weather.setCeiling(Integer.parseInt(statisticInfo.get(4).text()
				.split(":")[1].split("m")[0].trim()));

		weather.setStatus(statusInfo.getElementsByClass("cond").get(0).text());
		weather.setTemperature(Integer.parseInt(statusInfo
				.getElementsByClass("temp").get(0).text().split("°")[0]));

		return weather;

	}
}
