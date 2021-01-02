package org.pub.data.test.basegov

import org.pub.pt.data.sources.base.BaseGov
import org.pub.pt.data.sources.base.domain.BaseQueryResponse
import org.pub.pt.data.sources.domain.Message
import org.pub.global.domain.TableData
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/24/15.
 */
class BaseGovTest extends Specification{
    private BaseGov baseGov

    def setup(){
        baseGov=new BaseGov()
    }

    def "Get all paged results"(){
        when:
            BaseQueryResponse response = baseGov.getAllResults(0,10)
        then:
            response.getItems().size() > 0
    }

    def "Get results by adjudicante"(){
        when:
            BaseQueryResponse baseQuery = baseGov.getAllResults(0,5);
            BaseQueryResponse byAdjudicante = baseGov.getByAdjudicante(0,5,baseQuery.getItems().get(0).adjudicante)
        then:
            byAdjudicante.items.size() > 0
    }

    def "Get results by adjudicatario"(){
        when:
            BaseQueryResponse baseQuery = baseGov.getAllResults(0,5);
            BaseQueryResponse byAdjudicatario = baseGov.getByAjudicatario(0,5,baseQuery.getItems().get(0).adjudicatario)
        then:
            byAdjudicatario.items.size() > 0
    }

    def "Get contract information"(){
        when:
            BaseQueryResponse baseQuery = baseGov.getAllResults(0,5);
            List<TableData> contractInfo=baseGov.getEntryInformationByContractoId(baseQuery.items.get(0).id)
        then:
            contractInfo.size() > 0
    }

    def "Get message service text"(){
        when:
            Message message=baseGov.getMessage();
        then:
            message!=null
            message.message!=null && message.source!=null
    }
}
