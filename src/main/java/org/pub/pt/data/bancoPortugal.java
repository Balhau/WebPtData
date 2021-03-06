package org.pub.pt.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.pub.pt.data.sources.bdp.BancoPortugal;
import org.pub.global.domain.TableData;

public class bancoPortugal {
	public static final String HOST="http://www.bportugal.pt";
	public static final String SERIES_CRON=HOST+"/EstatisticasWeb/SeriesCronologicas.aspx";
	public static final String FILTRO_SERIES=HOST+"/EstatisticasWeb/FiltroSeries.aspx?IDs=826848";
	public static final String SERIES_CONTROLLER_DATA=HOST+"/EstatisticasWeb/ServerSESGrid.aspx?ClientDateStart=31-03-1996&ClientDateEnd=31-03-2015&ClientSeriesList=2068278,2068279&ClientNObs=0&HasSamePeriods=False";
	public static final int SCRIPT_DATA_INDEX=46;
	
	public static void main(String[] args) throws Exception{
		BancoPortugal bp=new BancoPortugal();
		//TableData categories=bp.getCategories();
		//System.out.println(categories.toXML());
		//TableData series=bp.getSeriesForCategorie(categories.getRows().get(0).getData().get(2));
		//System.out.println(series.toXML());
		List<String> idSerie=new ArrayList<String>();
		idSerie.add("2031463");
		idSerie.add("2027392");
		List<TableData> seriesDataO=null;
		TableData metadataForSeries=seriesDataO.get(0);
		TableData seriesData=seriesDataO.get(1);
		System.out.println(metadataForSeries.toJSON());
		System.out.println(seriesData.toJSON());
		/*System.out.println(categories.toXML());
		System.out.println(categories.getRows().size());
		System.out.println(bp.getSeriesForCategorie(categories.getRows().get(0).getData().get(2)).toXML());
		*/
		
	}
}
