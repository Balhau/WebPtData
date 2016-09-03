package org.pub.pt.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Sea extends BaseInfo{
	private int waterTemperature;
	private int waveHighId;
	private String waveHighDesc;
	private int waveDirId;
	private String waveDirDesc;
	private String waveDirSymb;
	
	public Sea(){
		
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
