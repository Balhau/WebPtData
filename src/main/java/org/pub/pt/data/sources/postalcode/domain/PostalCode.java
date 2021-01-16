package org.pub.pt.data.sources.postalcode.domain;

public class PostalCode {
    private String gps;
    private String description;
    private int major;
    private int minor;

    public PostalCode(String gps, String description, int major, int minor) {
        this.gps = gps;
        this.description = description;
        this.major = major;
        this.minor = minor;
    }

    public String getGps() {
        return gps;
    }

    public String getDescription() {
        return description;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }
}
