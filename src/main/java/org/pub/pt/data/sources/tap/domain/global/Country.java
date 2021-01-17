package org.pub.pt.data.sources.tap.domain.global;

public class Country {
    private final String IsoCode;
    private final String Description;
    private final boolean HasState;

    public Country(String isoCode, String description, boolean hasState) {
        this.IsoCode = isoCode;
        this.Description = description;
        this.HasState = hasState;
    }

    public String getIsoCode() {
        return IsoCode;
    }

    public String getDescription() {
        return Description;
    }

    public boolean isHasState() {
        return HasState;
    }
}
