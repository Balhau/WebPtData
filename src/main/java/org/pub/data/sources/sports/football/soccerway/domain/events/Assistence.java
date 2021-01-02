package org.pub.data.sources.sports.football.soccerway.domain.events;

import org.pub.data.sources.sports.football.soccerway.domain.Player;

/**
 * Represents an assistance for goal in a match event
 * Created by balhau on 9/24/16.
 */
public class Assistence extends MatchEvent {

    public Assistence(Player player) {
        super(MatchEventType.ASSISTENCE, player);
    }
}
