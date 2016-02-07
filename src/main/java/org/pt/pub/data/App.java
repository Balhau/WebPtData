package org.pt.pub.data;

import java.io.IOException;


import org.pt.pub.data.exceptions.EncodingException;
import org.pt.pub.data.sources.ine.Ine;
import org.pt.pub.data.sources.ine.domain.INEResultData;
import org.pt.pub.data.sources.ine.domain.INEServices;



/**
 * Hello world!
 * This will hold quick demo to the services
 */
public class App 
{
    public static void main( String[] args ) throws IOException, EncodingException
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
