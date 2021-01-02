package org.pub.data.sources.sports.football.resultdb.domain;

/**
 * Created by balhau on 9/22/16.
 */
public class LeagueInfo {
    private final String description;
    private final String url;

    public LeagueInfo(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public LeagueInfo() {
        this.description = null;
        this.url = null;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
