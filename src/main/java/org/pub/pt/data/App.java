package org.pub.pt.data;


import org.pub.pt.data.sources.ine.Ine;
import org.pub.pt.data.sources.ine.domain.INEResultData;
import org.pub.pt.data.sources.ine.domain.INEServices;


/**
 * Hello world!
 * This will hold quick demo to the services
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Ine ine=new Ine();
    	INEServices ineS=ine.getAvailableServices(1, 25);
    	String s1="http://www.ine.pt/xportal/xmain?xpid=INE&amp;xpgid=ine_indicadores&amp;indOcorrCod=0001699&amp;contexto=bd&amp;selTab=tab2";
    	//ine.ineTests(ineS.getList().get(0).getUrl());
    	String s0=ineS.getList().get(0).getUrl();
    	System.out.println(s0);
    	System.out.println(s1);
    	INEResultData data=ine.getDataFromService(s1);
    	System.out.println(data.toJSON());
//    	System.out.println(ineS.toString());
//    	System.out.println(ineS.toJSON());
//    	System.out.println(ineS.toXML());
    	
    }
    
    
}
