package org.pt.pub.global.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.domain.AbstractData;

@XmlRootElement(name="tableData")
public class TableData extends AbstractData{
	private List<TableRow> rows;
	
	public TableData(){
		this.rows=new ArrayList<TableRow>();
	}
	
	public TableData(List<TableRow> rows){
		this.rows=rows;
	}

	public List<TableRow> getRows() {
		return rows;
	}

	public void setRows(List<TableRow> rows) {
		this.rows = rows;
	}
	
	
}
