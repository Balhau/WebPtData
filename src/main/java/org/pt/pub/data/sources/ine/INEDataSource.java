package org.pt.pub.data.sources.ine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.AbstractDataSource;
import org.pt.pub.data.sources.ine.datatypes.INEDataRow;
import org.pt.pub.data.sources.ine.datatypes.INEResultData;
import org.pt.pub.data.sources.ine.datatypes.INEServices;
import org.pt.pub.data.sources.ine.datatypes.ServiceItem;

/**
 * This class is the main interface for the INE data. Here we load, parse and store in datastructures the data retrieved from
 * the INE web pages
 * @author balhau
 *
 */
public class INEDataSource extends AbstractDataSource{
	
	private static String INE_BASE="http://www.ine.pt";
	
	/**
	 * Default constructor
	 */
	public INEDataSource(){
	}
	
	public void INETests() throws IOException{
		String url1 ="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0007333&contexto=bd&selTab=tab2";
    	String url2 ="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0005878&contexto=bd&selTab=tab2";
    	String url=url1;
    	INEServices services=getAvailableServices(1, 50);
    	System.out.println(services.getList().size());
    	System.out.println(services.toJSON());
    	//ineTests(url);
	}
	
	/**
	 * You should use this method to retrieve an object {@link INEServices} with all the services available from the INE.
	 * This receives as argument the number of the page and the respective number of items per page
	 * @param pageNumber {@link int} Number of the page
	 * @param itemPerpage {@link int} NUmber items per page
	 * @return {@link INEServices} Object with a list of services available for you to feed into the {@link getDataFromService} method
	 * @throws IOException
	 */
	public INEServices getAvailableServices(int pageNumber,int itemPerpage) throws IOException{
		String sURL="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_base_dados&bdpagenumber="+pageNumber+"&bdind_por_pagina="+itemPerpage;
		Connection cn=Jsoup.connect(sURL);
		Document doc=cn.get();
		return parseTBodyServices(doc.select("table").get(1).select("tbody").get(0));
	}
	
	private INEServices parseTBodyServices(Element tbody){
		INEServices ines=new INEServices();
		Elements els=tbody.select("tr");
		for(int i=0;i<els.size();i++){
			ines.addItem(parseServiceRow(els.get(i)));
		}
		return ines;
	}
	
	private ServiceItem parseServiceRow(Element row){
		Element anchor=row.select("td").get(1).select("a").get(0);
		String url=INE_BASE+anchor.attr("href");
		return new ServiceItem(url, anchor.text());
	}
	
	public INEResultData getDataFromService(String urlData) throws IOException{
		Connection cn=Jsoup.connect(urlData);
    	Document doc=cn.get();
    	String iUrl=doc.select("iframe").attr("src");
    	System.out.println(INE_BASE+iUrl);
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
	
	
	public void ineTests(String urlData) throws IOException{
	    	Connection cn=Jsoup.connect(urlData);
	    	Document doc=cn.get();
	    	String iUrl=doc.select("iframe").attr("src");
	    	System.out.println(iUrl);
	    	cn=Jsoup.connect(iUrl);
	    	cn.timeout(5000);
	    	cn.header("Referer", urlData);
	    	doc=cn.get();
	    	Elements els=doc.select(".dados");
	    	Elements th=els.get(0).select("tr").get(0).select("th");
	    	System.out.println(th);
	    	/**
	    	String ref="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0007333&contexto=bd&selTab=tab2";
	    	cn.header("Referer",ref);
	    	Document doc=cn.get();
	    	
	    	Elements els= doc.select("table .dados");
	    	
	    	System.out.println(els);
	    	*/
	}
}
