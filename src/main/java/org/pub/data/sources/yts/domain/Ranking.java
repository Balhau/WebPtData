package org.pub.data.sources.yts.domain;

/**
 * This is a entity that represents a service that provide information and ranking about a specific
 * movie
 * Created by balhau on 9/23/16.
 */
public class Ranking {
    private final String description;
    private final String url;
    private final String value;

    public Ranking(String description, String url, String value) {
        this.description = description;
        this.url = url;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getValue() {
        return value;
    }
}
