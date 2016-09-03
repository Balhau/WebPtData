package org.pub.pt.data.sources.tap.domain;

/**
 * POJO that represents a segment in a flight. A flight from A to B can also go through C and D, A -> C -> D -> B
 * and we represent the flight A -> B as several segments (A ->C), (C->D) and (D->B), in this case three segments
 * represent the flight from A -> B. A flight detail will have a List of flight segments as a property
 * Created by vitorfernandes on 10/25/15.
 */
public class FlightSegment {
    private final String flight;
    private final String operator;
    private final String departure;
    private final String arrival;

    public FlightSegment(String flight,String operator,String departure,String arrival){
        this.flight=flight;this.operator=operator;
        this.departure=departure;this.arrival=arrival;
    }

    public String getFlight() {
        return flight;
    }

    public String getOperator() {
        return operator;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }
}
