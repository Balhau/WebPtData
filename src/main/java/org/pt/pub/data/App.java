package org.pt.pub.data;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.pt.pub.data.exceptions.EncodingException;
import org.pt.pub.data.sources.ine.INEServices;
import org.pt.pub.data.sources.ine.datatypes.INEDataSource;



/**
 * Hello world!
 * This will hold quick demo to the services
 */
public class App 
{
	
	
    public static void main( String[] args ) throws IOException, EncodingException
    {
    	INEDataSource ine=new INEDataSource();
    	INEServices ineS=ine.getAvailableServices(1, 25);
    	ine.ineTests("http://www.ine.pt/xportal/xmain?xpid=INE&xpgid=ine_indicadores&indOcorrCod=0001687&contexto=bd&selTab=tab2");
//    	System.out.println(ineS.toString());
//    	System.out.println(ineS.toJSON());
//    	System.out.println(ineS.toXML());
    	
    }
}
