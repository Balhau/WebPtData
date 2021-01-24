package org.pub.data.test.ipma

import org.pub.global.domain.TableData
import org.pub.pt.data.sources.ipma.Ipma
import org.pub.pt.data.sources.ipma.domain.BeachEntry
import org.pub.pt.data.sources.ipma.domain.api.District
import org.pub.pt.data.sources.ipma.domain.api.ForecastInfo
import org.pub.pt.data.sources.ipma.domain.api.ForecastLocation
import org.pub.pt.data.sources.ipma.domain.api.LocaleLabel
import org.pub.pt.data.sources.ipma.domain.api.Warnings
import spock.lang.Specification

/**
 * Created by balhau on 10/25/15.
 */
class IpmaTest extends Specification {
    private Ipma ipma;

    def setup() {
        ipma = new Ipma()
    }

    def "Get IPMA beach list"() {
        when:
        List<BeachEntry> tableData = ipma.getBeachEntries();
        then:
        tableData.size() > 0
    }

    def "Get IPMA beach info"() {
        when:
        List<BeachEntry> beachList = ipma.getBeachEntries();
        List<TableData> beachInfo = ipma.getBeachInfo(beachList.get(0).idBeach)
        then:
        beachInfo.size() > 0
    }

    def "Get IPMA weather types"(){
        when:
        Map<String, LocaleLabel> weatherTypes = ipma.getWeatherTypes()
        then:
        !weatherTypes.isEmpty() && weatherTypes.size()>0
    }

    def "Get IPMA wind types"(){
        when:
        Map<String, LocaleLabel> windTypes = ipma.getWindTypes()
        then:
        !windTypes.isEmpty() && windTypes.size()>0
    }

    def "Get IPMA rain types"(){
        when:
        Map<String, LocaleLabel> rainTypes = ipma.getRainTypes()
        then:
        !rainTypes.isEmpty() && rainTypes.size()>0
    }

    def "Get IPMA districts"(){
        when:
        List<District> districtsList = ipma.getDistricts()
        then:
        !districtsList.isEmpty() && districtsList.size()>0
    }

    def "Get IPMA warnings"(){
        when:
        Warnings warnings = ipma.getWarnings()
        then:
        warnings!=null && warnings.data!=null && warnings.data.size()>0
    }

    def "Get IPMA forecast locations"(){
        when:
        List< ForecastLocation> forecastLocations = ipma.getForecastLocations()
        then:
        forecastLocations!=null && forecastLocations.size()>0
    }

    def "Get forecast for location"(){
        when:
        List<ForecastLocation> forecastLocations = ipma.getForecastLocations()
        List<ForecastInfo> forecastInfoList = ipma.getForecastForLocation(forecastLocations.get(0))
        then:
        forecastLocations!=null && forecastLocations.size()>0 &&
                forecastInfoList!=null && forecastInfoList.size()>0

    }

}
