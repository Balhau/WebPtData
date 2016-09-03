package org.pt.pub.data.sources.ipma;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
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
import org.pub.global.configs.HtmlTag;
import org.pub.global.domain.TableData;
import org.pub.global.utils.DomUtils;


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
	
	@SuppressWarnings("unused")
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
	
	private static final String SYSMOLOGY_URL=HOST+"/pt/geofisica/sismologia/";
	/**
	 * Information about the sea
	 */
	private static final String SEA_STATUS=HOST+"/pt/maritima/costeira/";
	
	public Ipma(){
	}
	
	/**
	 * This method will return a list of {@link TableData} objects. Each of the elements represents
	 * the forecast for the sea in a specific day
	 * @param dayNumber {@link Integer} with the offset in days for the forecast
	 * @return {@link List} with {@link GeoWeather} entries
	 * @throws Exception if error is found while parsing data
	 */
	public List<GeoWeather<?>> getForecastDay(int dayNumber) throws Exception{
		List<GeoWeather<?>> forecast = new ArrayList<GeoWeather<?>>();
		Connection seac=DomUtils.get(String.format(FORECAST_DAY_PATTERN,dayNumber));
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
	
	/**
	 * This method returns the seismic activities found since a provided {@link Date}
	 * @param date {@link Date} From which we want the list of seismic activities
	 * @return {@link List} Of {@link TableData} elements
	 * @throws Exception if error found while parsing data
	 */
	public List<TableData> getSeismicActivity(Date date) throws Exception{
		List<TableData> seismic=new ArrayList<TableData>();
		Connection con=DomUtils.get(SYSMOLOGY_URL);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		Document doc=con.data(
				"year",cal.get(Calendar.YEAR)+"",
				"month",cal.get(Calendar.MONTH)+"",
				"day",cal.get(Calendar.DAY_OF_MONTH)+""
		)
		.header("Referer", SYSMOLOGY_URL)
		.get();
	
		Element portugalDiv=doc.getElementById("divID0");
		Element islandsDiv=doc.getElementById("divID1");
		Element restOfWorld=doc.getElementById("divID2");
		
		
		seismic.add(DomUtils.tableElementToTableData(portugalDiv.getElementsByTag(HtmlTag.TABLE).get(0)));
		seismic.add(DomUtils.tableElementToTableData(islandsDiv.getElementsByTag(HtmlTag.TABLE).get(0)));
		seismic.add(DomUtils.tableElementToTableData(restOfWorld.getElementsByTag(HtmlTag.TABLE).get(0)));
		return seismic;
	}
	
	/**
	 * This method returns a list of entries we can then use to fetch beach information 
	 * @return {@link List} of {@link BeachEntry} elements
	 * @throws Exception if error found while parsing data
	 */
	public List<BeachEntry> getBeachEntries() throws Exception{
		Connection con=DomUtils.get(SEA_STATUS);
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
		Connection con=DomUtils.get(BEACH_URL_PATTERN.replaceAll("ID", ""+idBeach));
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
	
	/**
	 * This method changes the state of the GeoWeather object by setting some properties
	 * @param weather {@link GeoWeather} Domain object representing the weather
	 * @param el {@link Element} DOM element
	 */
	private void decorateWeatherFromElement(GeoWeather<?> weather,Element el){
		weather.setFullmapshow(Integer.parseInt(el.attr("fullmapshow")));
		weather.setLatitude(Double.parseDouble(el.attr("lat")));
		weather.setLocalId(Integer.parseInt(el.attr("localid")));
		weather.setLongitude(Double.parseDouble(el.attr("lon")));
		weather.setName(el.attr("name"));
		weather.setPeriodId(Integer.parseInt(el.attr("periodid")));
	}
	
	/**
	 * Private method that changes the state of the {@link BaseInfo} object with properties extracted
	 * from the DOM 
	 * @param bi {@link BaseInfo} Information shared between the several weather types
	 * @param el {@link Element} DOM element
	 */
	private void decorateBaseInfoFromElement(BaseInfo bi,Element el){
		bi.setEndTime(el.attr("end-time"));
		bi.setStartTime(el.attr("start-time"));
	}
	
	/**
	 * Private method responsible for the extraction of {@link LandWeather} from the DOM.
	 * @param parentElement {@link Element} DOM element with the information relative to the {@link LandWeather}
	 * @return {@link LandWeather}
	 */
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
	
	/**
	 * Private method responsible for the extraction of several {@link Element} from other Element provided
	 * as parameter. Note that this method only gets the first element by tag name.
	 * @param node {@link Element} 
	 * @param tags {@link String[]} Vararg of strings with the tags for extraction
	 * @return {@link List} of {@link Element}
	 */
	private List<Element> getElementsByTag(Element node,String ...tags){
		List<Element> els=new ArrayList<>();
		for(String tag : tags){
			els.add(node.getElementsByTag(tag).get(0));
		}
		return els;
	}
	
	/**
	 * Private method responsible for the extraction of {@link SeaWeather} from an {@link Element} of the DOM
	 * @param parentElement {@link Element} of the DOM
 	 * @return {@link SeaWeather}
	 */
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
	
	/**
	 * Private method responsible for the extraction of {@link UvWeather} form an {@link Element} of the 
	 * DOM
	 * @param parentElement {@link Element} of the DOM
	 * @return {@link UvWeather} 
	 */
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
