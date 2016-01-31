package org.pub.data.sources.vimeo.domain;

import org.pt.pub.data.sources.domain.AbstractData;

/**
 * This is a container class that will hold the information about a movie in vimeo
 * @author balhau
 *
 */
public class VimeoVideo extends AbstractData{
	private String movieUrl;
	private String title;
	private String description;
	/**
	 * T
	 * @param title {@link String} Video title
	 * @param description {@link String} Description about the video
	 * @param movieUrl {@link String} The url that holds the video binary
	 */
	public VimeoVideo(String title, String description,String movieUrl){
		this.movieUrl=movieUrl;
		this.title=title;
		this.description=description;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	
}
