package org.pub.pt.data.sources.rodonorte.domain;

/**
 * This represents a bus trip in a {@link RodonorteCalendar}.
 */
public class BusEntry {
    private final String epoch;
    private final String daytype;
    private final String hour;

    public BusEntry(String epoch, String daytype, String hour) {
        this.epoch = epoch;
        this.daytype = daytype;
        this.hour = hour;
    }

    public String getEpoch() {
        return epoch;
    }
}
