package org.pt.pub.data.sources.ine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="DataRow")
public class INEDataRow extends AbstractData{
	
	private List<String> columns;
	
	public INEDataRow(){
		this.columns=new ArrayList<String>() ;	
	}
	
	public INEDataRow(List<String> columns){
		this.columns=columns;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	
	
	
}
