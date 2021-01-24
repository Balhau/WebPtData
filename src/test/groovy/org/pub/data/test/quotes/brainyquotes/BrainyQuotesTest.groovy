package org.pub.data.test.quotes.brainyquotes

import org.pub.pt.data.sources.domain.Message
import org.pub.pt.data.sources.quotes.brainyquote.BrainyMessage
import org.pub.pt.data.sources.quotes.brainyquote.domain.Author
import org.pub.pt.data.sources.quotes.brainyquote.domain.Topic
import spock.lang.Specification

/**
 * Created by balhau on 10/25/15.
 */
class BrainyQuotesTest extends Specification {

    private BrainyMessage brainyQuote

    def setup() {
        brainyQuote = new BrainyMessage()
    }

    def "Get brainyquote topics"() {
        when:
        List<Topic> topics = brainyQuote.getTopics()
        then:
        topics != null && topics.size() > 0
    }

    def "Get brainyquote quotes from topic"() {
        when:
        List<Topic> topics = brainyQuote.getTopics()
        List<Message> quotes = brainyQuote.getQuotes(topics.get(0), 1)
        then:
        quotes != null && quotes.size() > 0
    }

    def "Get brainyquote quotes and throw exception when invalid page is provided"() {
        when:
        List<Topic> topics = brainyQuote.getTopics()
        List<Message> quotes = brainyQuote.getQuotes(topics.get(0), -1)
        then:
        def e = thrown(Exception)
        e != null
    }

    def "Get brainyquote author list"() {
        when:
        List<Author> authors = brainyQuote.getAuthors()
        then:
        authors != null && authors.size() > 0
    }
}
