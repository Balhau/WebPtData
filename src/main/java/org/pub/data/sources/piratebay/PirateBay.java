package org.pub.data.sources.piratebay;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.global.base.ScraperPool;
import org.pub.global.utils.DomUtils;
import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * This will scrape the piratebay website, in search for torrent files
 * Created by vitorfernandes on 9/1/16.
 */
public class PirateBay extends AbstractDataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    class TorrentCallable implements Callable<TorrentInfo>{
        private final String torrentURL;
        private final String title;
        public TorrentCallable(String title,String torrentURL){
            this.torrentURL=torrentURL;
            this.title=title;
        }
        @Override
        public TorrentInfo call() throws Exception {
            Document doc = DomUtils.get(torrentURL).get();
            Elements torrentInfo = doc.getElementsByTag("dl").get(1).getElementsByTag("dd");

            return new TorrentInfo(title,
                    doc.getElementsByClass("nfo").get(0).getElementsByTag("pre").text(),
                    doc.getElementsByClass("download").get(0).getElementsByTag("a").get(0).attr("href"),
                    torrentURL,torrentInfo.get(0).text(),Integer.parseInt(torrentInfo.get(2).text()),Integer.parseInt(torrentInfo.get(3).text())
            );
        }
    }
    /**
     * This site will retrieve a list of possible pirateBay proxies
     */
    private static final String PIRATEBAY_PROXY_LIST_URL="https://thepiratebay-proxylist.org/";
    private static final String PIRATEBAY_PROXY_DOM_ATTRIBUTE="data-domain";
    private static final String PIRATEBAY_SEARCH_PATH="/s/?q=%s&page=%s&orderby=%s";

    public static final String UNORDERED="99";

    public static final List<String> PIRATEBAY_PROXY_LIST;
    private String url;

    static{
        PIRATEBAY_PROXY_LIST=getPirateBayProxyList();
    }

    public PirateBay(){
        this.url="https://"+PIRATEBAY_PROXY_LIST.get((int)Math.floor(Math.random()*PIRATEBAY_PROXY_LIST.size()));
    }

    public List<TorrentInfo> searchTorrents(String query,int page,String order) throws Exception{
        Connection con = DomUtils.get(buildSearchString(query, page, order));
        Document doc = con.get();
        return parseTorrents(doc);
    }

    /**
     * This private method will parse all torrent lines and fetch asynchronously all the torrent info presented in
     * the torrent page
     * @param doc
     * @return
     * @throws Exception
     */
    private List<TorrentInfo> parseTorrents(Document doc) throws Exception{
        Elements torrentsLines=doc.getElementsByClass("detName");
        List<TorrentInfo> torrents = new ArrayList<>(torrentsLines.size());
        Set<Future<TorrentInfo>> torrentFutures = new HashSet<>();
        String torrentURL;
        String title;
        Element anchor;
        //Get asynchronously the torrentInfo
        for(Element torrentLine : torrentsLines){
            anchor=torrentLine.getElementsByTag("a").get(0);
            torrentURL=url+anchor.attr("href");
            title=anchor.text();
            torrentFutures.add(ScraperPool.getPool().submit(new TorrentCallable(title,torrentURL)));
        }

        for(Future<TorrentInfo> t : torrentFutures){
            try{
                torrents.add(t.get());
            }catch (Exception ex){
                logger.error(ex.getLocalizedMessage());
            }
        }

        return torrents;

    }

    private String buildSearchString(String query,int page,String orderBy){
        return String.format(url+PIRATEBAY_SEARCH_PATH,query,page,orderBy);
    }

    /**
     * This will retrieve a list of thepiratebay endpoints
     * @return
     */
    public static List<String> getPirateBayProxyList(){
        List<String> proxyList=new ArrayList<>();

        try {
            Connection con = DomUtils.get(PIRATEBAY_PROXY_LIST_URL);
            Document doc = con.get();
            Elements proxies = doc.getElementsByAttribute(PIRATEBAY_PROXY_DOM_ATTRIBUTE);
            for(Element proxy : proxies){
                proxyList.add(proxy.attr(PIRATEBAY_PROXY_DOM_ATTRIBUTE));
            }
        }catch (Exception ex){}

        return proxyList;
    }
}
