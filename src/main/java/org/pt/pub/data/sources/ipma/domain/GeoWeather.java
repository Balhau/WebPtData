package org.pt.pub.data.sources.ipma.domain;
import javax.xml.bind.annotation.XmlElement;

import org.pt.pub.data.sources.AbstractData;


/**
 * This class will hold information for specific place regarding a generic type  
 * @author balhau
 *
 * @param <T>
 */
public abstract class GeoWeather<T> extends AbstractData{
	private int fullmapshow;
	private double latitude;
	private double longitude;
	private int localId;
	private String name;
	private int periodId;
	
	

	public abstract T getData();
	public abstract void setData(T props);
	

	public int getFullmapshow() {
		return fullmapshow;
	}

	public void setFullmapshow(int fullmapshow) {
		this.fullmapshow = fullmapshow;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}
}
