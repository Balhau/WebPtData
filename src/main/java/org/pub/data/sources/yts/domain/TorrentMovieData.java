package org.pub.data.sources.yts.domain;

/**
 * Created by balhau on 9/9/16.
 */
public class TorrentMovieData {
    private final String url;
    private final String description;

    public TorrentMovieData(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
