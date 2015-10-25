package org.pt.pub.data.sources.quotes.chucknorris;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.AbstractDataSource;
import org.pt.pub.data.sources.domain.Quote;
import org.pt.pub.data.sources.domain.QuoteService;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we will implement the scrapper for the chucknorris jokes
 * Created by vitorfernandes on 10/11/15.
 */
public class ChuckNorris extends AbstractDataSource implements QuoteService{

    private final String CHUCK_URL_BASE="http://www.chucknorrisfacts.com";
    private final String SEARCH_PATH=CHUCK_URL_BASE+"/search/node/%s%s";
    private final String PAGINATED_PATH=CHUCK_URL_BASE+"/all-chuck-norris-facts%s";

    public ChuckNorris(){

    }

    /**
     * Get the chuck norris facts in a paginated way
     * @param number
     */
    public List<Quote> getFacts(int number) throws Exception{
        List<Quote> facts=new ArrayList<>();
        String path=getPaginatedPath(number);
        System.out.println(path);
        Connection cn= Jsoup.connect(getPaginatedPath(number)).userAgent(GlobalConfigs.USER_AGENT);
        Document doc=cn.get();
        Elements items=doc.getElementsByClass("item-list").get(0).getElementsByTag("a");
        for(Element el : items){
            facts.add(new Quote(el.text(),"Chuck Norris Fact"));
        }
        return facts;
    }

    public Quote getQuote(){

        return null;
    }

    public List<Quote> getFacts(String searchKey,int number){
        List<Quote> facts=new ArrayList<>();
        return facts;
    }

    /**
     * This will return the url for the paginated path given the number of the page
     * @param numberPage {link @Integer} number of the path
     * @return
     */
    private String getPaginatedPath(int numberPage){
        return String.format(PAGINATED_PATH,
                (numberPage==0) ? "" : "?page="+numberPage
        );
    }
}
