package org.pub.pt.data.sources.tap.domain.global;

public class State {
    private final String CountryIsoCode;
    private final String IsoCode;
    private final String Description;

    public State(String countryIsoCode, String isoCode, String description) {
        this.CountryIsoCode = countryIsoCode;
        this.IsoCode = isoCode;
        this.Description = description;
    }

    public String getCountryIsoCode() {
        return CountryIsoCode;
    }

    public String getIsoCode() {
        return IsoCode;
    }

    public String getDescription() {
        return Description;
    }
}
