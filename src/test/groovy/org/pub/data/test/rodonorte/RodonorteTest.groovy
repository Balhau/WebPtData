package org.pub.data.test.rodonorte

import org.pub.pt.data.sources.rodonorte.Rodonorte
import org.pub.pt.data.sources.rodonorte.domain.Destination
import org.pub.pt.data.sources.rodonorte.domain.Ride
import spock.lang.Specification

/**
 * Created by vitorfernandes on 11/5/16.
 */
class RodonorteTest extends Specification {
    private Rodonorte rodonorte;

    def setup(){
        rodonorte=new Rodonorte()
    }

    def "Get origins"(){
        when:
            List<String> origins = rodonorte.getOriginList()
        then:
            origins.size()>0
    }

    def "Get destinations for origin"(){
        when:
            List<String> origins = rodonorte.getOriginList();
            List<Destination> destinations = rodonorte.getDestinations(origins.get(0))
        then:
            destinations.size()>0
    }

    def "Get available rides"(){
        when:
            List<String> origins = rodonorte.getOriginList();
            List<Destination> destinations = rodonorte.getDestinations(origins.get(0))
            List<Ride> rideList = rodonorte.getRides(origins.get(1),destinations.get(2))
        then:
            rideList.size()>0
    }
}
