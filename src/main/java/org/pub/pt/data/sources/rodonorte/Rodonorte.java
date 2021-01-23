package org.pub.pt.data.sources.rodonorte;


import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.rodonorte.callable.FetchCalendar;
import org.pub.pt.data.sources.rodonorte.domain.CalendarEntry;
import org.pub.pt.data.sources.rodonorte.domain.RodonorteCalendar;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


/**
 * This will return the scheduling of bus of a northern portuguese company
 */
public class Rodonorte {
    private static final String RODONORTE_CALENDAR_HOST = "http://beware.pt";
    private static final String CALLENDAR_LIST_PATTERN = "/IP/MotorBusca/rodonorte/Horarios.aspx?g=%s";
    private static final String CALLENDAR_PATTERN = "/IP/MotorBusca/rodonorte/Horario.aspx?id=%s";

    public static int NUMBER_OF_CALENDARS=19;

    private ExecutorService executorService;

    public Rodonorte(final ExecutorService executorService) {
        this.executorService = executorService;
    }

    public List<RodonorteCalendar> getCalendars(int page) throws Exception {
        if(page>NUMBER_OF_CALENDARS){
            throw new Exception("Illegal callendar page, max: "+NUMBER_OF_CALENDARS);
        }
        List<CalendarEntry> calendarEntries = getCalendarIds(page);

        //First fetch all futures
        List<Future<RodonorteCalendar>> futureCalendars = calendarEntries.stream()
                .map(this::getCalendarFuture)
                .collect(Collectors.toList());

        //Now wait for all the futures
        //This double iteration is needed to avoid submit/get cycles that
        //would defeat the all purpose of concurrency calls
        return futureCalendars.stream()
                .map(this::getOrNull)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Future<RodonorteCalendar> getCalendarFuture(final CalendarEntry calendarEntry){
        String calendarURL = String.format(RODONORTE_CALENDAR_HOST + CALLENDAR_PATTERN, calendarEntry.getId());
        return executorService.submit(new FetchCalendar(calendarEntry.getDescription(),calendarURL));
    }

    private <T> T getOrNull(Future<T> future){
        try{
            return future.get();
        }catch (Exception ex){
            return null;
        }
    }

    private List<CalendarEntry> getCalendarIds(final int page) throws Exception {
        String calendarsPageURL = String.format(RODONORTE_CALENDAR_HOST + CALLENDAR_LIST_PATTERN, page);
        Connection cn = DomUtils.getHTML(calendarsPageURL);
        Document doc = cn.get();

        return doc.getElementsByTag("tr")
                .stream()
                .map(this::toCalendarEntry)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CalendarEntry toCalendarEntry(final Element line) {
        if (line.attr("onclick").isBlank()) {
            return null;
        }

        int id = Integer.parseInt(line.attr("onclick").split("\\?id=")[1].split("';")[0]);
        String desc = line.text();
        return new CalendarEntry(desc, id);
    }
}
