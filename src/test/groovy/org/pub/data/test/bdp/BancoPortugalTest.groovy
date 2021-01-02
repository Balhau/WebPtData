package org.pub.data.test.bdp

import org.pub.global.domain.TableData
import org.pub.pt.data.sources.bdp.BancoPortugal
import spock.lang.Specification

/**
 * Created by balhau on 10/24/15.
 */
class BancoPortugalTest extends Specification {
    private BancoPortugal bdp;

    def setup() {
        bdp = new BancoPortugal()
    }

    def "Get categorias"() {
        when:
        TableData data = bdp.getCategories()
        then:
        data.rows.size() > 0 && data.rows.get(0).data.size() > 0
    }


}
