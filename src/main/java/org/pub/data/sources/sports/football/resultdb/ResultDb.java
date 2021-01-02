package org.pub.data.sources.sports.football.resultdb;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.sports.football.resultdb.domain.League;
import org.pub.data.sources.sports.football.resultdb.domain.LeagueInfo;
import org.pub.data.sources.sports.football.resultdb.domain.Result;
import org.pub.data.sources.sports.football.resultdb.domain.Season;
import org.pub.global.base.ScraperPool;
import org.pub.global.utils.DomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by balhau on 9/21/16.
 */
public class ResultDb {

    public class SeasonCallable implements Callable<Season> {
        private final String url;
        public static final int MAX_RESULTS = 600;
        private final Map<String, String> postdata;

        public SeasonCallable(String url, int limit) {
            this.url = url;
            this.postdata = new HashMap<>();
            this.postdata.put("gamelimit", limit + "");
        }

        public SeasonCallable(String url) {
            this(url, MAX_RESULTS);
        }

        @Override
        public Season call() throws Exception {
            List<Result> results = new ArrayList<>();
            String[] aux = url.split("/");
            int year = Integer.parseInt(aux[aux.length - 1]);
            String date;
            String homeTeam;
            String awayTeam;
            String homeResult;
            String awayResult;
            String[] split;
            Document doc = DomUtils.getHTML(url).data(postdata).post();
            Element tblResults = doc.getElementsByClass("results").get(0);
            Elements raux = tblResults.getElementsByTag("tr");
            List<Element> rows = raux.subList(1, raux.size());
            List<String> teams = new ArrayList<>();

            for (Element row : rows) {
                try {
                    Elements tds = row.getElementsByTag("td");
                    date = tds.get(0).text();
                    split = tds.get(1).text().split("-");
                    homeTeam = split[0];
                    awayTeam = split[1];
                    if (!teams.contains(homeTeam)) teams.add(homeTeam);
                    split = tds.get(2).text().split("-");
                    if (split.length == 2) {
                        homeResult = split[0];
                        awayResult = split[1];
                    } else {
                        homeResult = "-1";
                        awayResult = "-1";
                    }
                    results.add(new Result(date, homeTeam, awayTeam, Integer.parseInt(homeResult), Integer.parseInt(awayResult)));
                } catch (Exception ex) {
                    System.out.print("MERDA");
                }
            }
            return new Season(year, teams, results);
        }
    }

    public static final String HOSTNAME = "http://www.resultdb.com";

    public ResultDb() {

    }

    private List<Season> getSeasonsFromURLList(List<String> urls) throws Exception {

        List<Future<Season>> futures = new ArrayList<>();
        List<Season> seasons = new ArrayList<>();

        for (String url : urls) {
            futures.add(ScraperPool.getPool().submit(new SeasonCallable(url)));
        }

        for (Future<Season> future : futures) {
            seasons.add(future.get());
        }

        return seasons;
    }

    private List<String> getLeagueSeasonsURL(String url) throws Exception {
        List<String> seasonsUrls = new ArrayList<>();
        Document doc = DomUtils.getHTML(url).get();
        Element tblResults = doc.getElementsByClass("results").get(0);
        Elements rows = tblResults.getElementsByTag("tr");
        List<Element> lRows = rows.subList(1, rows.size());
        for (Element row : lRows) {
            seasonsUrls.add(url + row.getElementsByTag("a").get(0).attr("href"));
        }
        return seasonsUrls;
    }

    private List<LeagueInfo> parseLeaguesInfoFromDoc(Document doc) throws Exception {
        List<LeagueInfo> leagues = new ArrayList<>();
        Element leftDiv = doc.getElementById("left");
        Element box = leftDiv.getElementsByClass("box").get(0);
        Elements liels = box.getElementsByTag("li");
        for (Element li : liels) {
            Element anchor = li.getElementsByTag("a").get(0);
            leagues.add(
                    new LeagueInfo(anchor.text(),
                            HOSTNAME + anchor.attr("href"))
            );

        }
        return leagues;
    }

    public League getLeagueFromLeagueInfo(LeagueInfo info) throws Exception {
        return new League(info.getDescription(), info.getUrl(), getSeasonsFromURLList(getLeagueSeasonsURL(info.getUrl())));
    }

    public List<League> leaguesFromLeagueInfo(List<LeagueInfo> leagueInfos) throws Exception {
        List<League> leagues = new ArrayList<>();
        for (LeagueInfo info : leagueInfos) {
            leagues.add(getLeagueFromLeagueInfo(info));
        }
        return leagues;
    }

    public List<LeagueInfo> getAllLeaguesInfo() throws Exception {
        Connection con = DomUtils.getHTML(HOSTNAME);
        Document doc = con.get();
        return parseLeaguesInfoFromDoc(doc);
    }

    public List<League> getAllLeagues() throws Exception {
        return leaguesFromLeagueInfo(getAllLeaguesInfo());
    }
}
