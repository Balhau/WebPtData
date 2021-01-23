package org.pub.pt.data.sources.rodonorte.domain;

public class CalendarEntry {
    private final String description;
    private final int id;

    public CalendarEntry(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
