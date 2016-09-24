package org.pub.data.sources.sports.football.soccerway.domain.events;

import org.pub.data.sources.sports.football.soccerway.domain.Player;

/**
 * Abstract class for match events
 * Created by vitorfernandes on 9/24/16.
 */
public abstract class MatchEvent {
    private final MatchEventType eventType;
    private final Player player;

    public MatchEvent(MatchEventType eventType,Player player){
        this.eventType=eventType;
        this.player=player;
    }

    public MatchEventType getEventType() {
        return eventType;
    }

    public Player getPlayer() {
        return player;
    }
}
