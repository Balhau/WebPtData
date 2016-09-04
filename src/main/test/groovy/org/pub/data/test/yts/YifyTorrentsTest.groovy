package groovy.org.pub.data.test.yts

import org.pub.data.sources.yts.YifyTorrents
import org.pub.data.sources.yts.domain.TorrentLink
import spock.lang.Specification

/**
 * Created by vitorfernandes on 9/4/16.
 */
class YifyTorrentsTest extends Specification{
    private YifyTorrents yts

    def setup() {
        yts = new YifyTorrents()
    }

    def "Get torrent urls from first page"(){
        when:
            List<TorrentLink> urls=yts.getTorrentURLs(1);
        then:
            urls.size()!=0
    }
}
