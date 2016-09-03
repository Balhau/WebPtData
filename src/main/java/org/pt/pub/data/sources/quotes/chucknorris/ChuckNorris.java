package org.pt.pub.data.sources.quotes.chucknorris;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.AbstractDataSource;
import org.pt.pub.data.sources.domain.Message;
import org.pt.pub.data.sources.domain.MessageService;
import org.pt.pub.data.utilities.Utils;
import org.pt.pub.global.configs.GlobalConfigs;
import org.pt.pub.global.utils.DomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we will implement the scrapper for the chucknorris jokes
 * Created by vitorfernandes on 10/11/15.
 */
public class ChuckNorris extends AbstractDataSource implements MessageService {

    private final String CHUCK_URL_BASE="http://www.chucknorrisfacts.com";
    private final String SEARCH_PATH=CHUCK_URL_BASE+"/search/node/%s%s";
    private final String PAGINATED_PATH=CHUCK_URL_BASE+"/all-chuck-norris-facts%s";

    public ChuckNorris(){

    }

    /**
     * Get the chuck norris facts in a paginated way
     * @param number
     */
    public List<Message> getFacts(int number) throws Exception{
        List<Message> facts=new ArrayList<>();
        Connection cn= DomUtils.get(getPaginatedPath(number));
        Document doc=cn.get();
        Elements items=doc.getElementsByClass("item-list").get(0).getElementsByTag("a");
        for(Element el : items){
            facts.add(new Message(el.text(),"Chuck Norris Fact"));
        }
        return facts;
    }

    public Message getMessage() throws Exception{
        List<Message> pageFacts=getFacts((int) Math.floor(Math.random() * 20));
        return Utils.pickRandom(pageFacts);
    }

    public List<Message> getFacts(String searchKey,int number){
        List<Message> facts=new ArrayList<>();
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
