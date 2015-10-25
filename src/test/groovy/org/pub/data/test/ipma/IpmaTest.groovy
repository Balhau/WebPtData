package org.pub.data.test.ipma

import org.pt.pub.data.sources.ipma.Ipma
import org.pt.pub.data.sources.ipma.domain.BeachEntry
import org.pt.pub.data.sources.ipma.domain.GeoWeather
import org.pt.pub.global.domain.TableData
import spock.lang.Specification

/**
 * Created by vitorfernandes on 10/25/15.
 */
class IpmaTest extends Specification{
    private Ipma ipma;

    def setup(){
        ipma=new Ipma()
    }

    def "Get IPMA beach list"(){
        when:
            List<BeachEntry> tableData=ipma.getBeachEntries();
        then:
            tableData.size()>0
    }

    def "Get IPMA beach info"(){
        when:
            List<BeachEntry> beachList=ipma.getBeachEntries();
            List<TableData> beachInfo = ipma.getBeachInfo(beachList.get(0).idBeach)
        then:
            beachInfo.size() > 0
    }

    def "Get land, uv and sea weather for today"(){
        when:
            List<GeoWeather> weather =ipma.getForecastDay(1)
        then:
            weather.size()==3
    }

    def "Get seismic activity for today"(){
        when:
            List<TableData> seismicData=ipma.getSeismicActivity(new Date())
        then:
            seismicData.size() > 0
    }
}
