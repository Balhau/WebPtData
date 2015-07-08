package org.pt.pub.data.sources.rbe.domain;

import java.util.List;

/**
 * Domain object that represents the data from indicator feature.
 * @author balhau
 *
 */
public class RbeIndicator {
	private String[] categories;
	private List<String> series;
	private String[] seriesNames;
	
	public RbeIndicator(){
		
	}
	
	public RbeIndicator(String[] categories,List<String> series,String[] seriesNames){
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

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public String[] getSeriesNames() {
		return seriesNames;
	}

	public void setSeriesNames(String[] seriesNames) {
		this.seriesNames = seriesNames;
	}
}
