package org.pt.pub.data.sources.base.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pt.pub.data.sources.AbstractData;

/**
 * This class represents an row on a response of base query.
 * @author balhau
 *
 */
@XmlRootElement
public class BaseEntry extends AbstractData{
	
	private String description;
	private float price;
	private String publication;
	private String adjudicante;
	private String adjudicatario;
	private String url;
	
	
	public BaseEntry(){
		
	}
	
	public BaseEntry(
			String description,float price,
			String publication,String adjudicante,String adjudicatario,
			String url
			){
		this.description=description;
		this.price=price;
		this.publication=publication;
		this.adjudicante=adjudicante;
		this.adjudicatario=adjudicatario;
		this.url=url;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getPublication() {
		return publication;
	}


	public void setPublication(String publication) {
		this.publication = publication;
	}


	public String getAdjudicante() {
		return adjudicante;
	}


	public void setAdjudicante(String adjudicante) {
		this.adjudicante = adjudicante;
	}


	public String getAdjudicatario() {
		return adjudicatario;
	}


	public void setAdjudicatario(String adjudicatario) {
		this.adjudicatario = adjudicatario;
	}
}
