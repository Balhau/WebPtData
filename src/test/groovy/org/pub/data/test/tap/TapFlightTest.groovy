package org.pub.data.test.tap

import org.pub.pt.data.sources.tap.TapFlights
import org.pub.pt.data.sources.tap.domain.global.TapGlobalInfo
import spock.lang.Specification

/**
 * Created by balhau on 10/25/15.
 */
class TapFlightTest extends Specification {
    private TapFlights tapFlights

    def setup() {
        tapFlights = new TapFlights()
    }

    def "Get tap global info"() {
        when:
        "When we ask for global tap info"
        TapGlobalInfo globalInfo = tapFlights.getGlobalInfo()
        then:
        "We retrieve a org.pub.pt.data.sources.tap.domain.global.TapGlobalInfo object"
        globalInfo != null &&
                globalInfo.getStates().size() > 0 &&
                globalInfo.getAirports().size() > 0 &&
        globalInfo.getMarketList().size() > 0
    }
}
