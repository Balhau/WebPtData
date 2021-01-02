package org.pub.data.test.piratebay

import org.pub.data.sources.piratebay.PirateBay
import org.pub.data.sources.piratebay.domain.TorrentInfo
import spock.lang.Specification

/**
 * Tests for the Piratebay service
 * Created by balhau on 10/25/15.
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

    def "Get results from torrents search"() {
        when:
        List<TorrentInfo> torrents = pirateBay.searchTorrents("giro", 0, PirateBay.UNORDERED)
        then:
        torrents.size() > 0
    }
}