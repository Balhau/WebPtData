package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.Optional;

/**
 * Created by vitorfernandes on 9/25/16.
 */
public enum PlayerFoot{
    LEFT(new String[]{"esquerdo"}),
    RIGHT(new String[]{"direito"});

    private String[] descriptions;

    private PlayerFoot(String[] descriptions){
        this.descriptions=descriptions;
    }

    /**
     * Parse enum from string description
     * @param description String Description of the player position
     * @return PlayerPosition
     */
    public static Optional<PlayerFoot> fromDescription(String description){
        PlayerFoot[] playerFoots = PlayerFoot.values();
        for(PlayerFoot foot : playerFoots) {
            for (String desc : foot.descriptions) {
                if(desc.trim().toLowerCase().equals(description.toLowerCase().trim())) {
                    return Optional.of(foot);
                }
            }
        }
        return Optional.empty();
    }
}
