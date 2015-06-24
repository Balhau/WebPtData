package org.pt.pub.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.bdp.BancoPortugal;

public class bancoPortugal {
	public static final String HOST="http://www.bportugal.pt";
	public static final String SERIES_CRON=HOST+"/EstatisticasWeb/SeriesCronologicas.aspx";
	public static final String FILTRO_SERIES=HOST+"/EstatisticasWeb/FiltroSeries.aspx?IDs=826848";
	public static final String SERIES_CONTROLLER_DATA=HOST+"/EstatisticasWeb/ServerSESGrid.aspx?ClientDateStart=31-03-1996&ClientDateEnd=31-03-2015&ClientSeriesList=2068278,2068279&ClientNObs=0&HasSamePeriods=False";
	public static final int SCRIPT_DATA_INDEX=46;
	public static void main(String[] args) throws Exception{
		Connection cn=Jsoup.connect(FILTRO_SERIES);
		Document doc=cn.get();
		System.out.println(doc);
		//Elements scripts = doc.getElementsByTag("script");
		//int index=BancoPortugal.getSeriesScriptIndex(scripts);
		//String data=scripts.get(index).data().replace(BancoPortugal.SCRIPT_INDEX_PATTERN, "");
		//System.out.println(data.substring(0, data.length()-1).trim());
		
	}
}
