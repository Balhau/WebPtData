package org.pt.pub.data.sources.ipma.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

@XmlRootElement(name="SeaWeather")
public class Sea extends AbstractData{
	private Date startDate;
	private Date endDate;
	private int waterTemperature;
	private int waveHighId;
	private String waveHighDesc;
	private int waveDirId;
	private String waveDirDesc;
	private String waveDirSymb;
	
	public Sea(){
		
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

	public int getWaterTemperature() {
		return waterTemperature;
	}

	public void setWaterTemperature(int waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public int getWaveHighId() {
		return waveHighId;
	}

	public void setWaveHighId(int waveHighId) {
		this.waveHighId = waveHighId;
	}

	public String getWaveHighDesc() {
		return waveHighDesc;
	}

	public void setWaveHighDesc(String waveHighDesc) {
		this.waveHighDesc = waveHighDesc;
	}

	public int getWaveDirId() {
		return waveDirId;
	}

	public void setWaveDirId(int waveDirId) {
		this.waveDirId = waveDirId;
	}

	public String getWaveDirDesc() {
		return waveDirDesc;
	}

	public void setWaveDirDesc(String waveDirDesc) {
		this.waveDirDesc = waveDirDesc;
	}

	public String getWaveDirSymb() {
		return waveDirSymb;
	}

	public void setWaveDirSymb(String waveDirSymb) {
		this.waveDirSymb = waveDirSymb;
	}
	
	
}
