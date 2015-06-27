package org.pt.pub.data.sources.ipma.domain;

import org.pt.pub.data.sources.AbstractData;


/**
 * This class will hold information for specific place regarding a generic type  
 * @author balhau
 *
 * @param <T>
 */
public class Land<T> extends AbstractData{
	private T properties;
	private double latitude;
	private double longitude;
	private int localId;
	private String name;
	private int periodId;
	
	public Land(){
		
	}

	public T getProperties() {
		return properties;
	}

	public void setProperties(T properties) {
		this.properties = properties;
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
