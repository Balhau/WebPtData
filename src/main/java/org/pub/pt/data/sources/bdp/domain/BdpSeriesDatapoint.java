package org.pub.pt.data.sources.bdp.domain;

public class BdpSeriesDatapoint {
    private final int id;
    private final int version_id;
    private final int series_id;
    private final String privacy_code;
    private final String value;
    private final String reference_date;
    private final boolean is_highlighted;
    private final String pub_date;
    private BdpLanguage description;

    public BdpSeriesDatapoint(int id, int version_id, int series_id, String privacy_code, String value, String reference_date, boolean is_highlighted, String pub_date) {
        this.id = id;
        this.version_id = version_id;
        this.series_id = series_id;
        this.privacy_code = privacy_code;
        this.value = value;
        this.reference_date = reference_date;
        this.is_highlighted = is_highlighted;
        this.pub_date = pub_date;
    }

    public int getId() {
        return id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public int getSeries_id() {
        return series_id;
    }

    public String getPrivacy_code() {
        return privacy_code;
    }

    public String getValue() {
        return value;
    }

    public String getReference_date() {
        return reference_date;
    }

    public boolean isIs_highlighted() {
        return is_highlighted;
    }

    public String getPub_date() {
        return pub_date;
    }
}
