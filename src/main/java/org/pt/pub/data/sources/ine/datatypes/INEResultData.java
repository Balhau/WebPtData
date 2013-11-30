package org.pt.pub.data.sources.ine.datatypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

@XmlRootElement(name="INEResultData")
public class INEResultData extends AbstractData{
	@XmlElement(type=INEDataRow.class,name="data")
	private List<INEDataRow> dataRows;
	@XmlElement(type=INEDataRow.class,name="header")
	private List<INEDataRow> headerRows;
	
	public INEResultData(){
		dataRows=new ArrayList<INEDataRow>();
		headerRows=new ArrayList<INEDataRow>();
	}
	
	public INEResultData(List<INEDataRow> headers,List<INEDataRow> data){
		dataRows=data;
		headerRows=headers;
	}

	public List<INEDataRow> getDataRows() {
		return dataRows;
	}

	public List<INEDataRow> getHeaderRows() {
		return headerRows;
	}
	
	
}
