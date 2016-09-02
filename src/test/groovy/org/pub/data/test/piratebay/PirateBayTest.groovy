package org.pub.data.test.piratebay

import org.pt.pub.data.sources.quotes.brainyquote.domain.Topic
import org.pub.data.sources.piratebay.PirateBay
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class PirateBayTest extends Specification {

    private PirateBay pirateBay

    def setup() {
        pirateBay = new PirateBay()
    }

    def "Check piratebay proxy list"() {
        when:
            "When the app starts"
        then:
            PirateBay.PIRATEBAY_PROXY_LIST.size() > 0
    }
}