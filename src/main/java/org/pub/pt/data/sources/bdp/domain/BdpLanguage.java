package org.pub.pt.data.sources.bdp.domain;


/**
 * Abstraction for objects of internationalization
 */
public class BdpLanguage {
    private final String PT;
    private final String EN;

    public BdpLanguage(final String pt, final String en) {
        this.EN = en;
        this.PT = pt;
    }

    public String getPT() {
        return PT;
    }

    public String getEN() {
        return EN;
    }
}
