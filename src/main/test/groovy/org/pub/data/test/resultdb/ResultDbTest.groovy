package groovy.org.pub.data.test.resultdb

import org.pub.data.sources.sports.football.resultdb.ResultDb
import org.pub.data.sources.sports.football.resultdb.domain.League
import spock.lang.Specification

/**
 * Created by vitorfernandes on 9/21/16.
 */
class ResultDbTest extends Specification{
    private ResultDb resultDb;

    def setup(){
        resultDb=new ResultDb()
    }

    def "Get Leagues"(){
        when:
        List<League> leagues = resultDb.getAllLeagues()
        then:
        leagues!=null && leagues.size()>0
    }
}
