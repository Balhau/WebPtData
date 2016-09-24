package org.pub.data.sources.sports.football.soccerway.domain;

import org.pub.data.sources.sports.football.soccerway.domain.events.MatchEvent;

import java.util.List;

/**
 * Created by vitorfernandes on 9/24/16.
 */
public class Match {
    private final Team home;
    private final Team away;
    private final List<MatchEvent> events;
    private final int homeScore;
    private final int awayScore;

    public Match(Team home,Team away,List<MatchEvent> events,int homeScore,int awayScore){
        this.home=home;this.away=away;
        this.events=events;this.homeScore=homeScore;
        this.awayScore=awayScore;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public List<MatchEvent> getEvents() {
        return events;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
