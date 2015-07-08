package org.pt.pub.data;



import java.util.Calendar;

import org.pt.pub.data.sources.ipma.Ipma;

public class ipma {
	public static void main(String[] args) throws Exception{		
		
		Calendar c=Calendar.getInstance();
		c.set(2000, 1, 1);
		System.out.println(new Ipma().getSeismicActivity(c.getTime()));
	
		/**Ipma ipm=new Ipma();
		List<GeoWeather<?>> sdata=ipm.getForecastDayZero();
		LandWeather ola=new LandWeather();
		System.out.println(sdata.get(0).toXML());
	    System.out.println(sdata.get(1).toXML());
		System.out.println(sdata.get(2).toXML());*/
	}
}
