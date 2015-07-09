package org.pt.pub.data.sources.bdp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
 * This class exports data/statistics from the 
 * <a href="https://www.bportugal.pt" target="_blank">Banco de Portugal</a> 
 * institution.<br><br>
 * 
 * The statistical data that is scrapped and returned by this service resides in the 
 * <a href="http://www.bportugal.pt/EstatisticasWeb/" target="_blank">Estatisticas Web</a> web endpoint,
 * a service that is available from the institution<br><br>
 * 
 * <b>Tutorial:</b><br><br>
 * <pre>
 *  //Instantiate the class
 *  BancoPortugal bdp=new BancoPortugal();
 * 
 *  //Fetch the categories available
 *  TableData categories=bdp.getCategories();
 *  
 *  //Get id of category
 *  String catId=categories.getRows().get(0).getData().get(2));
 *  
 *  //Get the series for the categories selected
 *  TableData series=bdp.getSeriesForCategorie(catId);
 *  
 *  //Get series data for a specific series
 *  List&lt;String&gt; idSeries=new ArrayList&lt;String&gt;();
 *  String idSerie=series.getRows().get(0).getData().get(2));
 *  idSeries.add(idSerie);
 *  TableData dataForSerie=bdp.getSeriesForCategorie(idSeries,new Date());
 *  
 * </pre>
 * 
 * @author balhau
 *
 */
public class BancoPortugal extends AbstractDataSource{
	/**
	 * Representing the host of the Banco de Portugal institution
	 */
	public static final String HOST="http://www.bportugal.pt";
	/**
	 * Represents the categories web endpoint
	 */
	public static final String CATEGORIES_URL=HOST+"/EstatisticasWeb/SeriesCronologicas.aspx";
	/**
	 * Represents the filter for the series
	 */
	public static final String FILTRO_SERIES=HOST+"/EstatisticasWeb/FiltroSeries.aspx?IDs=";
	/**
	 * Pattern to be used to find the javascript variable that holds the data about 
	 * categories
	 */
	public static final String CATEGORIES_INDEX_PATTERN="var tvwCategsClientData =";
	/**
	 * Pattern to be used to find the javascript variable that holds the data about series
	 */
	public static final String SERIES_INDEX_PATTERN="var tvwSeriesClientData =";
	/**
	 * Web endpoint needed to fetch the series data
	 */
	public static final String SERIES_CONTROLLER_DATA=HOST+"/EstatisticasWeb/ServerSESGrid.aspx?ClientDateStart=31-03-1996&ClientDateEnd=%1$2s&ClientSeriesList=%2$2s&ClientNObs=0&HasSamePeriods=False";
	/**
	 * Timeout used to override the default one since the service is extremely slow
	 */
	public static final int HTTP_TIMEOUT=10*1000;
	
	/**
	 * Returns a {@link TableData} withe information regarding the categories we can query
	 * @return {@link TableData} categories information
	 * @throws Exception in case error is found while parsing data
	 */
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
	
	/**
	 * This is a utility method that transforms a {@link List} of seriesid and a {@link Date} parameter
	 * into a {@link String} that represents the url that can be used to query the data we want be 
	 * matched with the parameters
	 * @param seriesIdList {@link List} of {@link String} with the of series
	 * @param endDate {@link Date} that represents the last data to be retrieved
	 * @return {@link String} the web endpoint for the data
	 */
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
	
	/**
	 * This returns the data relative to a specific category id
	 * @param categorieID {@link String} id of the category
	 * @return {@link TableData} the data of the selected category
	 * @throws Exception if error exists while parsing data
	 */
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
	 * @param scripts {@link Elements} is a {@link Collection} of {@link Element} that can be queried
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
	
	/**
	 * Private method that check if the category is a leaf of the tree
	 * @param categorieId {@link String} with the id of the category
	 * @return {@link Boolean} 
	 */
	private boolean isFinalCategorie(String categorieId){
		return (categorieId.length() - categorieId.replaceAll("_", "").length()) == 3;
	}
	
	/**
	 * Private method that return the index of the {@link Element} of the script containing the 
	 * series information
	 * @param scripts {@link Elements} is a {@link Collection} of {@link Element}
	 * @return {@link Integer} offset of the wanted element
	 */
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
