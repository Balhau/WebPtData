package org.pub.data.test.tap

import org.pt.pub.data.sources.tap.TAP
import org.pt.pub.data.sources.tap.domain.Destination
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class TapTest extends Specification{
    private TAP tap

    def setup(){
        tap=new TAP()
    }

    def "Get destinations for 'Mad' query search"(){
        when:
            List<Destination> departures=tap.searchDeparture("Mad")
        then:
            departures.size() > 0
    }

    def "Get possible destinations from departure code"(){
        when:
            List<Destination> departures=tap.searchDeparture("Porto")
            List<Destination> arrivals=tap.findPossibleDestinations(departures.get(0).code)
        then:
            arrivals.size() > 0
    }


}
