package org.pt.pub.data;

import java.io.IOException;

import org.pt.pub.data.sources.ine.INEDataSource;



/**
 * Hello world!
 * This will hold quick demo to the services
 */
public class App 
{
	
	
    public static void main( String[] args ) throws IOException
    {
    	INEDataSource ine=new INEDataSource();
    	ine.INETests();
    }
}
