package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.List;

/**
 * Created by balhau on 9/24/16.
 */
public class TeamSeason {
    private final List<Player> players;
    private final Coach coach;

    public TeamSeason(List<Player> players, Coach coach) {
        this.players = players;
        this.coach = coach;
    }

    public TeamSeason() {
        this.players = null;
        this.coach = null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }
}
