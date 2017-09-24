package groovy.org.pub.data.test.streaming.ace

import org.pub.data.sources.streaming.ace.AceStream
import org.pub.data.sources.streaming.ace.domain.AceFeed
import org.pub.data.sources.streaming.ace.domain.AceSportsFeed
import spock.lang.Specification

class AceStreamTest extends Specification {
        private AceStream aceStream

        def setup() {
            aceStream=new AceStream()
        }

        def "Get Ace Stop acestream feeds"() {
            when:
                List<AceFeed> feeds = aceStream.getAceStopFeeds()
            then:
                feeds != null
                feeds.size()>0
        }

    def "Get acestream feeds from acesport"(){
        when:
            List<AceSportsFeed> feeds = aceStream.getAceSportsFeeds()
        then:
            feeds != null
            feeds.size()>0
    }
}
