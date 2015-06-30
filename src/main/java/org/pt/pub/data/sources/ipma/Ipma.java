package org.pt.pub.data.sources.ipma;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
	public List<TableData> getSeaInformation() throws Exception{
		List<TableData> forecast=new ArrayList<TableData>();
		Connection sea=Jsoup.connect(SEA_STATUS);
		Document doc=sea.get();
		Elements days=doc.getElementsByClass("tablelist");
		forecast.add(getSeaInformationHeaders(days.get(0)));
		for(Element day : days){
			forecast.add(DomUtils.tableElementToTableData(day));
		}
		return forecast;
	}
	
	/**
	 * This will return the sea information headers
	 * @param day
	 * @return
	 */
	private TableData getSeaInformationHeaders(Element day){
		TableData tdata=new TableData();
		TableRow theaders=new TableRow();
		Elements headers=day.getElementsByTag(HtmlTag.TH);
		for(Element header : headers){
			theaders.getData().add(header.text());
		}
		tdata.getRows().add(theaders);
		return tdata;
	}
	
}
