package groovy.org.pub.data.test.ine

import org.pub.pt.data.sources.ine.Ine
import org.pub.pt.data.sources.ine.domain.INEResultData
import org.pub.pt.data.sources.ine.domain.INEServices
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class IneTest extends Specification{
    private Ine ine;

    def setup(){
        ine=new Ine();
    }

    def "Get INE available services"(){
        when:
            INEServices services=ine.getAvailableServices(1,20)
        then:
            services.list.size()>20
    }

    def "Get INE data from service"(){
        when:
            INEServices services=ine.getAvailableServices(1,20);
            INEResultData data=ine.getDataFromService(services.list.get(0).url)
        then:
            data.dataRows.size() > 0
    }
}
