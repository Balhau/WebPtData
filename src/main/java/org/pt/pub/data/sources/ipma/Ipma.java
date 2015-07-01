package org.pt.pub.data.sources.ipma;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.ipma.domain.BaseInfo;
import org.pt.pub.data.sources.ipma.domain.GeoWeather;
import org.pt.pub.data.sources.ipma.domain.Land;
import org.pt.pub.data.sources.ipma.domain.LandWeather;
import org.pt.pub.data.sources.ipma.domain.SeaWeather;
import org.pt.pub.data.sources.ipma.domain.UvWeather;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.domain.TableData;
import org.pt.pub.global.domain.TableRow;
import org.pt.pub.global.utils.DomUtils;


/**
 * This class holds several querying methods to retrieve data from Instituto português do mar e da atmosfera also
 * known as Ipma
 * @author balhau
 *
 */
public class Ipma {
	/**
	 * Base host url
	 */
	private static final String HOST="http://www.ipma.pt";
	/**
	 * General weather information url
	 */
	private static final String OVERVIEW_PT=HOST+"/resources.www/internal.user/ww_overview_pt.xml";
	/**
	 * Referer http header needed to fetch data from several services
	 */
	private static final String REFERER_SWF_HOST=HOST+"/opencms/bin/flash/vrs1.1/prev.meteo.sam.portugal.swf";
	/**
	 * Day 0 weather information url
	 */
	private static final String FORECAST_DAY_0=HOST+"/resources.www/internal.user/wp_d0_pt.xml";
	/**
	 * Information about the sea
	 */
	private static final String SEA_STATUS=HOST+"/pt/maritima/costeira/";
	
	public Ipma(){
	}
	
	/**
	 * This method will return a list of {@link TableData} objects. Each of the elements represents
	 * the forecast for the sea in a specific day
	 * @return
	 * @throws Exception
	 */
	public List<GeoWeather<?>> getSeaInformation() throws Exception{
		List<GeoWeather<?>> forecast = new ArrayList<GeoWeather<?>>();
		Connection seac=Jsoup.connect(SEA_STATUS);
		Document doc=seac.get();
		Element land=doc.getElementsByTag("land").get(0);
		Element sea=doc.getElementsByTag("sea").get(0);
		Element uv=doc.getElementsByTag("uv").get(0);
		forecast.add(getLandWeather(land));
		forecast.add(getSeaWeather(sea));
		forecast.add(getUVWeather(uv));
		return forecast;
	}
	
	private void decorateWeatherFromElement(GeoWeather<?> weather,Element el){
		weather.setFullmapshow(Integer.parseInt(el.attr("fullmapshow")));
		weather.setLatitude(Double.parseDouble(el.attr("lat")));
		weather.setLocalId(Integer.parseInt(el.attr("localid")));
		weather.setLongitude(Double.parseDouble(el.attr("lon")));
		weather.setName(el.attr("name"));
		weather.setPeriodId(Integer.parseInt("periodid"));
	}
	
	private void decorateBaseInfoFromElement(BaseInfo bi,Element el){
		bi.setEndTime(el.attr("end-time"));
		bi.setStartTime(el.attr("start-time"));
	}
	
	private LandWeather getLandWeather(Element parentElement){
		Element land=parentElement.getElementsByTag("landweather").get(0);
		LandWeather lw=new LandWeather();
		decorateWeatherFromElement(lw,parentElement);
		Land l=new Land();
		decorateBaseInfoFromElement(l, land);
		l.setMin_temperature(Integer.parseInt(land.getElementsByTag("tempmin").get(0).text()));
		l.setMax_temperature(Integer.parseInt(land.getElementsByTag("tempmax").get(0).text()));
		
		Element currWeatherEl=land.getElementsByTag("currentweather").get(0);
		Element weatherDesc=land.getElementsByTag("symboldesc").get(0);
		Element wind=land.getElementsByTag("wind").get(0);
		Element windDirectionDescription=land.getElementsByTag("winddirectiondescription").get(0);
		Element windDirectionResume=land.getElementsByTag("winddirectionresume").get(0);
		Element windspeed=land.getElementsByTag("windspeed").get(0);
		
		l.setWeatherSymbol(Integer.parseInt(currWeatherEl.attr("symbolid")));
		l.setWeatherDescription(weatherDesc.text());
		l.setWindSymbol(Integer.parseInt(wind.attr("windsymbolid")));
		l.setWindDirectionDescription(windDirectionDescription.text());
		l.setWindDirection(windDirectionResume.text());
		l.setWindSpeed(windspeed.text());
		lw.setData(l);
		return lw;
	}
	
	private SeaWeather getSeaWeather(Element parentElement){
		return null;
	}
	
	private UvWeather getUVWeather(Element parentElement){
		return null;
	}
	
}
