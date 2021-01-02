package org.pub.data.sources.fedbizopps.domain;

/**
 * Opportunity domain object that will represent the search result entries
 */
public class Opportunity {

    public static class OpportunityBuilder {

        private String id;
        private String description;
        private String agency;
        private String type;
        private String date;
        private OpportunityDetails details;

        public OpportunityBuilder() {
        }

        public OpportunityBuilder Id(String id) {
            this.id = id;
            return this;
        }

        public OpportunityBuilder Description(String description) {
            this.description = description;
            return this;
        }

        public OpportunityBuilder Agency(String agency) {
            this.agency = agency;
            return this;
        }

        public OpportunityBuilder Type(String type) {
            this.type = type;
            return this;
        }

        public OpportunityBuilder Date(String date) {
            this.date = date;
            return this;
        }

        public OpportunityBuilder Details(OpportunityDetails details) {
            this.details = details;
            return this;
        }

        public Opportunity build() {
            return new Opportunity(
                    id, description, agency, type, date, details
            );
        }
    }

    private final String id;
    private final String description;
    private final String agency;
    private final String type;
    private final String date;
    private final OpportunityDetails details;

    public Opportunity(String id, String description, String agency, String type, String date, OpportunityDetails details) {
        this.id = id;
        this.description = description;
        this.agency = agency;
        this.type = type;
        this.date = date;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAgency() {
        return agency;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
