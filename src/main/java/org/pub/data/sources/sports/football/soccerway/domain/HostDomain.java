package org.pub.data.sources.sports.football.soccerway.domain;


/**
 * Enumeration with all supported Soccerway subdomains
 */
public enum HostDomain {

    PORTUGAL("pt"),
    BRASIL("br"),
    CHINESE("cn"),
    AUSTRALIA("au"),
    CANADA("ca"),
    GHANA("gh"),
    INTERNATIONAL_ENGLISH("int"),
    IRELAND("ie"),
    KENYA("ke"),
    MALAYSIA("my"),
    NIGERIA("ng"),
    MORDICS("nr"),
    SINGAPORE("sg"),
    SOUTH_AFRICA("za"),
    UNITED_KINGDOM("uk"),
    UNITED_STATES("us"),
    SPAIN("es"),
    SPAIN_LATAM("el"),
    FRENCH("fr"),
    GREEK("gr"),
    ITALY("it"),
    KOREAN("kr"),
    NETHERLANDS("nl"),
    POLAND("pl"),
    ROMANIA("ro"),
    THAI("th"),
    TURKY("tr"),
    RUSSIA("ru"),
    ARABE("ar")
    

    ;

    private static final String HOST_PATTERN = "https://%s.soccerway.com";
    private final String code;

    private HostDomain(final String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getHost() {
        return String.format(HOST_PATTERN, this.code);
    }
}
