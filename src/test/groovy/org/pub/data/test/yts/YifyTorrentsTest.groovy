package org.pub.data.test.yts

import org.pub.data.sources.yts.YifyTorrents
import org.pub.data.sources.yts.domain.TorrentLink
import org.pub.data.sources.yts.domain.YifyTorrent
import spock.lang.Specification

/**
 * Created by balhau on 9/4/16.
 */
class YifyTorrentsTest extends Specification {
    private YifyTorrents yts

    def setup() {
        yts = new YifyTorrents()
    }

    def "Get torrent urls from first page"() {
        when:
        List<TorrentLink> urls = yts.getTorrentURLs(1)
        then:
        urls.size() != 0
    }

    def "Get torrent information"() {
        when:
        //Torrent is loaded
        List<TorrentLink> torrentLinks = yts.getTorrentURLs(1)
        YifyTorrent yifyTorrent = yts.getTorrent(torrentLinks.get(0))
        then:
        //The torrent should have information
        yifyTorrent != null
        assert yifyTorrent.getRottenTomatoesAudience != null
    }

    def "Get torrents from page"() {
        when:
        //Torrents is loaded
        List<YifyTorrent> torrents = yts.getTorrentsFromPage(1)
        then:
        torrents.size() != 0
    }
}
