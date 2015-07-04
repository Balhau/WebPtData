package org.pt.pub.data.sources.ipma.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

/**
 * Entry for the beach url entries
 * @author balhau
 *
 */
@XmlRootElement
public class BeachEntry extends AbstractData{
	private int idBeach;
	private String name;
	
	public BeachEntry(){}
	
	
	public int getIdBeach() {
		return idBeach;
	}

	public void setIdBeach(int idBeach) {
		this.idBeach = idBeach;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
