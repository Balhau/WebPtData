package org.pub.data.sources.streaming.ace.domain;

import java.util.List;

public class AceSportsFeed {
    private String name;
    private String date;
    private String channel;
    private String language;
    private List<AceFeed> feeds;

    public AceSportsFeed(List<AceFeed> feeds, String name, String date, String channel, String language) {
        this.channel = channel;
        this.name = name;
        this.date = date;
        this.feeds = feeds;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getChannel() {
        return channel;
    }

    public String getLanguage() {
        return language;
    }

    public List<AceFeed> getFeeds() {
        return feeds;
    }
}
