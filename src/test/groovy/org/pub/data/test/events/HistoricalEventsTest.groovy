package org.pub.data.test.events

import org.pt.pub.data.sources.events.HistoricalEvents
import org.pt.pub.data.sources.events.domain.HistoricalEvent
import spock.lang.Specification

/**
 * Created by vitorfernandes on 11/7/15.
 */
class HistoricalEventsTest extends Specification{
    private HistoricalEvents historicalEvents;

    def setup(){
        historicalEvents=new HistoricalEvents();
    }

    def "Get Events for 1 January"(){
        when:
            List<HistoricalEvent> events=historicalEvents.getEventsByDay(1,1)
        then:
            events.size() > 0
    }

    def "Get Birthdays for 1 December"(){
        when:
            List<HistoricalEvent> birthdays=historicalEvents.getBirthdaysByDay(12,1);
        then:
            birthdays.size() > 0
    }

}
