package org.pub.pt.data.sources.tap.domain.global;

public enum TapLanguage {
    PT_PT("pt-PT");

    private final String codeLanguage;

    TapLanguage(final String code) {
        this.codeLanguage = code;
    }

    public String getCodeLanguage() {
        return this.codeLanguage;
    }
}
