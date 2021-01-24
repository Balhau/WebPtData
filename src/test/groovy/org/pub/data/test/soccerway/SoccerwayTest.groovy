package org.pub.data.test.soccerway

import org.pub.data.sources.sports.football.soccerway.Soccerway
import org.pub.data.sources.sports.football.soccerway.domain.HostDomain
import spock.lang.Specification

/**
 * Created by balhau on 9/24/16.
 */
class SoccerwayTest extends Specification {
    private Soccerway soccerway

    def setup() {
        soccerway = new Soccerway(HostDomain.PORTUGAL)
    }

    def "Get Coach info"() {
        1==2
    }

    def "Get Player info"() {
        1==2
    }

    def "Get Competitions descriptions"() {
        1==2
    }

    def "Get TeamSeason info"() {
        1==2
    }

}
