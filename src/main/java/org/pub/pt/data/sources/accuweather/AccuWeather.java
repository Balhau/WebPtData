package org.pub.pt.data.sources.accuweather;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.accuweather.domain.Temperature;
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
 *            }
 *    }));
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
 */
public class AccuWeather extends AbstractDataSource {

    public static final String SEARCH_WEATHER_URL_PATTERN = "https://www.accuweather.com/web-api/autocomplete?query=%s&language=en-us";

    /**
     * Method that returns a WeatherLocation object. This holds a list of
     * resources you can use in getLocation method
     *
     * @param searchPattern A pattern with the name of the location
     * @return {@link WeatherLocation}
     * @throws Exception Scrapping problems
     */
    public WeatherLocationList getLocations(String searchPattern)
            throws Exception {
        String url = String.format(SEARCH_WEATHER_URL_PATTERN, searchPattern);
        String data = DomUtils.getRawString(url);
        WeatherLocationList wlist = new WeatherLocationList();
        wlist.setWeatherLocationList(WeatherLocation.fromJSON(data));
        return wlist;
    }

    /**
     * This method returns a {@link Weather} object representing the weather in
     * a specific geographic Location. You can find geographic locations by
     * invoking the getLocations method
     *
     * @param WeatherLocation {@link String} Location of the weather to be retrieved
     * @return {@link Weather} Domain object representing weather
     * @throws Exception Scrapping problems
     */
    public Weather getLocation(WeatherLocation weatherLocation) throws Exception {
        String currentWeatherURL = "https://www.accuweather.com/pt/pt/"
                + weatherLocation.getName()
                + "/" + weatherLocation.getKey()
                + "/current-weather/" + weatherLocation.getKey();

        Connection cn = DomUtils.getHTML(currentWeatherURL);
        Document doc = cn.get();
        List<String> elements = doc.getElementsByClass("current-weather-details").get(0)
                .getElementsByClass("detail-item")
                .stream()
                .map(el -> el.getElementsByTag("div").get(2).text())
                .collect(Collectors.toList());

        Weather weather = new Weather();
        weather.setHumidity(Integer.parseInt(
                elements.get(4).split("%")[0].trim()
        ));

        weather.setPressure(Float.parseFloat(elements.get(6)
                .split("mb")[0]
                .trim()));
        weather.setUvindex(Integer.parseInt(
                elements.get(1).split(" ")[0]
        ));

        weather.setCloudCover(Integer.parseInt(
                elements.get(7).split("%")[0].trim()
        ));

        weather.setCeiling(Integer.parseInt(
                elements.get(9).split("m")[0].trim()
        ));

        weather.setVisibility(Integer.parseInt(
                elements.get(8).split("km")[0].trim()
        ));

        weather.setStatus(doc.getElementsByClass("phrase")
                .get(0)
                .text()
        );

        Elements temps = doc.getElementsByClass("current-weather-info")
                .get(0)
                .getElementsByTag("div");

        String displayTemp = doc.getElementsByClass("display-temp").get(0).text();
        String realFeelTemp = doc.getElementsByClass("realfeel").get(0).text();
        String realFeelMaxTemp = doc.getElementsByClass("temperature").get(0).text();

        weather.setTemperature(new Temperature(
                Float.parseFloat(
                        displayTemp.split("C")[0].substring(0,displayTemp.split("C")[0].length()-1)),
                Float.parseFloat(
                        realFeelTemp.split(" ")[1].substring(0, realFeelTemp.split(" ")[1].length() - 1
                        )),
                Float.parseFloat(
                        realFeelMaxTemp.split("Mx")[0].substring(0, realFeelMaxTemp.split("Mx")[0].length() - 1
                        ))
        ));

        return weather;

    }

    private String buildLocationUrl(WeatherLocation weatherLocation) {
        return "https://www.accuweather.com/pt/pt/" + weatherLocation.getName()
                + "/" + weatherLocation.getKey()
                + "/weather-forecast/" + weatherLocation.getKey();
    }
}
