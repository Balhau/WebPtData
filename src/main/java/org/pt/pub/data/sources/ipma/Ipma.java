package org.pt.pub.data.sources.ipma;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.ipma.domain.BaseInfo;
import org.pt.pub.data.sources.ipma.domain.BeachEntry;
import org.pt.pub.data.sources.ipma.domain.GeoWeather;
import org.pt.pub.data.sources.ipma.domain.Land;
import org.pt.pub.data.sources.ipma.domain.LandWeather;
import org.pt.pub.data.sources.ipma.domain.Sea;
import org.pt.pub.data.sources.ipma.domain.SeaWeather;
import org.pt.pub.data.sources.ipma.domain.Uv;
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
	private static final String FORECAST_DAY_PATTERN=HOST+"/resources.www/internal.user/wp_d%s_pt.xml";
	
	private static final String BEACH_URL_PATTERN=HOST+"/pt/maritima/costeira/index.jsp?selLocal=ID&idLocal=ID";
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
	public List<GeoWeather<?>> getForecastDay(int dayNumber) throws Exception{
		List<GeoWeather<?>> forecast = new ArrayList<GeoWeather<?>>();
		Connection seac=Jsoup.connect(String.format(FORECAST_DAY_PATTERN,dayNumber));
		Document doc=seac
				.header("Referer", REFERER_SWF_HOST)
				.get();
		Element land=doc.getElementsByTag("land").get(0);
		Element sea=doc.getElementsByTag("sea").get(0);
		Element uv=doc.getElementsByTag("uv").get(0);
		forecast.add(getLandWeather(land));
		forecast.add(getSeaWeather(sea));
		forecast.add(getUVWeather(uv));
		return forecast;
	}
	
	public List<BeachEntry> getBeachEntries() throws Exception{
		Connection con=Jsoup.connect(SEA_STATUS);
		Document doc=con.header("Referer",REFERER_SWF_HOST).get();
		return parseBeachEntries(doc.getElementById("selLocal"));
	}
	
	private List<BeachEntry> parseBeachEntries(Element selectionElement){
		List<BeachEntry> l=new ArrayList<BeachEntry>();
		Elements els=selectionElement.getElementsByTag(HtmlTag.OPTION);
		for(Element el:els){
			BeachEntry be=new BeachEntry();
			be.setName(el.text());
			String id=el.attr("value");
			be.setUrl(BEACH_URL_PATTERN.replaceAll("ID", id));
			l.add(be);
		}
		return l;
	}
	
	
	private void decorateWeatherFromElement(GeoWeather<?> weather,Element el){
		weather.setFullmapshow(Integer.parseInt(el.attr("fullmapshow")));
		weather.setLatitude(Double.parseDouble(el.attr("lat")));
		weather.setLocalId(Integer.parseInt(el.attr("localid")));
		weather.setLongitude(Double.parseDouble(el.attr("lon")));
		weather.setName(el.attr("name"));
		weather.setPeriodId(Integer.parseInt(el.attr("periodid")));
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
	
	private List<Element> getElementsByTag(Element node,String ...tags){
		List<Element> els=new ArrayList<>();
		for(String tag : tags){
			els.add(node.getElementsByTag(tag).get(0));
		}
		return els;
	}
	
	private SeaWeather getSeaWeather(Element parentElement){
		SeaWeather sw=new SeaWeather();
		decorateWeatherFromElement(sw, parentElement);
		Sea sea=new Sea();
		Element seaEl=parentElement.getElementsByTag("seaweather").get(0);
		decorateBaseInfoFromElement(sea, seaEl);
		
		List<Element> els=getElementsByTag(parentElement,
				"watertemp","wavehigh","wavehighdesc","wavedir","wavedirdesc","wavedirresume"
				);
		
		sea.setWaterTemperature(Integer.parseInt(els.get(0).text()));
		sea.setWaveHighId(Integer.parseInt(els.get(1).attr("wavehighid")));
		sea.setWaveHighDesc(els.get(2).text());
		sea.setWaveDirId(Integer.parseInt(els.get(3).attr("wavedirid")));
		sea.setWaveDirDesc(els.get(4).text());
		sea.setWaveDirSymb(els.get(5).text());
		sw.setData(sea);
		return sw;
	}
	
	private UvWeather getUVWeather(Element parentElement){
		UvWeather uvw=new UvWeather();
		Uv uv = new Uv();
		decorateWeatherFromElement(uvw, parentElement);
		List<Element> els=getElementsByTag(parentElement,
				"uvforecast","iuv","iuvhora"
		);
		decorateBaseInfoFromElement(uv, els.get(0));
		uv.setIuv(Float.parseFloat(els.get(1).text()));
		uv.setIuvHour(els.get(2).text());
		uvw.setData(uv);
		return uvw;
	}
	
}
