package org.pt.pub.data.sources.ipma.domain;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Uv")
public class Uv extends AbstractData{
	private String startDate;
	private String endDate;
	private float iuv;
	private String iuvHour;
	
	public Uv(){
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
