package org.pt.pub.global.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.global.configs.HtmlTag;
import org.pt.pub.global.domain.TableData;
import org.pt.pub.global.domain.TableRow;

/**
 * Utility class with a bunch of DOM utility methods 
 * @author balhau
 *
 */
public class DomUtils {
	
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
	
	
}
