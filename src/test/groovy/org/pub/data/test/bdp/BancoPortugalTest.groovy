package org.pub.data.test.bdp

import org.pub.pt.data.sources.bdp.BancoPortugal
import org.pub.pt.data.sources.bdp.domain.*
import spock.lang.Specification

/**
 * Created by balhau
 */
class BancoPortugalTest extends Specification {
    private BancoPortugal bdp;

    def setup() {
        bdp = new BancoPortugal()
    }

    def "Get domains"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        then:
        bdpDomainList.size() > 0
    }

    def "Get Hierarchies for domain"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        List<BdpHierarchy> bdpHierarchyList = bdp.getDomainHierarchies(bdpDomainList.get(0))
        then:
        !bdpHierarchyList.isEmpty() && bdpHierarchyList.get(0).dimension_id == (bdpDomainList.get(0).id)
    }

    def "Get hierarchy members"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        List<BdpHierarchy> bdpHierarchyList = bdp.getDomainHierarchies(bdpDomainList.get(0))
        List<BdpHierarchyMember> bdpHierarchyMemberList = bdp.getHierarchyMembers(bdpHierarchyList.get(0))
        then:
        !bdpHierarchyMemberList.isEmpty() &&
                bdpHierarchyMemberList.get(0).dimension_id == bdpDomainList.get(0).id &&
                bdpHierarchyMemberList.get(0).hierarchy_id == (bdpHierarchyList.get(0).id)
    }

    def "Get series for a list of members"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        List<BdpHierarchy> bdpHierarchyList = bdp.getDomainHierarchies(bdpDomainList.get(0))
        List<BdpHierarchyMember> bdpHierarchyMemberList = bdp.getHierarchyMembers(bdpHierarchyList.get(0))

        List<BdpHierarchyMember> twoMembers = List.of(
                (BdpHierarchyMember) bdpHierarchyMemberList.get(0),
                (BdpHierarchyMember) bdpHierarchyMemberList.get(1)
        )

        BdpSeriesInfoResponse serieInfoResponse = bdp.getSeriesForMembers(twoMembers)
        then:
        serieInfoResponse != null && serieInfoResponse.getData().size() > 0
    }

    def "Get metadata for series"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        List<BdpHierarchy> bdpHierarchyList = bdp.getDomainHierarchies(bdpDomainList.get(0))
        List<BdpHierarchyMember> bdpHierarchyMemberList = bdp.getHierarchyMembers(bdpHierarchyList.get(0))

        List<BdpHierarchyMember> twoMembers = List.of(
                (BdpHierarchyMember) bdpHierarchyMemberList.get(0),
                (BdpHierarchyMember) bdpHierarchyMemberList.get(1)
        )

        BdpSeriesInfoResponse seriesInfoResponse = bdp.getSeriesForMembers(twoMembers)
        BdpSeriesMetadataResponse seriesMetadataResponse = bdp.getSeriesMetadata(seriesInfoResponse.data.get(0));

        then:
        seriesMetadataResponse != null &&
                seriesMetadataResponse.data != null &&
                seriesMetadataResponse.data.series_id == seriesInfoResponse.data.get(0).id
    }


    def "Get datapoints for series"() {
        when:
        List<BdpDomain> bdpDomainList = bdp.getDomains()
        List<BdpHierarchy> bdpHierarchyList = bdp.getDomainHierarchies(bdpDomainList.get(0))
        List<BdpHierarchyMember> bdpHierarchyMemberList = bdp.getHierarchyMembers(bdpHierarchyList.get(0))

        List<BdpHierarchyMember> twoMembers = List.of(
                (BdpHierarchyMember) bdpHierarchyMemberList.get(0),
                (BdpHierarchyMember) bdpHierarchyMemberList.get(1)
        )

        BdpSeriesInfoResponse seriesInfoResponse = bdp.getSeriesForMembers(twoMembers)
        BdpSeriesMetadataResponse seriesMetadataResponse = bdp.getSeriesMetadata(seriesInfoResponse.data.get(0));
        BdpSeriesDataResponse seriesDataResponse = bdp.getSeriesData(seriesMetadataResponse.data)
        BdpSeriesDataResponse seriesDataResponseWithInterval = bdp.getSeriesData(seriesMetadataResponse.data, "2019-12-01", "2020-01-01")

        then:
        seriesDataResponse != null &&
                seriesDataResponseWithInterval != null &&
                seriesDataResponse.data.size() > 0 &&
                seriesDataResponseWithInterval.data.size() > 0 &&
                seriesDataResponse.data.get(0).series_id == seriesMetadataResponse.data.series_id &&
                seriesDataResponseWithInterval.data.get(0).series_id == seriesMetadataResponse.data.series_id

    }

}