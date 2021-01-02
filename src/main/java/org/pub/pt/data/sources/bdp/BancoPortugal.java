package org.pub.pt.data.sources.bdp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.pub.global.configs.HtmlTag;
import org.pub.global.domain.TableData;
import org.pub.global.domain.TableRow;

import com.balhau.utils.StringUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.pub.global.utils.DomUtils;

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
 * </pre>
 * 
 * @author balhau
 *
 */
public class BancoPortugal extends AbstractDataSource{
	/**
	 * Representing the host of the Banco de Portugal institution
	 */
	public static final String BPSTAT_HOST="https://bpstat.bportugal.pt";

	public static final String SERIES_OBSERVATIONS_PATTERN="https://bpstat.bportugal.pt/api/observations/?series_ids=%s&start_date=%s&end_date=%s";
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
		return null;
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
		String out=f.format("SERIES_CONTROLLER_DATA", dateFormat.format(endDate),strIdsList).toString();
		f.close();
		return out;
	}
}
