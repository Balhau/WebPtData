package org.pt.pub.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ipma {
	private static final String HOST="http://www.ipma.pt";
	private static final String OVERVIEW_PT=HOST+"/resources.www/internal.user/ww_overview_pt.xml";
	private static final String REFERER_SWF_HOST=HOST+"/opencms/bin/flash/vrs1.1/prev.meteo.sam.portugal.swf";
	private static final String FORECAST_DAY_0=HOST+"/resources.www/internal.user/wp_d0_pt.xml";
	public static void main(String[] args) throws Exception{
		Connection cn1=Jsoup.connect(OVERVIEW_PT);
		Connection cn2=Jsoup.connect(FORECAST_DAY_0);
		Document overview=cn1
				.header("Referer", REFERER_SWF_HOST)
				.get();
		
		Document forecast0=cn2
				.header("Referer", REFERER_SWF_HOST)
				.get();
	
		System.out.println(forecast0);

	}
}
