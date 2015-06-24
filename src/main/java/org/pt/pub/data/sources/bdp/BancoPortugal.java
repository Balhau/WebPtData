package org.pt.pub.data.sources.bdp;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.codehaus.groovy.util.StringUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.AbstractDataSource;
import org.pt.pub.data.sources.bdp.domain.TableRow;
import org.pt.pub.data.sources.bdp.domain.TableData;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.utils.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * This class exports data/statistics from the Banco de Portugal institution
 * @author balhau
 *
 */
public class BancoPortugal extends AbstractDataSource{
	
	public static final String HOST="http://www.bportugal.pt";
	public static final String CATEGORIES_URL=HOST+"/EstatisticasWeb/SeriesCronologicas.aspx";
	public static final String FILTRO_SERIES=HOST+"/EstatisticasWeb/FiltroSeries.aspx?IDs=";
	public static final String CATEGORIES_INDEX_PATTERN="var tvwCategsClientData =";
	public static final String SERIES_INDEX_PATTERN="var tvwSeriesClientData =";
	public static final String SERIES_CONTROLLER_DATA=HOST+"/EstatisticasWeb/ServerSESGrid.aspx?ClientDateStart=31-03-1996&ClientDateEnd=%1$2s&ClientSeriesList=%2$2s&ClientNObs=0&HasSamePeriods=False";
	
	public TableData getCategories() throws Exception{
		Connection cn=Jsoup.connect(CATEGORIES_URL);
		TableData cl=new TableData();
		TableRow categorie;
		Document doc=cn.get();
		Elements scripts = doc.getElementsByTag(HtmlTag.SCRIPT);
		Element scriptData=scripts.get(getCategoriesScriptIndex(scripts));
		String data=scriptData.data().replace(BancoPortugal.CATEGORIES_INDEX_PATTERN, "");
		data=data.substring(0, data.length()-1).trim();
		//System.out.println(data);
		JsonArray dataArray=(JsonArray)new JsonParser().parse(data);
		for(JsonElement line:dataArray){
			categorie=new TableRow();
			if(isFinalCategorie(((JsonArray)line).get(0).getAsString())){
				for(JsonElement el : (JsonArray) line){
					categorie.getData().add(el.getAsString());
				}
				cl.getRows().add(categorie);
			}
		}
		return cl;
	}
	
	private String buildSeriesDataURL(List<String> seriesIdList,String endDate){
		StringBuilder sb=new StringBuilder();
		Formatter f =new Formatter(sb, Locale.US);
		StringUtils.join(seriesIdList, ",");
		return sb.toString();
	}
	
	public TableData getSeriesForCategorie(String categorieID) throws Exception{
		TableData data=new TableData();
		String url=FILTRO_SERIES+categorieID;
		Connection cn = Jsoup.connect(url);
		Document doc=cn.get();
		Elements scripts = doc.getElementsByTag(HtmlTag.SCRIPT);
		Element scriptData=scripts.get(getSeriesScriptIndex(scripts));
		String sdata=scriptData.data().replace(BancoPortugal.SERIES_INDEX_PATTERN, "");
		sdata=sdata.substring(0, sdata.length()-1).trim();
		JsonArray dataArray=(JsonArray)new JsonParser().parse(sdata);
		TableRow serie;
		for(JsonElement line:dataArray){
			serie=new TableRow();
			for(JsonElement el : (JsonArray) line){
				serie.getData().add(el.getAsString());
			}
			data.getRows().add(serie);
		}
		return data;
	}
	
	private int getCategoriesScriptIndex(Elements scripts){
		int i=0;
		for(Element node : scripts){
			if(node.data().contains(CATEGORIES_INDEX_PATTERN)){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private boolean isFinalCategorie(String categorieId){
		return (categorieId.length() - categorieId.replaceAll("_", "").length()) == 3;
	}
	
	private int getSeriesScriptIndex(Elements scripts){
		int i=0;
		for(Element node : scripts){
			if(node.data().contains(SERIES_INDEX_PATTERN)){
				return i;
			}
			i++;
		}
		return -1;
	}
}
