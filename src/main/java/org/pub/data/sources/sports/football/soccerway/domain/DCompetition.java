package org.pub.data.sources.sports.football.soccerway.domain;

/**
 * DCompetition is a shortname for description competetition. This is just a container for competition
 * pointers and the respective urls
 * Created by vitorfernandes on 9/24/16.
 */
public class DCompetition {
    private final String description;
    private final String url;

    public DCompetition(){
        this.url=null;
        this.description=null;
    }

    public DCompetition(String description,String url){
        this.description=description;this.url=url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
