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
	private String url;
	private String name;
	
	public BeachEntry(){}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
