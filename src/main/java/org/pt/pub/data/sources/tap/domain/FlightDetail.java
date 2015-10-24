package org.pt.pub.data.sources.tap.domain;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public class FlightDetail {
    private final String flight;
    private final String operator;
    private final String departure;
    private final String arrival;
    private final String discount;
    private final String basic;
    private final String classic;
    private final String plus;
    private final String executive;

    private FlightDetail(
            String flight,
            String operator,
            String departure,
            String arrival,
            String discount,
            String basic,
            String classic,
            String plus,
            String executive
    ){
        this.flight=flight;this.operator=operator;
        this.departure=departure;this.basic=basic;
        this.arrival=arrival;this.discount=discount;
        this.classic=classic;this.plus=plus;
        this.executive=executive;
    }

}
