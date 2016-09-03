package org.pub.pt.data.sources.tap.domain;

/**
 * Created by vitorfernandes on 10/25/15.
 */
public class Destination {
    private final String code;
    private final String description;

    public Destination(String code,String description){
        this.code=code;this.description=description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
