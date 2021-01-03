package org.pub.pt.data.sources.bdp.domain;

/**
 * Banco de Portugal Domain representation
 */
public class BdpDomain {
    private final int id;
    private final int parent_id;
    private final int version_id;
    private final int idbp;
    private final String pub_date;
    private final BdpLanguage title;
    private final BdpLanguage description;
    private final String notes;
    private final int order_by;
    private final String tags;
    private final String is_territory;
    private final String is_source;

    public BdpDomain(
            int id, int parent_id, int version_id, int idbp, String pub_date,
            BdpLanguage title, BdpLanguage description, String notes,
            int order_by, String tags, String is_territory, String is_source
    ) {
        this.id = id;
        this.parent_id = parent_id;
        this.version_id = version_id;
        this.idbp = idbp;
        this.pub_date = pub_date;
        this.title = title;
        this.description = description;
        this.notes = notes;
        this.order_by = order_by;
        this.tags = tags;
        this.is_territory = is_territory;
        this.is_source = is_source;
    }

    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public int getIdbp() {
        return idbp;
    }

    public String getPub_date() {
        return pub_date;
    }

    public BdpLanguage getTitle() {
        return title;
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

    public String getIs_territory() {
        return is_territory;
    }

    public String getIs_source() {
        return is_source;
    }
}
