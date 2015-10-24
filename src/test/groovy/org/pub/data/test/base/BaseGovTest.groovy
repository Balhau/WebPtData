package org.pub.data.test.base

import org.pt.pub.data.sources.base.Base
import org.pt.pub.data.sources.base.domain.BaseQueryResponse
import org.pt.pub.global.domain.TableData
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/24/15.
 */
class BaseGovTest extends Specification{
    private Base base

    def setup(){
        base=new Base()
    }

    def "Get all paged results"(){
        when:
            BaseQueryResponse response = base.getAllResults(0,10)
        then:
            response.getItems().size() == 12
    }

    def "Get results by adjudicante"(){
        when:
            BaseQueryResponse baseQuery= base.getAllResults(0,5);
            BaseQueryResponse byAdjudicante=base.getByAdjudicante(0,5,baseQuery.getItems().get(0).adjudicante)
        then:
            byAdjudicante.items.size() > 0
    }

    def "Get results by adjudicatario"(){
        when:
            BaseQueryResponse baseQuery = base.getAllResults(0,5);
            BaseQueryResponse byAdjudicatario = base.getByAjudicatario(0,5,baseQuery.getItems().get(0).adjudicatario)
        then:
            byAdjudicatario.items.size() > 0
    }

    def "Get contract information"(){
        when:
            BaseQueryResponse baseQuery = base.getAllResults(0,5);
            List<TableData> contractInfo=base.getEntryInformationByContractoId(baseQuery.items.get(0).id)
        then:
            contractInfo.size() > 0
    }
}
