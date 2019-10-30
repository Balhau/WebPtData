package org.pub.data.test.fedbizopps

import org.pub.data.sources.fedbizopps.FedbizOpps
import org.pub.data.sources.fedbizopps.domain.Opportunity
import org.pub.global.base.ScraperPool
import spock.lang.Specification

/**
 * Created by vitorfernandes on 12/24/16.
 */
class FedbizOppsTest extends Specification {
    private FedbizOpps fedbizOpps;

    def setup(){
        fedbizOpps = new FedbizOpps(ScraperPool.getPool());
    }

    def "Get page 1 of search results"(){
        int page = 1
        when:
            List<Opportunity> opportunities = fedbizOpps.getOpportunitiesPage(page);
        then:
            opportunities.size()!=0
    }
}
