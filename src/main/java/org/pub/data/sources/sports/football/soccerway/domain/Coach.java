package org.pub.data.sources.sports.football.soccerway.domain;

import org.pub.pt.data.utilities.Builder;

/**
 * Created by balhaus on 9/22/16.
 */
public class Coach extends Person {

    public static class CoachBuilder extends PersonBuilder implements Builder<Coach> {
        public CoachBuilder() {
            super();
        }

        @Override
        public Coach build() {
            return new Coach(this.url, this.firstName, this.lastName, this.nacionality,
                    this.dateOfBirth, this.age, this.birthCountry, this.birthPlace
            );
        }
    }

    public Coach(String url,
                 String firstName, String lastName, String nacionality,
                 String dateOfBirth, int age, String birthCountry, String birthPlace) {
        super(url, firstName, lastName, nacionality, dateOfBirth, age, birthCountry, birthPlace);

    }
}
