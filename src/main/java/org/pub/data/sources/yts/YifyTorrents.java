package org.pub.data.sources.yts;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.pub.data.sources.yts.domain.TorrentLink;
import org.pub.data.sources.yts.domain.YifyTorrent;
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

    /**
     * This gives you a {@link TorrentInfo} based on the url of the torrent
     * @param url
     * @return
     */
    public YifyTorrent getTorrentFromURL(String url) throws Exception{
        List<String> torrentLinks = new ArrayList<>();
        Document doc = DomUtils.get(url).get();
        Element movieInfo=doc.getElementById("movie-info");
        String name=movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h1").text();
        int year = Integer.parseInt(movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h2").get(0).text());
        Elements tlinks = movieInfo.getElementsByTag("p");
        for(Element tlink : tlinks){
            torrentLinks.add(tlink.getElementsByTag("a").attr("href"));
        }
        Elements ratings = movieInfo.getElementsByClass("rating-row");
        int likes = Integer.parseInt(ratings.get(0).getElementById("movie-likes").text());
        String rtCritics = ratings.get(1).getElementsByTag("span").get(0).text();
        String rtAudience = ratings.get(2).getElementsByTag("span").get(0).text();
        String imdb = ratings.get(3).getElementsByTag("span").get(0).text();
        String imageUrl = doc.getElementById("movie-poster").getElementsByTag("img").get(0).attr("src");
        return new YifyTorrent(name,torrentLinks,imdb,rtCritics,rtAudience,likes,year,imageUrl);
    }

    /**
     * Given a TorrentLink this method will give you the respective {@link TorrentInfo} containing the torrent information
     * you want
     * @param link
     * @return
     */
    public YifyTorrent getTorrent(TorrentLink link) throws Exception{
        return getTorrentFromURL(link.getUrl());
    }
}
