package org.pt.pub.data.sources.ipma.domain;

import java.util.Date;

import org.pt.pub.data.sources.AbstractData;

public class UvWeather extends AbstractData{
	private Date startDate;
	private Date endDate;
	private float iuv;
	private String iuvHour;
	
	public UvWeather(){
		
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getIuv() {
		return iuv;
	}

	public void setIuv(float iuv) {
		this.iuv = iuv;
	}

	public String getIuvHour() {
		return iuvHour;
	}

	public void setIuvHour(String iuvHour) {
		this.iuvHour = iuvHour;
	}
}
