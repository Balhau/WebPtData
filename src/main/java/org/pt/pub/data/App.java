package org.pt.pub.data;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



/**
 * Hello world!
 * This will hold quick demo to the services
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	String url="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006895&contexto=bd&selTab=tab2";
    	Connection cn=Jsoup.connect(url);
    	cn.header("Referer","http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0006895&contexto=bd&selTab=tab2");
    	Document doc=cn.get();
    	
    	Elements els=doc.select("body");
    	String urlDATA="http://www.ine.pt"+els.get(0).select("iframe").get(0).attr("src");
    	String urlNData="http://www.ine.pt/bddXplorer/htdocs/bddXplorer04.jsp?indOcorrCod=0006895&contexto=bd&userLoadSave=&lang=PT";
    	String urlN=urlNData;
    	System.out.println(urlN);
    	Document docD=Jsoup.connect(urlN).get();
    	System.out.println(docD);
//    	System.out.println(docD.select("table"));
    }
}
