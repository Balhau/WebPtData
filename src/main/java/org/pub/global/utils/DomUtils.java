package org.pub.global.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.configs.GlobalConfigs;
import org.pub.global.configs.HtmlTag;
import org.pub.global.domain.TableData;
import org.pub.global.domain.TableRow;

import jdk.nashorn.api.scripting.*;

/**
 * Utility class with a bunch of DOM utility methods 
 * @author balhau
 *
 */
public class DomUtils {

	private static final String RESTRICTED_HEADERS_ALLOW="sun.net.http.allowRestrictedHeaders";

	static{
		System.setProperty(RESTRICTED_HEADERS_ALLOW, "true");
	}
	
	/**
	 * Convert a table dom element into a {@link TableData} domain object
	 * @param table {@link Element}
	 * @return {@link TableData}
	 */
	public static TableData tableElementToTableData(Element table){
		TableData tb=new TableData();
		Elements rows=table.getElementsByTag(HtmlTag.TR);
		for(Element row : rows){
			Elements aux=row.getElementsByTag(HtmlTag.TD);
			Elements cols=aux.size()==0?row.getElementsByTag(HtmlTag.TH):aux;
			TableRow trow=new TableRow();
			for(Element col : cols){
				trow.getData().add(col.text());
			}
			tb.getRows().add(trow);
		}
		return tb;
	}

	/**
	 * Default way to establish a http connection
	 * @param url
	 * @return
	 * @throws Exception
     */
	public static Connection get(String url) throws Exception {
		return Jsoup.connect(url).userAgent(GlobalConfigs.USER_AGENT).timeout(GlobalConfigs.CONNECTION_TIMEOUT);
	}
	
	/**
	 * Method that extracts a List of {@link String[]} elements from a {@link ScriptObjectMirror}
	 * @param scm {@link ScriptObjectMirror} Object from nashorn framework with javascript contents
	 * @return {@link List} Of {@link String[]}
 	 */
	@SuppressWarnings("restriction")
	public static List<String[]> stringTableFromScriptObjectMirror(ScriptObjectMirror scm){
		Collection<Object> vals=scm.values();
		List<String[]> ls=new ArrayList<String[]>();
		for(Object strList : vals){
			ls.add(stringArrayFromScriptObjectMirror(((ScriptObjectMirror) strList)));
		}
		return ls;
	}
	/**
	 * Method that extracts a {@link String[]} from a {@link ScriptObjectMirror} element
	 * @param scm {@link ScriptObjectMirror} that represents an array of {@link String}
	 * @return {@link String[]}
	 */
	@SuppressWarnings("restriction")
	public static String[] stringArrayFromScriptObjectMirror(ScriptObjectMirror scm){
		Collection<Object> vals=scm.values();
		String[] l=new String[vals.size()];
		int i=0;
		for(Object str : vals){
			l[i]=""+str;
			i++;
		}
		return l;
	}
}
