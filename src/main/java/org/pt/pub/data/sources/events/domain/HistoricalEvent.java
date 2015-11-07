package org.pt.pub.data.sources.events.domain;

/**
 * Domain object that represents the concept of historical event
 * Created by vitorfernandes on 11/7/15.
 */
public class HistoricalEvent {
    private final int day;
    private final int month;
    private final int year;
    private final String event;

    public HistoricalEvent(
            int day,int month, int year,String event
    ){
        this.day=day;this.month=month;
        this.year=year;this.event=event;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getEvent() {
        return event;
    }
}
