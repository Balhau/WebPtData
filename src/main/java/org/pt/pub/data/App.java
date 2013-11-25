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
    	ineTests();
    }
    
    private static void ineTests() throws IOException{
    	String url="http://www.ine.pt/bddXplorer/htdocs/bddXplorer04.jsp?indOcorrCod=0007333&contexto=bd&userLoadSave=&lang=PT";
    	Connection cn=Jsoup.connect(url);
    	String ref="http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0007333&contexto=bd&selTab=tab2";
    	cn.header("Referer",ref);
    	Document doc=cn.get();
    	
    	Elements els= doc.select("table .dados");
    	
    	System.out.println(els);
    }
}
