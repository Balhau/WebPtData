package groovy.org.pub.data.test.resultdb

import org.pub.data.sources.sports.football.resultdb.ResultDb
import org.pub.data.sources.sports.football.resultdb.domain.League
import org.pub.data.sources.sports.football.resultdb.domain.LeagueInfo
import spock.lang.Specification

/**
 * Created by vitorfernandes on 9/21/16.
 */
class ResultDbTest extends Specification{
    private ResultDb resultDb;

    def setup(){
        resultDb=new ResultDb()
    }

    def "Get Leagues info"(){
        when:
            List<LeagueInfo> leagueInfo = resultDb.getAllLeaguesInfo()
        then:
            leagueInfo != null && leagueInfo.size()>0
    }

    def "Get League from LeagueInfo"(){
        when:
            List<LeagueInfo> leagueInfo = resultDb.getAllLeaguesInfo()
            League league = resultDb.getLeagueFromLeagueInfo(leagueInfo.get(0))
        then:
            league != null
    }

    def "Get Leagues"(){
        when:
            List<League> leagues = resultDb.getAllLeagues()
        then:
            leagues!=null && leagues.size()>0
    }
}
