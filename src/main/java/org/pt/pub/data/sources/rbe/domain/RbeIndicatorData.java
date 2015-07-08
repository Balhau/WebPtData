package org.pt.pub.data.sources.rbe.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

/**
 * Domain object that represents the data from a {@link RbeIndicator} entry.
 * @author balhau
 *
 */
@XmlRootElement
public class RbeIndicatorData extends AbstractData{
	private String[] categories;
	private List<String[]> series;
	private String[] seriesNames;
	
	public RbeIndicatorData(){
		
	}
	
	public RbeIndicatorData(String[] categories,List<String[]> series,String[] seriesNames){
		this.categories=categories;
		this.series=series;
		this.seriesNames=seriesNames;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public List<String[]> getSeries() {
		return series;
	}

	public void setSeries(List<String[]> series) {
		this.series = series;
	}

	public String[] getSeriesNames() {
		return seriesNames;
	}

	public void setSeriesNames(String[] seriesNames) {
		this.seriesNames = seriesNames;
	}
}
