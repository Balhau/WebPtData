package org.pub.data.sources.sports.football.soccerway.domain;

import org.pub.pt.data.utilities.Builder;

import java.util.Optional;

/**
 * Created by balhau on 9/22/16.
 */
public class Player extends Person {

    public static class PlayerBuilder extends PersonBuilder implements Builder<Player> {

        private String position;
        private int height;
        private int weight;
        private PlayerFoot foot;

        public PlayerBuilder() {
            super();
        }

        public PlayerBuilder Position(String position) {
            this.position = position;
            return this;
        }

        public PlayerBuilder Height(int height) {
            this.height = height;
            return this;
        }

        public PlayerBuilder Weight(int weight) {
            this.weight = weight;
            return this;
        }

        public PlayerBuilder Foot(PlayerFoot foot) {
            this.foot = foot;
            return this;
        }

        public Player build() {
            return new Player(this.url, this.firstName, this.lastName, this.nacionality,
                    this.dateOfBirth, this.age, this.birthCountry, this.birthPlace, this.position,
                    this.height, this.weight, this.foot);
        }

    }

    private final Optional<PlayerPosition> position;
    private final int height;
    private final int weight;
    private final PlayerFoot foot;

    public Player(String url,
                  String firstName, String lastName, String nacionality,
                  String dateOfBirth, int age, String birthCountry, String birthPlace,
                  String position, int height, int weight, PlayerFoot foot
    ) {
        super(url, firstName, lastName, nacionality, dateOfBirth, age, birthCountry, birthPlace);
        this.position = PlayerPosition.fromDescription(position);
        this.height = height;
        this.weight = weight;
        this.foot = foot;
    }
}
