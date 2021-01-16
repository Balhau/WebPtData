package org.pub.pt.data.sources.tap.domain;

public enum TapCountry {
    PT("PT");

    private final String countryCode;

    TapCountry(final String code) {
        this.countryCode = code;
    }

    public String getCountryCode() {
        return this.countryCode;
    }
}
