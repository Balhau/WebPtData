package org.pub.data.sources.piratebay;

import com.sun.org.apache.xerces.internal.util.DOMUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.AbstractDataSource;
import org.pt.pub.global.configs.GlobalConfigs;
import org.pt.pub.global.utils.DomUtils;
import org.pub.data.sources.piratebay.domain.TorrentInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This will scrape the piratebay website, in search for torrent files
 * Created by vitorfernandes on 9/1/16.
 */
public class PirateBay extends AbstractDataSource{

    class TorrentCallable implements Callable<TorrentInfo>{
        private final String torrentURL;
        public TorrentCallable(String torrentURL){
            this.torrentURL=torrentURL;
        }
        @Override
        public TorrentInfo call() throws Exception {
            DomUtils.get(torrentURL);
            return null;
        }
    }
    /**
     * This site will retrieve a list of possible pirateBay proxies
     */
    private static final String PIRATEBAY_PROXY_LIST_URL="https://thepiratebay-proxylist.org/";
    private static final String PIRATEBAY_PROXY_DOM_ATTRIBUTE="data-domain";
    private static final String PIRATEBAY_SEARCH_PATH="/s/?q=%s&page=%s&orderby=%s";
    private static final int THREAD_POOL_SIZE=20;

    public static final List<String> PIRATEBAY_PROXY_LIST;
    private String url;
    private ExecutorService pool;

    static{
        PIRATEBAY_PROXY_LIST=getPirateBayProxyList();
    }

    public PirateBay(){
        this.url="https://"+PIRATEBAY_PROXY_LIST.get((int)Math.floor(Math.random()*PIRATEBAY_PROXY_LIST.size()));
        this.pool= Executors.newFixedThreadPool(THREAD_POOL_SIZE);
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
        //Get asynchronously the torrentInfo
        for(Element torrentLine : torrentsLines){
            torrentURL=url+torrentLine.getElementsByTag("a").get(0).attr("href");
            torrentFutures.add(pool.submit(new TorrentCallable(torrentURL)));
        }

        for(Future<TorrentInfo> t : torrentFutures){
            torrents.add(t.get());
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
