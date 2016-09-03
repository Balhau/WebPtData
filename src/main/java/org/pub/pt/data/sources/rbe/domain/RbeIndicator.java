package org.pub.pt.data.sources.rbe.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.pub.pt.data.sources.domain.AbstractData;


/**
 * Domain object with information of a statistic link for the Rede de Bibliotecas Escolares
 * @author balhau
 *
 */
@XmlRootElement
public class RbeIndicator extends AbstractData {
	private int categorie;
	private int serie;
	private String url;
	private String title;
	private String description;
	private String data;
	
	public RbeIndicator(){
		
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
