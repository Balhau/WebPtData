package org.pt.pub.data.sources.ine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.AbstractDataSource;
import org.pt.pub.data.sources.ine.domain.INEDataRow;
import org.pt.pub.data.sources.ine.domain.INEResultData;
import org.pt.pub.data.sources.ine.domain.INEServices;
import org.pt.pub.data.sources.ine.domain.ServiceItem;

/**
 * This class is the main interface for the INE data. Here we load, parse and store in datastructures the data retrieved from
 * the INE web pages<br><br>
 * 
 * <b>Tutorial:</b><br><br>
 * <pre>
 * //Instantiate constructor
 * {@link Ine} ine=new Ine();
 * 
 * //Get statistics in a paginated way starting from the beginning and with 25 elements by page
 * {@link INEServices} services=ine.{@link #getAvailableServices(int, int) getAvailableServices(0,25)}
 * 
 * //Now get a service item and fetch the data for that service
 * {@link ServiceItem} service=services.{@link INEServices#getList() getList()}.{@link List#get(int) get(0)}
 * {@link String} url=service.{@link ServiceItem#getUrl() getUrl()}
 * {@link INEResultData} data=ine.{@link #getDataFromService(String) getDataFromService(url)}
 * </pre>
 * @author balhau
 *
 */
public class Ine extends AbstractDataSource{
	//TODO: We should replace the domain objects here with the global domain TableData and TableRow
	//domain objects
	private static String INE_BASE="http://www.ine.pt";
	
	/**
	 * Default constructor
	 */
	public Ine(){
	}
	
	/**
	 * You should use this method to retrieve an object {@link INEServices} with all the services available from the INE.
	 * This receives as argument the number of the page and the respective number of items per page
	 * @param pageNumber {@link int} Number of the page
	 * @param itemPerpage {@link int} NUmber items per page
	 * @return {@link INEServices} Object with a list of services available for you to feed into the {@link getDataFromService} method
	 * @throws IOException IO problems
	 */
	public INEServices getAvailableServices(int pageNumber,int itemPerpage) throws IOException{
		String sURL="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_base_dados&bdpagenumber="+pageNumber+"&bdind_por_pagina="+itemPerpage;
		Connection cn=Jsoup.connect(sURL);
		Document doc=cn.get();
		return parseTBodyServices(doc.select("table").get(1).select("tbody").get(0));
	}
	
	/**
	 * Private method that is responsible for the parsing of the DOM table with the services
	 * @param tbody {@link Element} representing a table element
	 * @return {@link INEServices} Domain object
	 */
	private INEServices parseTBodyServices(Element tbody){
		INEServices ines=new INEServices();
		Elements els=tbody.select("tr");
		for(int i=0;i<els.size();i++){
			ines.addItem(parseServiceRow(els.get(i)));
		}
		return ines;
	}
	
	/**
	 * Method responsible to parse DOM rows with the elements
	 * @param row {@link Element} tr DOM element
	 * @return {@link ServiceItem} domain object
	 */
	private ServiceItem parseServiceRow(Element row){
		Element anchor=row.select("td").get(1).select("a").get(0);
		String url=INE_BASE+anchor.attr("href");
		return new ServiceItem(url, anchor.text());
	}
	
	/**
	 * Method responsible to parse INE statistical data from page
	 * @param urlData {@link String} represents the url
	 * @return {@link INEResultData} domain object
	 * @throws IOException in the case of error while parsing data
	 */
	public INEResultData getDataFromService(String urlData) throws IOException{
		Connection cn=Jsoup.connect(urlData);
    	Document doc=cn.get();
    	String iUrl=doc.select("iframe").get(1).attr("src");
    	cn=Jsoup.connect(INE_BASE+iUrl);
    	cn.timeout(5000);
    	cn.header("Referer", urlData);
    	doc=cn.get();
    	Elements els=doc.select(".dados");
    	Elements rows=els.get(0).select("tr");
    	List<INEDataRow> headers=parseHeadersData(rows);
    	List<INEDataRow> data=parseContentData(rows);
    	return new INEResultData(headers, data);
	}
	
	/**
	 * Responsible for the parsing of all data rows
	 * @param rows {@link Elements} {@link Collection} of tr dom elements
	 * @return {@link List} of {@link INEDataRow} elements
	 */
	private List<INEDataRow> parseContentData(Elements rows){
		List<INEDataRow> dRows=new ArrayList<INEDataRow>();
		for(Element row : rows){
			Elements thEls=row.select("td");
			if(thEls.size()!=0){
				List<String> cols=new ArrayList<String>();
				for(Element col:thEls){
					cols.add(col.text());
				}
				dRows.add(new INEDataRow(cols));
			}
		}
		return dRows;
	}
	
	/**
	 * Method responsible for the parsing of the header 
	 * @param rows {@link Elements} {@link Collection} of rows in the table
	 * @return {@link List} of {@link INEDataRow}
	 */
	private List<INEDataRow> parseHeadersData(Elements rows){
		List<INEDataRow> hrows=new ArrayList<INEDataRow>();
		for(Element row : rows){
			Elements thEls=row.select("th");
			if(thEls.size()!=0){
				List<String> cols=new ArrayList<String>();
				for(Element col:thEls){
					cols.add(col.text());
				}
				hrows.add(new INEDataRow(cols));
			}
		}
		return hrows;
	}
}
