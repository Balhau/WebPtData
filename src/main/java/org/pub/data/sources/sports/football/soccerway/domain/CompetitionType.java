package org.pub.data.sources.sports.football.soccerway.domain;

/**
 * Created by balhau on 9/24/16.
 */
public enum CompetitionType {
    LOCAL_CLUBS("");


    private final String url;

    private CompetitionType(String url) {
        this.url = url;
    }

    public String getURL() {
        return this.url;
    }
}
