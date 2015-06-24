package org.pt.pub.data.sources.bdp;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.AbstractDataSource;

/**
 * This class exports data/statistics from the Banco de Portugal institution
 * @author balhau
 *
 */
public class BancoPortugal extends AbstractDataSource{
	
	public static final String SCRIPT_INDEX_PATTERN="var tvwCategsClientData =";
	
	public static int getSeriesScriptIndex(Elements scripts){
		int i=0;
		for(Element node : scripts){
			if(node.data().contains(SCRIPT_INDEX_PATTERN)){
				return i;
			}
			i++;
		}
		return -1;
	}
}
