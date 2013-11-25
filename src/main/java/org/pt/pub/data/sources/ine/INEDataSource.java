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

/**
 * This class 
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
    	System.out.println(services);
    	//ineTests(url);
	}
	
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
	
	
	private void ineTests(String urlData) throws IOException{
	    	Connection cn=Jsoup.connect(urlData);
	    	Document doc=cn.get();
	    	String iUrl=doc.select("iframe").attr("src");
	    	System.out.println(INE_BASE+iUrl);
	    	cn=Jsoup.connect(INE_BASE+iUrl);
	    	cn.timeout(5000);
	    	cn.header("Referer", urlData);
	    	doc=cn.get();
	    	Elements els=doc.select(".dados");
	    	Elements th=els.get(0).select("tr");
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
