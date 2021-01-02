package org.pub.data.sources.piratebay;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This will scrape the piratebay website, in search for torrent files
 * Created by balhau on 9/1/16.
 */
public class PirateBay extends AbstractDataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This site will retrieve a list of possible pirateBay proxies
     */
    private static final String PIRATEBAY_PROXY_LIST_URL = "https://thepiratebayproxylist.se/";
    private static final String PIRATEBAY_SEARCH_PATH = "apibay/q.php?q=%s&page=%s&orderby=%s";

    public static final String UNORDERED = "99";

    public static final List<String> PIRATEBAY_PROXY_LIST;
    private String url;

    static {
        PIRATEBAY_PROXY_LIST = getPirateBayProxyList();
    }

    public PirateBay() {
        this.url = PIRATEBAY_PROXY_LIST.get((int) Math.floor(Math.random() * PIRATEBAY_PROXY_LIST.size()));
    }

    public List<TorrentInfo> searchTorrents(String query, int page, String order) throws Exception {
        String jsonData = DomUtils.getRawString(buildSearchString(query, page, order));
        return parseTorrents(jsonData);
    }

    /**
     * Parse json data
     *
     * @return
     * @throws Exception
     */
    private List<TorrentInfo> parseTorrents(String jsonData) throws Exception {
        Type torrentListType = new TypeToken<ArrayList<TorrentInfo>>() {
        }.getType();
        List<TorrentInfo> torrents = new Gson().fromJson(jsonData, torrentListType);
        return torrents;

    }

    private String buildSearchString(String query, int page, String orderBy) {
        return String.format(url + PIRATEBAY_SEARCH_PATH, query, page, orderBy);
    }

    /**
     * This will retrieve a list of thepiratebay endpoints
     *
     * @return
     */
    public static List<String> getPirateBayProxyList() {
        List<String> proxyList = new ArrayList<>();
        try {
            Connection con = DomUtils.getHTML(PIRATEBAY_PROXY_LIST_URL);
            Document doc = con.get();
            Elements proxies = doc.getElementsByTag("tbody").get(0).getElementsByAttribute("href");
            return proxies.stream()
                    .map(p -> p.attr("href"))
                    .skip(1)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return proxyList;
        }
    }
}
