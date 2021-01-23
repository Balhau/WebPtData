package org.pub.pt.data.sources.rodonorte.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RodonorteCalendar {
    private final Map<String, List<BusEntry>> calendarMap;

    public RodonorteCalendar(Map<String, List<BusEntry>> calendarMap) {
        this.calendarMap = calendarMap;
    }

    public Map<String, List<BusEntry>> getCalendarMap() {
        return calendarMap;
    }

    public void putEntryOnMap(String local,BusEntry busEntry){
        this.calendarMap.putIfAbsent(local,new ArrayList<>()).add(busEntry);
    }
}
