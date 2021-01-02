package org.pub.data.sources.yts;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.piratebay.domain.TorrentInfo;
import org.pub.data.sources.yts.domain.Ranking;
import org.pub.data.sources.yts.domain.TorrentLink;
import org.pub.data.sources.yts.domain.TorrentMovieData;
import org.pub.data.sources.yts.domain.YifyTorrent;
import org.pub.global.base.ScraperPool;
import org.pub.global.utils.DomUtils;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * This is a scrapper for the YIFY yts.ag torrents information
 * Created by balhau on 9/4/16.
 */
public class YifyTorrents extends AbstractDataSource {

    class YifyTorrentCallable implements Callable<Optional<YifyTorrent>> {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        private final TorrentLink tlink;

        public YifyTorrentCallable(TorrentLink tlink) {
            this.tlink = tlink;
        }

        @Override
        public Optional<YifyTorrent> call() throws Exception {
            Optional<YifyTorrent> opt;
            try {
                opt = Optional.of(getTorrent(tlink));
            } catch (Exception ex) {
                logger.error(ex.getLocalizedMessage());
                opt = Optional.empty();
            }
            return opt;
        }
    }

    private final String URL_BASE = "https://yts.mx";
    private final String BROWSE_PATTERN = "/browse-movies?page=%s";

    public YifyTorrents() {

    }

    private String getPageURL(int page) {
        return URL_BASE + String.format(BROWSE_PATTERN, page);
    }

    public List<TorrentLink> getTorrentURLs(int page) throws Exception {
        List<TorrentLink> torrentLinks = new ArrayList<>();
        Document doc = DomUtils.getHTML(getPageURL(page)).get();
        Elements links = doc.getElementsByClass("browse-movie-title");
        for (Element link : links) {
            torrentLinks.add(new TorrentLink(link.attr("href"), link.text()));
        }
        return torrentLinks;
    }

    /**
     * This gives you a {@link TorrentInfo} based on the url of the torrent
     *
     * @param url
     * @return
     */
    public YifyTorrent getTorrentFromURL(String url) throws Exception {
        List<TorrentMovieData> torrentLinks = new ArrayList<>();
        Element a;
        Document doc = DomUtils.getHTML(url).get();
        Element movieInfo = doc.getElementById("movie-info");
        String name = movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h1").text();
        int year = Integer.parseInt(movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h2").get(0).text());
        Elements tlinks = movieInfo.getElementsByTag("p").get(0).getElementsByTag("a");
        for (Element tlink : tlinks) {
            torrentLinks.add(
                    new TorrentMovieData(tlink.attr("href"), tlink.text())
            );
        }
        Elements ratings = movieInfo.getElementsByClass("rating-row");
        int likes = Integer.parseInt(ratings.get(0).getElementById("movie-likes").text());

        List<Ranking> rankings = ratings.stream()
                .skip(1)
                .limit(3)
                .filter(this::filterHiddenDivs)
                .map(this::parseRankingEntry)
                .collect(Collectors.toList());


        String imageUrl = doc.getElementById("movie-poster").getElementsByTag("img").get(0).attr("src");
        return buildYifyTorrent(name, torrentLinks, rankings, likes, year, imageUrl);
    }

    /**
     * YiFy ratings are dynamic. In some cases only one others two or even three types of ratings are provided
     * This switch statement deals with the dynamic nature of ratings
     * @return
     */
    private YifyTorrent buildYifyTorrent(String name, List<TorrentMovieData> links, List<Ranking> rankings,
                                         int likes, int year, String imageUrl) {
        switch (rankings.size()) {
            case 1:
                return new YifyTorrent(name, links, rankings.get(0), null, null, likes, year, imageUrl);
            case 2:
                return new YifyTorrent(name, links, rankings.get(1), null, rankings.get(0), likes, year, imageUrl);
            default:
                return new YifyTorrent(name, links, rankings.get(2), rankings.get(0), rankings.get(1), likes, year, imageUrl);
        }
    }

    private boolean filterHiddenDivs(Element el) {
        return el.getElementsByClass("durs-bordered").size() == 0;
    }

    private Ranking parseRankingEntry(Element el) {
        Element a = el.getElementsByTag("a").get(0);
        String value = el.getElementsByTag("span").get(0).text();
        String url = a.attr("href");
        String description = a.attr("title");
        return new Ranking(value, url, description);
    }

    /**
     * Given a TorrentLink this method will give you the respective {@link TorrentInfo} containing the torrent information
     * you want
     *
     * @param link
     * @return
     */
    public YifyTorrent getTorrent(TorrentLink link) throws Exception {
        return getTorrentFromURL(link.getUrl());
    }

    /**
     * @param page
     * @return
     * @throws Exception
     */
    public List<YifyTorrent> getTorrentsFromPage(int page) throws Exception {
        List<YifyTorrent> t = new ArrayList<>();
        List<Future<Optional<YifyTorrent>>> futures = new ArrayList<>();
        List<TorrentLink> tls = getTorrentURLs(page);
        for (TorrentLink tl : tls) {
            futures.add(ScraperPool.getPool().submit(new YifyTorrentCallable(tl)));
        }

        for (Future<Optional<YifyTorrent>> f : futures) {
            if (f.get().isPresent()) {
                t.add(f.get().get());
            }
        }

        return t;
    }
}
