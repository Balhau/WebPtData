package org.pub.global.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.pub.pt.data.sources.domain.AbstractData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="tableRow")
public class TableRow extends AbstractData{
	List<String> column;
	
	public TableRow(){
		this.column=new ArrayList<String>();
	}
	
	public TableRow(List<String> data){
		this.column=data;
	}

	public List<String> getData() {
		return column;
	}

	public void setData(List<String> data) {
		this.column = data;
	}
	
	
}