package org.pub.pt.data.sources.bdp.domain;

public class BdpHierarchy {
    private final int id;
    private final int version_id;
    private final int dimension_id;
    private final String pub_date;
    private final BdpLanguage title;
    private final BdpLanguage title_alt;
    private final BdpLanguage description;
    private final String notes;
    private final int order_by;
    private final String tags;

    public BdpHierarchy(int id, int version_id, int dimension_id, String pub_date, BdpLanguage title, BdpLanguage title_alt, BdpLanguage description, String notes, int order_by, String tags) {
        this.id = id;
        this.version_id = version_id;
        this.dimension_id = dimension_id;
        this.pub_date = pub_date;
        this.title = title;
        this.title_alt = title_alt;
        this.description = description;
        this.notes = notes;
        this.order_by = order_by;
        this.tags = tags;
    }

    public int getId() {
        return id;
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
}
