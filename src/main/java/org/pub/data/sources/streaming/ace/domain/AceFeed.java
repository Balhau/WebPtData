package org.pub.data.sources.streaming.ace.domain;

/**
 * This is just a container for acestream feed endpoints
 */
public class AceFeed {
    private final String url;
    private final String description;

    public AceFeed(String url, String description) {
        this.description = description;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
