package org.pub.data.sources.yts.domain;

import java.util.List;

/**
 * Domain object for the YIFY torrent data information
 * Created by vitorfernandes on 9/4/16.
 */
public class YifyTorrent {
    private final String description;
    private final List<String> torrentLinks;
    private final String imdb;
    private final String rottenTomatoesCritics;
    private final String getRottenTomatoesAudience;
    private final int likes;
    private final int year;
    private final String imageURL;

    public YifyTorrent(String description,List<String> magnetLinks,String imdb,
                       String rtCritics,String rtAudience,int likes,int year,
                       String imageURL){
        this.description=description;this.torrentLinks=magnetLinks;
        this.imdb=imdb;this.rottenTomatoesCritics=rtCritics;
        this.getRottenTomatoesAudience=rtAudience;this.likes=likes;
        this.year=year;this.imageURL=imageURL;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTorrentLinks() {
        return torrentLinks;
    }

    public String getImdb() {
        return imdb;
    }

    public String getRottenTomatoesCritics() {
        return rottenTomatoesCritics;
    }

    public String getGetRottenTomatoesAudience() {
        return getRottenTomatoesAudience;
    }

    public int getLikes() {
        return likes;
    }

    public int getYear() {
        return year;
    }

    public String getImageURL() {
        return imageURL;
    }
}
