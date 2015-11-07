package org.pt.pub.data.sources.events;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.pt.pub.data.sources.domain.MessageService;
import org.pt.pub.data.sources.domain.Message;
import org.pt.pub.data.sources.events.domain.HistoricalEvent;
import org.pt.pub.data.utilities.Utils;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.*;

/**
 * This is a feed that returns information about relevant events that occurred in a certain day
 * Created by vitorfernandes on 11/7/15.
 */
public class HistoricalEvents implements MessageService{
    public static final String URL_BASE="http://www.onthisday.com";
    public static final String EVENTS_BY_DAY_PATTERN=URL_BASE+"/events/%s/%s";
    public static final String BIRTHDAY_BY_DAY_PATTERN=URL_BASE+"/birthdays/%s/%s";
    public static final String DEATHS_BY_DAY_PATTERN=URL_BASE+"/deaths/%s/%s";

    private static final String[] MONTHS=new String[]{
            "january","february","march","april","may",
            "june","july","august","september","october",
            "november","december"
    };


    public HistoricalEvents(){

    }

    public Message getMessage() throws Exception{
        Date now=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(now);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        List<HistoricalEvent> events= getEventsByDay(month,day);
        HistoricalEvent event=Utils.pickRandom(events);
        return new Message(
                event.getEvent(),
                "Historical Event"
        );
    }

    /**
     * This will return a list with the events that happened in this specific day of a equally specific month
     * @param month Month number
     * @param day Day number
     * @return List of historical events
     */
    public List<HistoricalEvent> getEventsByDay(int month,int day) throws Exception{
        Connection con = Jsoup.connect(
                String.format(EVENTS_BY_DAY_PATTERN,MONTHS[month-1],day)
        ).userAgent(GlobalConfigs.USER_AGENT);

        return parseDocument(con,month,day);
    }

    public List<HistoricalEvent> getBirthdaysByDay(int month,int day) throws Exception{
        Connection con = Jsoup.connect(
                String.format(BIRTHDAY_BY_DAY_PATTERN,MONTHS[month-1],day)
        ).userAgent(GlobalConfigs.USER_AGENT);
        return parseDocument(con,month,day);
    }

    private List<HistoricalEvent> parseDocument(Connection con,int month,int day) throws Exception{
        List<HistoricalEvent> events=new ArrayList<>();
        Document doc=con.get();
        String textToParse=doc.getElementById("main_text").getElementsByTag("p").get(1).html();
        String[] eventsToParse=textToParse.split("<br />");

        for(String eventToParse : eventsToParse){
            Optional<HistoricalEvent> event=parseLineEventToHistoricalEvent(eventToParse, month, day);
            if(event.isPresent()) events.add(event.get());
        }

        return events;
    }

    private Optional<HistoricalEvent> parseLineEventToHistoricalEvent(String line,int month,int day)
    {
        Element body=Jsoup.parseBodyFragment(line).body();
        String[] fields=body.text().split("-");
        int year;
        try {
            year=fields[0].contains("BC") ? -Integer.valueOf(fields[0].split("BC")[0].trim()) : Integer.valueOf(fields[0].trim());
        }catch (Exception ex){return Optional.empty();}

        String event = fields[1];

        return Optional.of(new HistoricalEvent(day,month,year,event));
    }
}
