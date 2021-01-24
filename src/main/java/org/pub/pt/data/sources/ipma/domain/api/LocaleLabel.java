package org.pub.pt.data.sources.ipma.domain.api;

public class LocaleLabel {
    private final String PT;
    private final String EN;

    public LocaleLabel(String PT, String EN) {
        this.PT = PT;
        this.EN = EN;
    }

    public String getPT() {
        return PT;
    }

    public String getEN() {
        return EN;
    }
}
