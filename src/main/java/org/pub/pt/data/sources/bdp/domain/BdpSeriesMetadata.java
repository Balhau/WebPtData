package org.pub.pt.data.sources.bdp.domain;

public class BdpSeriesMetadata {
    private final int series_id;
    private final String first_date;
    private final String first_value;
    private final String last_date;
    private final String last_value;
    private final String before_last_date;
    private final String before_last_value;
    private final String counterpart_date;
    private final String counterpart_value;
    private final String counterpart_1999_date;
    private final String counterpart_1999_value;

    public BdpSeriesMetadata(int series_id, String first_date, String first_value, String last_date,String last_value, String before_last_date, String before_last_value, String counterpart_date, String counterpart_value, String counterpart_1999_date, String counterpart_1999_value) {
        this.series_id = series_id;
        this.first_date = first_date;
        this.first_value = first_value;
        this.last_date=last_date;
        this.last_value = last_value;
        this.before_last_date = before_last_date;
        this.before_last_value = before_last_value;
        this.counterpart_date = counterpart_date;
        this.counterpart_value = counterpart_value;
        this.counterpart_1999_date = counterpart_1999_date;
        this.counterpart_1999_value = counterpart_1999_value;
    }

    public String getLast_date() {
        return last_date;
    }

    public int getSeries_id() {
        return series_id;
    }

    public String getFirst_date() {
        return first_date;
    }

    public String getFirst_value() {
        return first_value;
    }

    public String getLast_value() {
        return last_value;
    }

    public String getBefore_last_date() {
        return before_last_date;
    }

    public String getBefore_last_value() {
        return before_last_value;
    }

    public String getCounterpart_date() {
        return counterpart_date;
    }

    public String getCounterpart_value() {
        return counterpart_value;
    }

    public String getCounterpart_1999_date() {
        return counterpart_1999_date;
    }

    public String getCounterpart_1999_value() {
        return counterpart_1999_value;
    }
}
