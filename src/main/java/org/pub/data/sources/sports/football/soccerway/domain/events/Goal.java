package org.pub.data.sources.sports.football.soccerway.domain.events;

import org.pub.data.sources.sports.football.soccerway.domain.Player;

/**
 * Represents a Goal in a match event
 * Created by balhau on 9/24/16.
 */
public class Goal extends MatchEvent {

    private Assistence assistence;

    public Goal(Player player, Assistence assistence) {
        super(MatchEventType.GOAL, player);
        this.assistence = assistence;
    }

    public Assistence getAssistence() {
        return assistence;
    }
}
