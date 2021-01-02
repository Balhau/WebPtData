package org.pub.data.sources.yts.domain;

import java.util.List;

/**
 * Domain object for the YIFY torrent data information
 * Created by balhau on 9/4/16.
 */
public class YifyTorrent {
    private final String description;
    private final List<TorrentMovieData> torrentLinks;
    private final Ranking imdb;
    private final Ranking rottenTomatoesCritics;
    private final Ranking getRottenTomatoesAudience;
    private final int likes;
    private final int year;
    private final String imageURL;

    public YifyTorrent(String description, List<TorrentMovieData> magnetLinks, Ranking imdb,
                       Ranking rtCritics, Ranking rtAudience, int likes, int year,
                       String imageURL) {
        this.description = description;
        this.torrentLinks = magnetLinks;
        this.imdb = imdb;
        this.rottenTomatoesCritics = rtCritics;
        this.getRottenTomatoesAudience = rtAudience;
        this.likes = likes;
        this.year = year;
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public List<TorrentMovieData> getTorrentLinks() {
        return torrentLinks;
    }

    public Ranking getImdb() {
        return imdb;
    }

    public Ranking getRottenTomatoesCritics() {
        return rottenTomatoesCritics;
    }

    public Ranking getGetRottenTomatoesAudience() {
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
