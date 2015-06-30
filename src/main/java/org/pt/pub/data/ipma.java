package org.pt.pub.data;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pt.pub.data.sources.ipma.Ipma;
import org.pt.pub.global.domain.TableData;

public class ipma {
	private static final String HOST="http://www.ipma.pt";
	private static final String OVERVIEW_PT=HOST+"/resources.www/internal.user/ww_overview_pt.xml";
	private static final String REFERER_SWF_HOST=HOST+"/opencms/bin/flash/vrs1.1/prev.meteo.sam.portugal.swf";
	private static final String FORECAST_DAY_0=HOST+"/resources.www/internal.user/wp_d0_pt.xml";
	private static final String SEA_STATUS=HOST+"/pt/maritima/costeira/";
	
	public static void main(String[] args) throws Exception{
		Connection cn1=Jsoup.connect(OVERVIEW_PT);
		Connection cn2=Jsoup.connect(FORECAST_DAY_0);
		Connection cn3=Jsoup.connect(SEA_STATUS);
		Document overview=cn1
				.header("Referer", REFERER_SWF_HOST)
				.get();
		
		Document forecast0=cn2
				.header("Referer", REFERER_SWF_HOST)
				.get();
		
		Document seaforecast=cn3.get();
		
		System.out.println(forecast0);
	
		Ipma ipm=new Ipma();
		List<TableData> sdata=ipm.getSeaInformation();

	}
}
