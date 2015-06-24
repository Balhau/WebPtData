package org.pt.pub.data;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.bdp.BancoPortugal;
import org.pt.pub.data.sources.bdp.domain.TableData;

public class bancoPortugal {
	public static final String HOST="http://www.bportugal.pt";
	public static final String SERIES_CRON=HOST+"/EstatisticasWeb/SeriesCronologicas.aspx";
	public static final String FILTRO_SERIES=HOST+"/EstatisticasWeb/FiltroSeries.aspx?IDs=826848";
	public static final String SERIES_CONTROLLER_DATA=HOST+"/EstatisticasWeb/ServerSESGrid.aspx?ClientDateStart=31-03-1996&ClientDateEnd=31-03-2015&ClientSeriesList=2068278,2068279&ClientNObs=0&HasSamePeriods=False";
	public static final int SCRIPT_DATA_INDEX=46;
	
	public static void main(String[] args) throws Exception{
		BancoPortugal bp=new BancoPortugal();
		TableData categories=bp.getCategories();
		StringBuilder sb=new StringBuilder();
		Formatter f =new Formatter(sb, Locale.US);
		System.out.println(f.format(BancoPortugal.SERIES_CONTROLLER_DATA, "123","333"));
		/*System.out.println(categories.toXML());
		System.out.println(categories.getRows().size());
		System.out.println(bp.getSeriesForCategorie(categories.getRows().get(0).getData().get(2)).toXML());
		*/
		
	}
}
