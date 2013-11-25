package org.pt.pub.data.sources.ine;

public class ServiceItem {
	private String url;
	private String desc;
	
	public ServiceItem(String url,String desc){
		this.url=url;
		this.desc=desc;
	}

	public String getUrl() {
		return url;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
