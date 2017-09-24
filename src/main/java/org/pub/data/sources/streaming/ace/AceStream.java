package org.pub.data.sources.streaming.ace;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.streaming.ace.domain.AceFeed;
import org.pub.global.utils.DomUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will give us a set of calls to retrieve acestream links
 */
public class AceStream {
    public static final String ACE_STOP_BASE="https://acesoplisting.in/";

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
}
