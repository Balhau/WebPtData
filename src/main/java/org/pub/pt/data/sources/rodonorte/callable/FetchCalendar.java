package org.pub.pt.data.sources.rodonorte.callable;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.rodonorte.domain.BusEntry;
import org.pub.pt.data.sources.rodonorte.domain.RodonorteCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Callable stratey to fetch asyncrhonously calendar entries
 */
public class FetchCalendar implements Callable<RodonorteCalendar> {

    private final String calendarDescription;
    private final String calendarURL;

    public FetchCalendar(String calendarDescription, String calendarURL) {
        this.calendarDescription = calendarDescription;
        this.calendarURL = calendarURL;
    }

    @Override
    public RodonorteCalendar call() throws Exception {
        Connection cn = DomUtils.getHTML(this.calendarURL);
        Document doc = cn.get();

        Elements lines = doc
                .getElementsByClass("scheduleTable")
                .get(0)
                .getElementsByTag("tr");

        List<String> epochs = parseHeaderLine(lines.get(0));
        List<String> dayTypes = parseHeaderLine(lines.get(1));

        Map<String,List<BusEntry>> calendarMap = lines.stream()
                .skip(2)
                .map(e->mapLine(e,epochs,dayTypes))
                .collect(
                        Collectors.toMap(
                                Map.Entry<String,List<BusEntry>>::getKey,
                                Map.Entry<String,List<BusEntry>>::getValue
                        )
                );

        return new RodonorteCalendar(calendarMap);
    }

    private List<String> parseHeaderLine(Element line){
        return line.getElementsByTag("td")
                .stream()
                .skip(1)
                .map(Element::text)
                .collect(Collectors.toList());
    }

    private Map.Entry<String,List<BusEntry>> mapLine(Element busLine, List<String> epochs,List<String> dayTypes){
        Elements columns = busLine.getElementsByTag("td");
        String name = columns.get(0).text();
        return Map.entry(name,parseBusLine(busLine,epochs,dayTypes));
    }

    private List<BusEntry> parseBusLine(Element line,List<String> epochs,List<String> days){
        Elements collumns =line.getElementsByTag("td");
        List<BusEntry> entries = new ArrayList<>(collumns.size());
        for(int i=1;i<collumns.size();i++){
            entries.add(new BusEntry(epochs.get(i-1),days.get(i-1), collumns.get(i).text()));
        }

        return entries;
    }

}
