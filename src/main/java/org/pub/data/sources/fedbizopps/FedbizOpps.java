package org.pub.data.sources.fedbizopps;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.fedbizopps.domain.Opportunity;
import org.pub.data.sources.fedbizopps.domain.OpportunityDetails;
import org.pub.global.utils.DomUtils;
import org.pub.global.utils.PredicateDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Scrapper for the fedbizopps page. This web platform holds the contracts available for the federal government of USA.
 * The purpose of this scraper consists in giving a simplified API to retrieve valuable information that is stored and
 * available through this web interface
 */
public class FedbizOpps {
    public static final String FED_BIZ_OPPS_URL = "https://www.fbo.gov/";
    public static final String INDEX_SEARCH_PATTERN = "index?s=opportunity&mode=list&tab=list&pageID=%s";
    private final ExecutorService pool;

    private final Function<Future<Opportunity>, Opportunity>
            opportunityFromFuture = (Future<Opportunity> o) -> {
        Opportunity opportunity;
        try {
            opportunity = o.get();
        } catch (Exception ex) {
            return (Opportunity) null;
        }
        return opportunity;
    };

    class DetailsCallable implements Callable<OpportunityDetails> {

        private final String url;

        public DetailsCallable(String url) {
            this.url = url;
        }

        @Override
        public OpportunityDetails call() throws Exception {
            Connection con = DomUtils.getHTML(url);
            Document doc = con.get();

            return null;
        }
    }

    class OpportunityFuture implements Future<Opportunity> {
        private final Future<OpportunityDetails> detailsFuture;
        private final Opportunity opportunity;

        public OpportunityFuture(Opportunity opportunity, Future<OpportunityDetails> detailsFuture) {
            this.detailsFuture = detailsFuture;
            this.opportunity = opportunity;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public Opportunity get() throws InterruptedException, ExecutionException {
            return new Opportunity.OpportunityBuilder()
                    .Agency(opportunity.getAgency())
                    .Date(opportunity.getDate())
                    .Description(opportunity.getDescription())
                    .Id(opportunity.getId())
                    .Details(detailsFuture.get())
                    .build();
        }

        @Override
        public Opportunity get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }

    public FedbizOpps(ExecutorService pool) {
        this.pool = pool;
    }

    private String buildIndexSearchString(int page) {
        return String.format(FED_BIZ_OPPS_URL + INDEX_SEARCH_PATTERN, page);
    }

    private Future<Opportunity> parseIndexSearchRowDetails(String urldetails) {
        return null;
    }

    private Future<Opportunity> parseIndexSearchRow(Element row) {
        try {
            row.getElementsByTag("td").get(0).getElementsByTag("a");
            String url = FED_BIZ_OPPS_URL + row.getElementsByTag("td").get(0).getElementsByTag("a").attr("href");
            String agency = row.getElementsByTag("td").get(1).text();
            String type = row.getElementsByTag("td").get(2).text();
            String date = row.getElementsByTag("td").get(3).text();

            return new OpportunityFuture(
                    new Opportunity.OpportunityBuilder()
                            .Id(url)
                            .Agency(agency)
                            .Type(type)
                            .Date(date).build(), pool.submit(new DetailsCallable(url))
            );
        } catch (Exception ex) {
            return null;
        }
    }

    private List<Opportunity> parseIndexSearchResults(Document doc) throws Exception {
        List<Opportunity> searchList = new ArrayList<>();
        Elements els = doc.getElementsByClass("list").get(0).getElementsByTag("tr");
        List<Element> rows = els.subList(1, els.size());

        List<Future<Opportunity>> futures = rows.stream()
                .map(this::parseIndexSearchRow)
                .filter(PredicateDictionary.NOT_NULL)
                .collect(Collectors.toList());

        List<Opportunity> opportunities = futures
                .stream()
                .map(opportunityFromFuture)
                .filter(PredicateDictionary.NOT_NULL)
                .collect(Collectors.toList());

        return opportunities;
    }

    /**
     * Given a page number this method will return a {@link List} of {@link Opportunity} objects with a bunch of
     * structured information
     *
     * @param page
     * @return
     * @throws Exception
     */
    public List<Opportunity> getOpportunitiesPage(int page) throws Exception {
        Connection con = DomUtils.getHTML(buildIndexSearchString(page));
        return parseIndexSearchResults(con.get());

    }
}
