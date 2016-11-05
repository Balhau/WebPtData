package org.pub.pt.data.sources.rodonorte.domain;

/**
 *
 */
public class Destination {
    private final double code;
    private final String name;

    public Destination(double code,String name){
        this.code=code;this.name=name;
    }

    public double getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
