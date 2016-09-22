package org.pub.data.sources.sports.football.resultdb.domain;

/**
 * Football match result domain object
 * Created by vitorfernandes on 9/21/16.
 */
public class Result {
    private final String date;
    private final String awayTeam;
    private final String homeTeam;
    private final int pointAway;
    private final int pointHome;

    public Result(String date,String homeTeam,String awayTeam,int pointHome,int pointAway){
        this.date=date;
        this.awayTeam=awayTeam;
        this.homeTeam=homeTeam;
        this.pointAway=pointAway;
        this.pointHome=pointHome;
    }

    public int getPointHome() {
        return pointHome;
    }

    public String getDate() {
        return date;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getPointAway() {
        return pointAway;
    }

}
