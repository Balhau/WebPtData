package org.pub.data.sources.yts;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.yts.domain.TorrentLink;
import org.pub.global.utils.DomUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * This is a scrapper for the YIFY yts.ag torrents information
 * Created by vitorfernandes on 9/4/16.
 */
public class YifyTorrents {
    private final String URL_BASE="https://yts.ag";
    private final String BROWSE_PATTERN="/browse-movies?page=%s";

    public YifyTorrents(){

    }

    private String getPageURL(int page){
        return URL_BASE+String.format(BROWSE_PATTERN,page);
    }

    public List<TorrentLink> getTorrentURLs(int page) throws Exception {
        List<TorrentLink> torrentLinks = new ArrayList<>();
        Document doc= DomUtils.get(getPageURL(page)).get();
        Elements links = doc.getElementsByClass("browse-movie-title");
        for(Element link : links){
            torrentLinks.add(new TorrentLink(link.attr("href"),link.text()));
        }
        return torrentLinks;
    }
}
