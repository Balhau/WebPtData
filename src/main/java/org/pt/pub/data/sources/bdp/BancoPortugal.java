package org.pt.pub.data.sources.bdp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.AbstractDataSource;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.domain.TableData;
import org.pt.pub.global.domain.TableRow;

import com.balhau.utils.StringUtils;
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
	public static final int HTTP_TIMEOUT=10*1000;
	
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
	
	private String buildSeriesDataURL(List<String> seriesIdList, Date endDate){
		StringBuilder sb=new StringBuilder();
		Formatter f =new Formatter(sb, Locale.US);
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		String strIdsList=StringUtils.join(seriesIdList, ",");
		String out=f.format(SERIES_CONTROLLER_DATA, dateFormat.format(endDate),strIdsList).toString();
		f.close();
		return out;
	}
	
	/**
	 * Method to retrieve data for set of series
	 * @param seriesList {@link List} List of string with the ids for the series
	 * @param endDate {@link Date} Date with the end of the series
	 * @return {@link TableData} with the data for the series
	 * @throws Exception if error while parsing data
	 */
	public List<TableData> getDataForSeries(List<String> seriesList,Date endDate) throws Exception {
		List<TableData> ldata=new ArrayList<TableData>();
		TableData mtadata=new TableData();
		TableData data=new TableData();
		TableRow row;
		String url=buildSeriesDataURL(seriesList, endDate);
		Connection cn=Jsoup.connect(url);
		cn.timeout(HTTP_TIMEOUT);
		Document doc=cn.get();
		Elements series=doc.getElementsByTag("serie");
		Elements metadataRows=doc.getElementsByTag("mdata");
		
		//Get the metadata
		for(Element metadata:metadataRows){
			row=new TableRow();
			Elements cells = metadata.getAllElements();
			for(Element cell : cells){
				row.getData().add(cell.text());
			}
			mtadata.getRows().add(row);
		}
		
		//Get the data
		for(Element serie:series){
			row=new TableRow();
			Elements cells=serie.getAllElements();
			for(Element cell : cells){
				row.getData().add(cell.text());
			}
			data.getRows().add(row);
		}
		
		ldata.add(mtadata);
		ldata.add(data);
		return ldata;
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
	
	/**
	 * Private method that return the index of the {@link Element} of the script containing the 
	 * categories information 
	 * @param scripts {@link Elements} contains a set of {@link Element} that can be queried
	 * @return {@link Integer} offset of the wanted element
	 */
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
