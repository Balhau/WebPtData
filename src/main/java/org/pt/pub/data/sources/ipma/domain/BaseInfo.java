package org.pt.pub.data.sources.ipma.domain;

import org.pt.pub.data.sources.AbstractData;

public abstract class BaseInfo extends AbstractData{
	protected String endTime;
	protected String startTime;
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
