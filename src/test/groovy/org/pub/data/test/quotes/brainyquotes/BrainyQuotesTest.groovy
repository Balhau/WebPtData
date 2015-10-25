package org.pub.data.test.quotes.brainyquotes

import org.pt.pub.data.sources.domain.Quote
import org.pt.pub.data.sources.quotes.brainyquote.BrainyQuote
import org.pt.pub.data.sources.quotes.brainyquote.domain.Topic
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class BrainyQuotesTest extends Specification{

    private BrainyQuote brainyQuote

    def setup(){
        brainyQuote=new BrainyQuote()
    }

    def "Get brainyquote topics"(){
        when:
            List<Topic> topics = brainyQuote.getTopics()
        then:
            topics!=null && topics.size()>0
    }

    def "Get brainyquote quotes from topic"(){
        when:
            List<Topic> topics = brainyQuote.getTopics()
            List<Quote> quotes=brainyQuote.getQuotes(topics.get(0).name,1)
        then:
            quotes!=null && quotes.size()>0
    }

    def "Get brainyquote quotes and throw exception when invalid page is provided"(){
        when:
            List<Topic> topics = brainyQuote.getTopics()
            List<Quote> quotes=brainyQuote.getQuotes(topics.get(0),-1)
        then:
            def e = thrown(Exception)
            e!=null

    }
}
