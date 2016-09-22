package org.pub.data.sources.sports.football.resultdb.domain;

import java.util.List;

/**
 * Created by vitorfernandes on 9/21/16.
 */
public class League {
    private final String name;
    private final String url;
    private final List<Season> season;

    public League(String name,String url,List<Season> season){
        this.name=name;
        this.url=url;
        this.season=season;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Season> getSeasons() {
        return season;
    }
}
