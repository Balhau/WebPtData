package groovy.org.pub.data.test.tap

import org.pub.pt.data.sources.tap.TAP
import org.pub.pt.data.sources.tap.domain.Destination
import org.pub.pt.data.sources.tap.domain.FlightDetail
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

    def "Get flights from madrid to porto"(){
        when:
            Map<String,List<FlightDetail>> flights=tap.getFlights(
                    "MAD","OPO","25.10.2017","01.11.2017","1"
            )
        then:
            flights.keySet().size() != 0
    }
}
