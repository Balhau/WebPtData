package org.pt.pub.data.sources.tap.domain;

import java.util.List;

/**
 * Created by vitorfernandes on 10/24/15.
 */
public class FlightDetail {
    private final List<FlightSegment> segments;
    private final String discount;
    private final String basic;
    private final String classic;
    private final String plus;
    private final String executive;

    public FlightDetail(
            List<FlightSegment> segments,
            String discount,
            String basic,
            String classic,
            String plus,
            String executive
    ){
        this.segments=segments;this.basic=basic;this.discount=discount;
        this.classic=classic;this.plus=plus;
        this.executive=executive;
    }

    public List<FlightSegment> getSegments() {
        return segments;
    }

    public String getDiscount() {
        return discount;
    }

    public String getBasic() {
        return basic;
    }

    public String getClassic() {
        return classic;
    }

    public String getPlus() {
        return plus;
    }

    public String getExecutive() {
        return executive;
    }
}
