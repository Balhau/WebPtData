package org.pt.pub.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

@XmlRootElement
public class BeachInfo extends AbstractData{
	private String hour;
	private float seaTotalMeter;
	private float waving;
	private String waveDirection;
	private float wavePeriod;
	private float wave;
	private float wind;
	private String windDirection;
	private int beaufortScale;
	private float waterTemperature;
	
	
	public BeachInfo(){
		
	}


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public float getSeaTotalMeter() {
		return seaTotalMeter;
	}


	public void setSeaTotalMeter(float seaTotalMeter) {
		this.seaTotalMeter = seaTotalMeter;
	}


	public float getWaving() {
		return waving;
	}


	public void setWaving(float waving) {
		this.waving = waving;
	}


	public String getWaveDirection() {
		return waveDirection;
	}


	public void setWaveDirection(String waveDirection) {
		this.waveDirection = waveDirection;
	}


	public float getWavePeriod() {
		return wavePeriod;
	}


	public void setWavePeriod(float wavePeriod) {
		this.wavePeriod = wavePeriod;
	}


	public float getWave() {
		return wave;
	}


	public void setWave(float wave) {
		this.wave = wave;
	}


	public float getWind() {
		return wind;
	}


	public void setWind(float wind) {
		this.wind = wind;
	}


	public String getWindDirection() {
		return windDirection;
	}


	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}


	public int getBeaufortScale() {
		return beaufortScale;
	}


	public void setBeaufortScale(int beaufortScale) {
		this.beaufortScale = beaufortScale;
	}


	public float getWaterTemperature() {
		return waterTemperature;
	}


	public void setWaterTemperature(float waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	
}
