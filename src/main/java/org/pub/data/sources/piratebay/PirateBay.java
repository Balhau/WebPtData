package org.pub.data.sources.piratebay;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.AbstractDataSource;
import org.pt.pub.global.configs.GlobalConfigs;
import org.pub.data.sources.piratebay.domain.TorrentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * This will scrape the piratebay website, in search for torrent files
 * Created by vitorfernandes on 9/1/16.
 */
public class PirateBay extends AbstractDataSource{
    /**
     * This site will retrieve a list of possible pirateBay proxies
     */
    private static final String PIRATEBAY_PROXY_LIST_URL="https://thepiratebay-proxylist.org/";
    private static final String PIRATEBAY_PROXY_DOM_ATTRIBUTE="data-domain";
    private static final String PIRATEBAY_SEARCH_PATH="/s/?q=%s&page=%s&orderby=%s";

    public static final List<String> PIRATEBAY_PROXY_LIST;
    private String url;

    static{
        PIRATEBAY_PROXY_LIST=getPirateBayProxyList();
    }

    public PirateBay(){
        this.url="https://"+PIRATEBAY_PROXY_LIST.get((int)Math.floor(Math.random()*PIRATEBAY_PROXY_LIST.size()));
    }

    public List<TorrentInfo> searchTorrents(String query,int page,String order) throws Exception{
        Connection con = Jsoup.connect(buildSearchString(query,page,order)).userAgent(GlobalConfigs.USER_AGENT).timeout(GlobalConfigs.CONNECTION_TIMEOUT);
        Document doc = con.get();
        Element body = doc.body();
        return null;
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
            Connection con = Jsoup.connect(PIRATEBAY_PROXY_LIST_URL).userAgent(GlobalConfigs.USER_AGENT).timeout(GlobalConfigs.CONNECTION_TIMEOUT);
            Document doc = con.get();
            Elements proxies = doc.getElementsByAttribute(PIRATEBAY_PROXY_DOM_ATTRIBUTE);
            for(Element proxy : proxies){
                proxyList.add(proxy.attr(PIRATEBAY_PROXY_DOM_ATTRIBUTE));
            }
        }catch (Exception ex){}

        return proxyList;
    }
}
