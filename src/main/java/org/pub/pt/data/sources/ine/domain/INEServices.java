package org.pub.pt.data.sources.ine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.pub.pt.data.sources.domain.AbstractData;

/**
 * Class that represent a list of services. We created this class to be able to call toString 
 * method by overloading
 * @author balhau
 *
 */
@XmlRootElement(name="INEServices")
public class INEServices extends AbstractData{
	@XmlElement(type=ServiceItem.class,name="service")
	private List<ServiceItem> items;
	
	public INEServices(){
		this.items=new ArrayList<ServiceItem>();
	}
	
	public List<ServiceItem> getList(){
		return this.items;
	}
	
	public void addItem(ServiceItem serviceItem){
		this.items.add(serviceItem);
	}
	
	public String toJSON(){
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		ServiceItem item;
		for(int i=0;i<items.size();i++){
			item=items.get(i);
			sb.append("\n");
			if(i!=0) sb.append(",");
			sb.append("{'url':'"+item.getUrl()+"','desc':'"+item.getDesc()+"'}");
		}
		sb.append("]");
		return sb.toString();
	}
}
