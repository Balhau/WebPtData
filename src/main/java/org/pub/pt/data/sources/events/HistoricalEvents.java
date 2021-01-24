package org.pub.pt.data.sources.events;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.domain.Message;
import org.pub.pt.data.sources.domain.MessageService;
import org.pub.pt.data.sources.events.domain.HistoricalEvent;
import org.pub.pt.data.utilities.Utils;

import java.util.*;

/**
 * This is a feed that returns information about relevant events that occurred in a certain day
 * Created by vitorfernandes on 11/7/15.
 */
public class HistoricalEvents implements MessageService {
    public static final String URL_BASE = "https://www.onthisday.com";
    public static final String EVENTS_BY_DAY_PATTERN = URL_BASE + "/events/%s/%s";
    public static final String BIRTHDAY_BY_DAY_PATTERN = URL_BASE + "/birthdays/%s/%s";
    public static final String DEATHS_BY_DAY_PATTERN = URL_BASE + "/deaths/%s/%s";

    private static final String[] MONTHS = new String[]{
            "january", "february", "march", "april", "may",
            "june", "july", "august", "september", "october",
            "november", "december"
    };


    public HistoricalEvents() {
    }

    public Message getMessage() throws Exception {
        List<HistoricalEvent> events = getTodayEvents();
        HistoricalEvent event = Utils.pickRandom(events);
        int month = event.getMonth() + 1;
        return new Message(
                event.getEvent(),
                event.getYear() + "-" + month + "-" + event.getDay()
        );
    }

    /**
     * This will return a list with the events that happened in this specific day of a equally specific month
     *
     * @param month Month number
     * @param day   Day number
     * @return List of historical events
     */
    public List<HistoricalEvent> getEventsByDay(int month, int day) throws Exception {
        Connection con = DomUtils.getHTML(
                String.format(EVENTS_BY_DAY_PATTERN, MONTHS[month - 1], day)
        );

        return parseDocument(con, month, day);
    }

    public List<HistoricalEvent> getTodayEvents() throws Exception {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return getEventsByDay(month + 1, day);

    }

    public List<HistoricalEvent> getDeathsByDay(int month, int day) throws Exception {
        Connection con = DomUtils.getHTML(
                String.format(DEATHS_BY_DAY_PATTERN, MONTHS[month - 1], day)
        );
        return parseDocument(con, month, day);
    }

    public List<HistoricalEvent> getBirthdaysByDay(int month, int day) throws Exception {
        Connection con = DomUtils.getHTML(
                String.format(BIRTHDAY_BY_DAY_PATTERN, MONTHS[month - 1], day)
        );
        return parseDocument(con, month, day);
    }

    private List<HistoricalEvent> parseDocument(Connection con, int month, int day) throws Exception {
        List<HistoricalEvent> events = new ArrayList<>();
        Document doc = con.get();
        Elements eventsLi = doc.getElementsByClass("event-list");

        for (Element event : eventsLi) {
            safeParse(events,event,month,day);
        }
        return events;
    }

    private void safeParse(List<HistoricalEvent> events,Element event,int month, int day){
        try {
            int year = Integer.parseInt(event.getElementsByTag("a").get(0).text().split("BC")[0].split("AC")[0].trim());
            HistoricalEvent hevent = new HistoricalEvent(day, month, year, event.text());
            events.add(hevent);
        }catch (Exception ex){}
    }
}
