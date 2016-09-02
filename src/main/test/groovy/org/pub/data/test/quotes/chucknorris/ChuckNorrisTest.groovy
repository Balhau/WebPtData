package groovy.org.pub.data.test.quotes.chucknorris

import org.pt.pub.data.sources.domain.Message
import org.pt.pub.data.sources.quotes.chucknorris.ChuckNorris
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class ChuckNorrisTest extends Specification{
    private ChuckNorris chuckNorris;

    def setup(){
        chuckNorris=new ChuckNorris();
    }

    def "Get chucknorris facts"(){
        when:
            List<Message> facts=chuckNorris.getFacts(0);
        then:
            facts.size()>0
    }

    def "Get quote from chuck"(){
        when:
            Message quote=chuckNorris.getMessage()
        then:
            quote != null && quote.source!=null && quote.message != null
    }
}
