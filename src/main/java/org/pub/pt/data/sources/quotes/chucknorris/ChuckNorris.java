package org.pub.pt.data.sources.quotes.chucknorris;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.domain.AbstractDataSource;
import org.pub.pt.data.sources.domain.Message;
import org.pub.pt.data.sources.domain.MessageService;
import org.pub.pt.data.utilities.Utils;
import org.pub.global.utils.DomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we will implement the scrapper for the chucknorris jokes
 * Created by vitorfernandes on 10/11/15.
 */
public class ChuckNorris extends AbstractDataSource implements MessageService {

    private final String CHUCK_URL_BASE="https://chucknorrisfacts.net";
    private final String SEARCH_PATH=CHUCK_URL_BASE+"/search/node/%s%s";
    private final String PAGINATED_PATH=CHUCK_URL_BASE+"/facts.php%s";

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
        Elements items = doc.getElementById("content").getElementsByAttributeValueContaining("style","border-top: 1px solid");
        for(Element el : items){
            String chuckMessage = el.children().get(0).text();
            facts.add(new Message(chuckMessage,"Chuck Norris Fact"));
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
