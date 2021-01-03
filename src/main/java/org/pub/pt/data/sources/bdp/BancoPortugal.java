package org.pub.pt.data.sources.bdp;

import com.balhau.utils.StringUtils;
import com.google.gson.Gson;
import org.pub.global.domain.TableData;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.bdp.domain.*;
import org.pub.pt.data.sources.domain.AbstractDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * This class exports data/statistics from the
 * <a href="https://www.bportugal.pt" target="_blank">Banco de Portugal</a>
 * institution.<br><br>
 * <p>
 * The statistical data that is scrapped and returned by this service resides in the
 * <a href="http://www.bportugal.pt/EstatisticasWeb/" target="_blank">Estatisticas Web</a> web endpoint,
 * a service that is available from the institution<br><br>
 *
 * <b>Tutorial:</b><br><br>
 * <pre>
 * </pre>
 *
 * @author balhau
 */
public class BancoPortugal extends AbstractDataSource {
    /**
     * Representing the host of the Banco de Portugal institution
     */
    public static final String BPSTAT_HOST = "https://bpstat.bportugal.pt";

    private static final int RECORDS_PER_PAGE = 100;
    public static final String SERIES_OBSERVATIONS_PATTERN = BPSTAT_HOST + "/api/observations/?series_ids=%s&start_date=%s&end_date=%s";
    public static final String DOMAINS_URL = BPSTAT_HOST + "/api/dimensions/?domain_ids=";
    public static final String HIERARCHIES_FOR_DOMAIN_PATTERN = BPSTAT_HOST + "/api/hierarchies/?dimension_id=%s&domain_ids=&dimension_ids=&member_ids=";
    public static final String HIERARCHY_MEMBERS_PATTERN = BPSTAT_HOST + "/api/hierarchies/%s/members/?domain_ids=&dimension_ids=&member_ids=";
    public static final String SERIES_INFO_PATTERN_PAGE = BPSTAT_HOST + "/api/series/?&dimension_ids=%s&member_ids=%s&page=1&page_size=" + RECORDS_PER_PAGE + "&";
    public static final String SERIES_METADATA_PATTERN_PAGE = BPSTAT_HOST + "/api/series/%s/observations_meta/";

    private static final String DATA_AVAILABLE_INITIAL_DATE = "";

    /**
     * Timeout used to override the default one since the service is extremely slow
     */
    public static final int HTTP_TIMEOUT = 10 * 1000;

    /**
     * Returns a {@link TableData} withe information regarding the categories we can query
     *
     * @return {@link TableData} categories information
     * @throws Exception in case error is found while parsing data
     */
    public List<BdpDomain> getDomains() {
        String jsonData = DomUtils.getRawString(DOMAINS_URL);
        BdpDomainResponse bdpDomainResponse = new Gson().fromJson(jsonData, BdpDomainResponse.class);
        return bdpDomainResponse.data;
    }

    public List<BdpHierarchy> getDomainHierarchies(final BdpDomain bdpDomain) {
        String jsonData = DomUtils.getRawString(String.format(HIERARCHIES_FOR_DOMAIN_PATTERN, bdpDomain.getId()));
        BdpHierarchiesResponse bdpHierarchiesResponse = new Gson().fromJson(jsonData, BdpHierarchiesResponse.class);
        return bdpHierarchiesResponse.data;
    }

    public List<BdpHierarchyMember> getHierarchyMembers(final BdpHierarchy bdpHierarchy) {
        String jsonData = DomUtils.getRawString(String.format(HIERARCHY_MEMBERS_PATTERN, bdpHierarchy.getId()));
        BdpHierarchyMembersResponse bdmHierarchyMembersResponse = new Gson().fromJson(jsonData, BdpHierarchyMembersResponse.class);
        return bdmHierarchyMembersResponse.data;
    }

    public BdpSeriesInfoResponse getSeriesForMembers(final List<BdpHierarchyMember> bdpMembers) {
        List<String> domainsList = bdpMembers.stream()
                .map(m -> m.getDimension_id() + "")
                .collect(Collectors.toList());

        List<String> membersList = bdpMembers.stream()
                .map(m -> m.getId() + "")
                .collect(Collectors.toList());

        String domainIds = String.join(",", domainsList);
        String membersIds = String.join(",", membersList);

        String url = String.format(SERIES_INFO_PATTERN_PAGE, domainIds, membersIds);

        String jsonData = DomUtils.getRawString(url);
        BdpSeriesInfoResponse seriesInfoResponse = new Gson().fromJson(jsonData, BdpSeriesInfoResponse.class);
        return seriesInfoResponse;
    }

    public BdpSeriesMetadataResponse getSeriesMetadata(final BdpSeriesInfo seriesInfo) {
        String url = String.format(SERIES_METADATA_PATTERN_PAGE, seriesInfo.getId());
        String jsonData = DomUtils.getRawString(url);
        BdpSeriesMetadataResponse seriesMetadataresponse = new Gson().fromJson(jsonData, BdpSeriesMetadataResponse.class);
        return seriesMetadataresponse;
    }

    public BdpSeriesDataResponse getSeriesData(final BdpSeriesMetadata seriesMetadata) {
        return getSeriesData(seriesMetadata, null, null);
    }

    public BdpSeriesDataResponse getSeriesData(final BdpSeriesMetadata seriesMetadata, final String startDate, final String endDate) {
        String start = startDate == null ? seriesMetadata.getFirst_date() : startDate;
        String end = endDate == null ? seriesMetadata.getLast_date() : endDate;

        String url = String.format(SERIES_OBSERVATIONS_PATTERN,seriesMetadata.getSeries_id(),start,end);
        String jsonData = DomUtils.getRawString(url);
        BdpSeriesDataResponse seriesDataResponse = new Gson().fromJson(jsonData,BdpSeriesDataResponse.class);
        return seriesDataResponse;
    }


    /**
     * This is a utility method that transforms a {@link List} of seriesid and a {@link Date} parameter
     * into a {@link String} that represents the url that can be used to query the data we want be
     * matched with the parameters
     *
     * @param seriesIdList {@link List} of {@link String} with the of series
     * @param endDate      {@link Date} that represents the last data to be retrieved
     * @return {@link String} the web endpoint for the data
     */
    private String buildSeriesDataURL(List<String> seriesIdList, Date endDate) {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb, Locale.US);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strIdsList = StringUtils.join(seriesIdList, ",");
        String out = f.format("SERIES_CONTROLLER_DATA", dateFormat.format(endDate), strIdsList).toString();
        f.close();
        return out;
    }
}
