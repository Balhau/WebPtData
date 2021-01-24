package org.pub.pt.data.sources.ipma;

import java.util.*;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.ipma.domain.BeachEntry;
import org.pub.global.configs.HtmlTag;
import org.pub.global.domain.TableData;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.ipma.domain.api.*;


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
	private static final String API_HOST="http://api.ipma.pt";
	/**
	 * General weather information url
	 */
	
	@SuppressWarnings("unused")
	private static final String OVERVIEW_PT=HOST+"/resources.www/internal.user/ww_overview_pt.xml";
	/**
	 * Referer http header needed to fetch data from several services
	 */
	private static final String REFERER_SWF_HOST=HOST+"/opencms/bin/flash/vrs1.1/prev.meteo.sam.portugal.swf";
	/**
	 * Day 0 weather information url
	 */
	private static final String FORECAST_AGGREGATE_PATTERN=API_HOST+"/public-data/forecast/aggregate/%s.json";

	private static final String WEATHER_TYPES_URL=HOST+"/bin/file.data/weathertypes.json";
	private static final String WIND_TYPES_URL=HOST+"/bin/file.data/windtypes.json";
	private static final String RAIN_TYPES_URL=HOST+"/bin/file.data/raintypes.json";
	private static final String WARNINGS_URL=API_HOST+"/public-data/warnings/warnings_www.json";

	private static final String DISTRICTS_URL=API_HOST+"/public-data/districts.json";
	private static final String FORECAST_LOCATIONS_URL=API_HOST+"/public-data/forecast/locations.json";
	
	private static final String BEACH_URL_PATTERN=HOST+"/pt/maritima/costeira/index.jsp?selLocal=ID&idLocal=ID";

	/**
	 * Information about the sea
	 */
	private static final String SEA_STATUS=HOST+"/pt/maritima/costeira/";
	
	public Ipma(){
	}

	/**
	 * Just for serialization purposes
	 */
	public static final class WeatherMap extends HashMap<String,LocaleLabel>{ }
	public static final class DistrictList extends ArrayList<District>{}
	public static final class ForecastLocationList extends ArrayList<ForecastLocation>{}
	public static final class ForecastInfoList extends ArrayList<ForecastInfo>{}


	public Map<String, LocaleLabel> getWeatherTypes(){
		String jsonData = DomUtils.getRawString(WEATHER_TYPES_URL);
		return new Gson().fromJson(jsonData, WeatherMap.class);
	}

	public Map<String,LocaleLabel> getWindTypes(){
		String jsonData = DomUtils.getRawString(WIND_TYPES_URL);
		return new Gson().fromJson(jsonData, WeatherMap.class);
	}

	public Map<String,LocaleLabel> getRainTypes(){
		String jsonData = DomUtils.getRawString(RAIN_TYPES_URL);
		return new Gson().fromJson(jsonData, WeatherMap.class);
	}

	public List<District> getDistricts(){
		String jsonData=DomUtils.getRawString(DISTRICTS_URL);
		return new Gson().fromJson(jsonData,DistrictList.class);
	}

	public Warnings getWarnings(){
		String jsonData=DomUtils.getRawString(WARNINGS_URL);
		return new Gson().fromJson(jsonData,Warnings.class);
	}

	public List<ForecastLocation> getForecastLocations(){
		String jsonData = DomUtils.getRawString(FORECAST_LOCATIONS_URL);
		return new Gson().fromJson(jsonData,ForecastLocationList.class);
	}

	public List<ForecastInfo> getForecastForLocation(ForecastLocation location){
		String jsonData = DomUtils.getRawString(String.format(FORECAST_AGGREGATE_PATTERN,location.getGlobalIdLocal()));
		return new Gson().fromJson(jsonData,ForecastInfoList.class);
	}
	
	/**
	 * This method returns a list of entries we can then use to fetch beach information 
	 * @return {@link List} of {@link BeachEntry} elements
	 * @throws Exception if error found while parsing data
	 */
	public List<BeachEntry> getBeachEntries() throws Exception{
		Connection con=DomUtils.getHTML(SEA_STATUS);
		Document doc=con.header("Referer",REFERER_SWF_HOST).get();
		return parseBeachEntries(doc.getElementById("selLocal"));
	}
	
	/**
	 * This method returns information about a specific beach. 
	 * @param idBeach {@link Integer} the id of the beach we want information
	 * @return {@link List} a list of {@link TableData} elements with information regarding the beach in
	 * question
	 * @throws Exception if error found while parsing data
	 */
	public List<TableData> getBeachInfo(int idBeach) throws Exception{
		List<TableData> beachInfoList = new ArrayList<TableData>();
		Connection con=DomUtils.getHTML(BEACH_URL_PATTERN.replaceAll("ID", ""+idBeach));
		Document doc=con.get();
		Elements tables=doc.getElementsByTag(HtmlTag.TABLE);
		for(Element table : tables){
			beachInfoList.add(DomUtils.tableElementToTableData(table));
		}
		return beachInfoList;
	}
	
	
	/**
	 * This private method is responsible for the parsing of the beach entries in the DOM
	 * @param selectionElement
	 * @return {@link List} of {@link BeachEntry}
	 */
	private List<BeachEntry> parseBeachEntries(Element selectionElement){
		List<BeachEntry> l=new ArrayList<BeachEntry>();
		Elements els=selectionElement.getElementsByTag(HtmlTag.OPTION);
		for(Element el:els){
			BeachEntry be=new BeachEntry();
			be.setName(el.text());
			String id=el.attr("value");
			be.setIdBeach(Integer.parseInt(id));
			l.add(be);
		}
		return l;
	}
	
}
