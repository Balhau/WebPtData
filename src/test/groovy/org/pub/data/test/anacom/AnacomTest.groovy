package org.pub.data.test.anacom

import org.pub.pt.data.sources.anacom.Anacom
import org.pub.pt.data.sources.anacom.domain.TarifaFixo
import org.pub.pt.data.sources.anacom.domain.TarifaInternet
import org.pub.pt.data.sources.anacom.domain.TarifaMovel
import org.pub.pt.data.sources.anacom.domain.TarifaTelevisao
import spock.lang.Specification

/**
 * Tests to the anacom services
 * Created by balhau on 10/24/15.
 */
class AnacomTest extends Specification {
    private Anacom anacom

    def setup() {
        anacom = new Anacom()
    }

    def "Retrieve the internet services"() {
        when:
        List<TarifaInternet> internet = anacom.getTarifariosInternet()
        then:
        internet.size() > 0
    }

    def "Retrieve the mobile services"() {
        when:
        List<TarifaMovel> movel = anacom.getTarifariosMovel()
        then:
        movel.size() > 0
    }

    def "Retrieve the not so mobile services"() {
        when:
        List<TarifaFixo> fixo = anacom.getTarifariosFixo()
        then:
        fixo.size() > 0
    }

    def "Retrive television services"() {
        when:
        List<TarifaTelevisao> televisao = anacom.getTarifariosTelevisao()
        then:
        televisao.size() > 0
    }

}
