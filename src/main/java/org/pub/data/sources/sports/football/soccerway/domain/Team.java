package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.List;

/**
 * Created by balhau on 9/22/16.
 */
public class Team {

    public class TeamInfoBuilder {
        private int founded;
        private String address;
        private String country;
        private String phone;
        private String fax;
        private String email;
        private String city;
        private int stadiumCapacity;

        public TeamInfoBuilder() {

        }

        public TeamInfoBuilder Founded(int founded) {
            this.founded = founded;
            return this;
        }

        public TeamInfoBuilder Address(String address) {
            this.address = address;
            return this;
        }

        public TeamInfoBuilder Country(String country) {
            this.country = country;
            return this;
        }

        public TeamInfoBuilder Phone(String phone) {
            this.phone = phone;
            return this;
        }

        public TeamInfoBuilder Fax(String fax) {
            this.fax = fax;
            return this;
        }

        public TeamInfoBuilder Email(String email) {
            this.email = email;
            return this;
        }

        public TeamInfoBuilder City(String city) {
            this.city = city;
            return this;
        }

        public TeamInfoBuilder StadiumCapacity(int stadiumCapacity) {
            this.stadiumCapacity = stadiumCapacity;
            return this;
        }

        public TeamInfo build() {
            return new TeamInfo(
                    founded, address, country, phone,
                    fax, email, city, stadiumCapacity
            );
        }

    }

    public class TeamBuilder {
        private List<TeamSeason> teamSeasons;
        private TeamInfo teamInfo;

        public TeamBuilder() {

        }

        public TeamBuilder TeamSeasons(List<TeamSeason> teamSeasons) {
            this.teamSeasons = teamSeasons;
            return this;
        }

        public TeamBuilder TeamInfo(TeamInfo teamInfo) {
            this.teamInfo = teamInfo;
            return this;
        }

        public Team build() {
            return new Team(teamSeasons, teamInfo);
        }
    }

    public class TeamInfo {
        private final int founded;
        private final String address;
        private final String country;
        private final String phone;
        private final String fax;
        private final String email;
        private final String city;
        private final int stadiumCapacity;

        public TeamInfo(
                int founded, String address, String country, String phone,
                String fax, String email, String city, int stadiumCapacity
        ) {
            this.founded = founded;
            this.address = address;
            this.country = country;
            this.phone = phone;
            this.fax = fax;
            this.email = email;
            this.city = city;
            this.stadiumCapacity = stadiumCapacity;
        }
    }


    private final List<TeamSeason> teamSeasons;
    private final TeamInfo teamInfo;

    public Team(List<TeamSeason> teamSeasons, TeamInfo teamInfo) {
        this.teamSeasons = teamSeasons;
        this.teamInfo = teamInfo;
    }
}
