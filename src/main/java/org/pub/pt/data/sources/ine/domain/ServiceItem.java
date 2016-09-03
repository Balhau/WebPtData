package org.pub.pt.data.sources.ine.domain;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="INEService")
public class ServiceItem {
	private String url;
	private String desc;
	
	/**
	 * This class represents a container for the service of INE. The INE has several services to be
	 * consulted. For that the page represents a link and a respective description. That info
	 * is represented by this structure
	 * @param url {@link String} String that holds the url for the service
	 * @param desc {@link String} A little description of the url 
	 */
	public ServiceItem(String url,String desc){
		this.url=url;
		this.desc=desc;
	}
	
	public ServiceItem(){}

	public String getUrl() {
		return url;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
