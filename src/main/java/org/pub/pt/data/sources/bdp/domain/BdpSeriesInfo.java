package org.pub.pt.data.sources.bdp.domain;

public class BdpSeriesInfo {
    private final int id;
    private final int version_id;
    private final int[] domain_ids;
    private final String created_at;
    private final String last_observation_reference_date;
    private final String last_observation_value;
    //private final Object membercombination;
    private final int integration_id;
    private final int last_integration_id;
    private final boolean is_synced;
    private final String op_code;
    private final String pub_date;
    private final String end_pub_date;
    private final String modified_at;
    private final String privacy_code;
    //private final Object membercombination_ids;


    public BdpSeriesInfo(int id, int version_id, int[] domain_ids, String created_at, String last_observation_reference_date, String last_observation_value, int integration_id, int last_integration_id, boolean is_synced, String op_code, String pub_date, String end_pub_date, String modified_at, String privacy_code) {
        this.id = id;
        this.version_id = version_id;
        this.domain_ids = domain_ids;
        this.created_at = created_at;
        this.last_observation_reference_date = last_observation_reference_date;
        this.last_observation_value = last_observation_value;
        this.integration_id = integration_id;
        this.last_integration_id = last_integration_id;
        this.is_synced = is_synced;
        this.op_code = op_code;
        this.pub_date = pub_date;
        this.end_pub_date = end_pub_date;
        this.modified_at = modified_at;
        this.privacy_code = privacy_code;
    }

    public int getId() {
        return id;
    }

    public int getVersion_id() {
        return version_id;
    }

    public int[] getDomain_ids() {
        return domain_ids;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getLast_observation_reference_date() {
        return last_observation_reference_date;
    }

    public String getLast_observation_value() {
        return last_observation_value;
    }

    public int getIntegration_id() {
        return integration_id;
    }

    public int getLast_integration_id() {
        return last_integration_id;
    }

    public boolean isIs_synced() {
        return is_synced;
    }

    public String getOp_code() {
        return op_code;
    }

    public String getPub_date() {
        return pub_date;
    }

    public String getEnd_pub_date() {
        return end_pub_date;
    }

    public String getModified_at() {
        return modified_at;
    }

    public String getPrivacy_code() {
        return privacy_code;
    }
}
