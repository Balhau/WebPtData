package groovy.org.pub.data.test.events

import org.pt.pub.data.sources.domain.Message
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

    def "Get event message"(){
        when:
            Message historicalMessage = historicalEvents.getMessage()
        then:
            historicalMessage!=null
            historicalMessage.message!=null
            historicalMessage.source!=null

    }

    def "Get deaths for 5 of november"(){
        when:
            List<HistoricalEvent> deaths= historicalEvents.getDeathsByDay(11,5)
        then:
            deaths.size()>0
    }

    def "Get today events"(){
        when:
            List<HistoricalEvent> eventsToday=historicalEvents.getTodayEvents();
        then:
            eventsToday.size()>0
    }

}
