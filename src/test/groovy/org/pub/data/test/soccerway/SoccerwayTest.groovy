package org.pub.data.test.soccerway


import org.pub.data.sources.sports.football.soccerway.Soccerway
import org.pub.data.sources.sports.football.soccerway.domain.Coach
import org.pub.data.sources.sports.football.soccerway.domain.DCompetition
import org.pub.data.sources.sports.football.soccerway.domain.Player
import org.pub.data.sources.sports.football.soccerway.domain.TeamSeason
import spock.lang.Specification

/**
 * Created by balhau on 9/24/16.
 */
class SoccerwayTest extends Specification {
    private Soccerway soccerway

    def setup() {
        soccerway = new Soccerway()
    }

    def "Get Coach info"() {
        when:
        Coach coach = soccerway.getCoach("http://pt.soccerway.com/coaches/rui-vitoria/141772/")
        then:
        coach != null
    }

    def "Get Player info"() {
        when:
        Player player = soccerway.getPlayer("http://pt.soccerway.com/players/ezequiel-matias-schelotto/91348/")
        then:
        player != null
    }

    def "Get Competitions descriptions"() {
        when:
        List<DCompetition> compList = soccerway.getCompetitions()
        then:
        compList != null && compList.size() > 0
    }

    def "Get TeamSeason info"() {
        when:
        TeamSeason teamSeason = soccerway.getTeamSeason(1679, 9775)
        then:
        teamSeason != null
    }

}
