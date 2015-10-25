package org.pt.pub.data.sources.base.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.domain.AbstractData;

@XmlRootElement
public class BaseQueryResponse extends AbstractData{
	private List<BaseEntry> items;
	private int numOfResults;
	private int startOffset;
	private int endOffset;
	
	public BaseQueryResponse(){
		
	}
	
	public BaseQueryResponse(List<BaseEntry> items,int numOfResults,int startOffset,int endOffset){
		this.items=items;
		this.numOfResults=numOfResults;
		this.startOffset=startOffset;
		this.endOffset=endOffset;
	}

	public List<BaseEntry> getItems() {
		return items;
	}

	public void setItems(List<BaseEntry> items) {
		this.items = items;
	}

	public int getNumOfResults() {
		return numOfResults;
	}

	public void setNumOfResults(int numOfResults) {
		this.numOfResults = numOfResults;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public int getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(int endOffset) {
		this.endOffset = endOffset;
	}
	
	
}
