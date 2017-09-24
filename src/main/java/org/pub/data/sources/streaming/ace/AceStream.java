package org.pub.data.sources.streaming.ace;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.streaming.ace.domain.AceFeed;
import org.pub.data.sources.streaming.ace.domain.AceSportsFeed;
import org.pub.global.utils.DomUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will give us a set of calls to retrieve acestream links
 */
public class AceStream {
    public static final String ACE_STOP_BASE="https://acesoplisting.in/";
    public static final String ACE_SPORTS = "http://acesports.tk/";

    public AceStream(){}

    /**
     * This call will retrieve all the feeds presented in the ACE_STOP_BASE feed
     * @return List of {@link AceFeed} elements
     * @throws Exception
     */
    public List<AceFeed> getAceStopFeeds() throws Exception {
        Document doc = DomUtils.get(ACE_STOP_BASE).get();
        Elements domFeeds = doc.getElementsByTag("a");
        return domFeeds.stream()
                .filter(el -> el.attr("href").startsWith("acestream://"))
                .map( el -> new AceFeed(el.attr("href"),el.attr("title")))
                .collect(Collectors.toList());
    }

    public List<AceSportsFeed> getAceSportsFeeds() throws Exception {

        Document doc = DomUtils.get(ACE_SPORTS).get();
        Elements domFeeds = doc.getElementsByTag("tr");

        return domFeeds.stream()
                .filter(element -> element.getElementsByTag("td").size()==5)
                .map(this::sportAceFeedFromRow)
                .collect(Collectors.toList());
    }

    private  AceSportsFeed sportAceFeedFromRow(Element row){

        Elements elements = row.getElementsByTag("td");
        String name = elements.get(0).text();
        String date = elements.get(1).text();
        String channel = elements.get(2).text();
        String language = elements.get(3).text();
        Elements domFeeds = elements.get(4).getElementsByTag("a");

        List<AceFeed> feeds = domFeeds.stream()
                .map(el -> new AceFeed(el.attr("href"),el.text()))
                .collect(Collectors.toList());

        return new AceSportsFeed(
                feeds,name,date,channel,language
        );
    }
}
