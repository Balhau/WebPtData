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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * This is a scrapper for the YIFY yts.ag torrents information
 * Created by vitorfernandes on 9/4/16.
 */
public class YifyTorrents extends AbstractDataSource {

    class YifyTorrentCallable implements Callable<Optional<YifyTorrent>>{
        private final TorrentLink tlink;

        public YifyTorrentCallable(TorrentLink tlink){
            this.tlink=tlink;
        }
        @Override
        public Optional<YifyTorrent> call() throws Exception {
            Optional<YifyTorrent> opt;
            try{
                opt=Optional.of(getTorrent(tlink));
            }catch (Exception ex){
                opt=Optional.empty();
            }
            return opt;
        }
    }

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
        List<TorrentMovieData> torrentLinks = new ArrayList<>();
        Element a;
        Document doc = DomUtils.get(url).get();
        Element movieInfo=doc.getElementById("movie-info");
        String name=movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h1").text();
        int year = Integer.parseInt(movieInfo.getElementsByClass("hidden-xs").get(0).getElementsByTag("h2").get(0).text());
        Elements tlinks = movieInfo.getElementsByTag("p").get(0).getElementsByTag("a");
        for(Element tlink : tlinks){
            torrentLinks.add(
                    new TorrentMovieData(tlink.attr("href"),tlink.text())
            );
        }
        Elements ratings = movieInfo.getElementsByClass("rating-row");
        int likes = Integer.parseInt(ratings.get(0).getElementById("movie-likes").text());

        a=ratings.get(1).getElementsByTag("a").get(0);
        String rtCriticsValue = ratings.get(1).getElementsByTag("span").get(0).text();
        String rtCriticsUrl = a.attr("href");
        String rtCriticsDescription = a.attr("title");
        Ranking rtCritics = new Ranking(rtCriticsDescription,rtCriticsUrl,rtCriticsValue);

        a=ratings.get(2).getElementsByTag("a").get(0);
        String rtAudienceValue = ratings.get(2).getElementsByTag("span").get(0).text();
        String rtAudienceUrl = a.attr("href");
        String rtAudienceDescription = a.attr("title");
        Ranking rtAudience = new Ranking(rtAudienceDescription,rtAudienceUrl,rtAudienceValue);

        a=ratings.get(3).getElementsByTag("a").get(0);
        String imdbValue = ratings.get(3).getElementsByTag("span").get(0).text();
        String imdbUrl = a.attr("href");
        String imdbDescription = a.attr("title");
        Ranking imdb = new Ranking(imdbDescription,imdbUrl,imdbValue);


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

    public List<YifyTorrent> getTorrentsFromPage(int page) throws Exception{
        List<YifyTorrent> t = new ArrayList<>();
        List<Future<Optional<YifyTorrent>>> futures=new ArrayList<>();
        List<TorrentLink> tls = getTorrentURLs(page);
        for(TorrentLink tl : tls){
            futures.add(ScraperPool.getPool().submit(new YifyTorrentCallable(tl)));
        }

        for(Future<Optional<YifyTorrent>> f : futures){
            if(f.get().isPresent()){
                t.add(f.get().get());
            }
        }

        return t;
    }
}
