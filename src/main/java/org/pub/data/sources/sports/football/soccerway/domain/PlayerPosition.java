package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.Optional;

/**
 * Created by balhau on 9/22/16.
 */
public enum PlayerPosition {

    GOALKEEPER(new String[]{"goleiro"});


    private String[] descriptions;

    private PlayerPosition(String[] descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Parse enum from string description
     *
     * @param description String Description of the player position
     * @return PlayerPosition
     */
    public static Optional<PlayerPosition> fromDescription(String description) {
        PlayerPosition[] playerPositions = PlayerPosition.values();
        for (PlayerPosition player : playerPositions) {
            for (String desc : player.descriptions) {
                if (desc.trim().toLowerCase().equals(description.toLowerCase().trim())) {
                    return Optional.of(player);
                }
            }
        }
        return Optional.empty();
    }


}
