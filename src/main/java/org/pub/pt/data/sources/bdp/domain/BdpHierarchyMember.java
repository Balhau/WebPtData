package org.pub.pt.data.sources.bdp.domain;

/**
 * Banco de Portugal Hierarchy representation
 */
public class BdpHierarchyMember {
    private final int id;
    private final int parent_id;
    private final int hierarchy_id;
    private final int version_id;
    private final int dimension_id;
    private final String pub_date;
    private final BdpLanguage title;
    private final BdpLanguage title_alt;
    private final String code;
    private final BdpLanguage description;
    private final String notes;
    private final int order_by;
    private final String tags;
    private final boolean hasobs;
    private final boolean high_number_of_series;
    private final String geocodes;
    private final String geotype;

    public BdpHierarchyMember(int id, int parent_id, int hierarchy_id, int version_id, int dimension_id, String pub_date, BdpLanguage title, BdpLanguage title_alt, String code, BdpLanguage description, String notes, int order_by, String tags, boolean hasobs, boolean high_number_of_series, String geocodes, String geotype) {
        this.id = id;
        this.parent_id = parent_id;
        this.hierarchy_id = hierarchy_id;
        this.version_id = version_id;
        this.dimension_id = dimension_id;
        this.pub_date = pub_date;
        this.title = title;
        this.title_alt = title_alt;
        this.code = code;
        this.description = description;
        this.notes = notes;
        this.order_by = order_by;
        this.tags = tags;
        this.hasobs = hasobs;
        this.high_number_of_series = high_number_of_series;
        this.geocodes = geocodes;
        this.geotype = geotype;
    }

    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public int getHierarchy_id() {
        return hierarchy_id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public int getDimension_id() {
        return dimension_id;
    }

    public String getPub_date() {
        return pub_date;
    }

    public BdpLanguage getTitle() {
        return title;
    }

    public BdpLanguage getTitle_alt() {
        return title_alt;
    }

    public String getCode() {
        return code;
    }

    public BdpLanguage getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public int getOrder_by() {
        return order_by;
    }

    public String getTags() {
        return tags;
    }

    public boolean isHasobs() {
        return hasobs;
    }

    public boolean isHigh_number_of_series() {
        return high_number_of_series;
    }

    public String getGeocodes() {
        return geocodes;
    }

    public String getGeotype() {
        return geotype;
    }
}
