package org.pub.pt.data.sources.ipma.domain;

import org.pub.pt.data.sources.domain.AbstractData;

public abstract class BaseInfo extends AbstractData {
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
