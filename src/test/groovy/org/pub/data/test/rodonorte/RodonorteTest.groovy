package org.pub.data.test.rodonorte

import org.pub.global.base.ScraperPool
import org.pub.pt.data.sources.rodonorte.Rodonorte
import org.pub.pt.data.sources.rodonorte.domain.RodonorteCalendar
import spock.lang.Specification


class RodonorteTest extends Specification {
    private Rodonorte rodonorte;

    def setup() {
        rodonorte = new Rodonorte(ScraperPool.getPool())
    }

    def "Get Calendars for page 1"() {
        when:
        List<RodonorteCalendar> calendarPages = rodonorte.getCalendars(1)
        then:
        calendarPages != null &&
                calendarPages.size() > 0 &&
                calendarPages.get(0) != null &&
                calendarPages.get(0).getCalendarMap() != null &&
                calendarPages.get(0).getCalendarMap().size() > 0
    }

    def "Get Calendars for page 2"() {
        when:
        List<RodonorteCalendar> calendarPages = rodonorte.getCalendars(2)
        then:
        calendarPages != null &&
                calendarPages.size() > 0 &&
                calendarPages.get(0) != null &&
                calendarPages.get(0).getCalendarMap() != null &&
                calendarPages.get(0).getCalendarMap().size() > 0
    }

}
