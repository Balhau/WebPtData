package org.pt.pub.data.sources.quotes.brainyquote;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.Message;
import org.pt.pub.data.sources.domain.MessageService;
import org.pt.pub.data.sources.quotes.brainyquote.domain.Author;
import org.pt.pub.data.sources.quotes.brainyquote.domain.Topic;
import org.pt.pub.data.utilities.Utils;
import org.pub.global.utils.DomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will fetch information from brainyquote site
 * Created by vitorfernandes on 10/24/15.
 */
public class BrainyMessage implements MessageService {
    public static String BRAINY_QUOTE_BASE="http://www.brainyquote.com";
    public static String BRAINY_TOPICS=BRAINY_QUOTE_BASE+"/quotes/topics.html";
    public static String BRAINY_AUTHORS=BRAINY_QUOTE_BASE+"/quotes/favorites.html";

    public List<Topic> getTopics() throws Exception{
        Connection con= DomUtils.get(BRAINY_TOPICS);
        Document doc=con.get();

        Elements tables = doc.getElementsByTag("table");

        tables.remove(0);tables.remove(0);

        return getTopicsFromTables(tables);
    }

    public List<Author> getAuthors() throws Exception{
        List<Author> authors=new ArrayList<>();
        Connection con = DomUtils.get(BRAINY_AUTHORS);
        Document doc = con.get();
        Elements eAuthors=doc.getElementsByClass("bqLn");
        for(Element eauth : eAuthors){
            Element anchor=eauth.getAllElements().get(0);
            authors.add(new Author(anchor.text(),anchor.attr("href")));
        }
        return authors;
    }

    /**
     * This will retrieve a list of quotes based on the topic and a page number you provide
     * @param topic This is the topic by which you want your quotes
     * @param page The number of the page
     * @return A List of Message objects with all the quotes that are stored in the page
     * @throws Exception
     */
    public List<Message> getQuotes(String topic,int page) throws Exception{
        List<Message> messages =new ArrayList<>();
        List<Topic> topics=getTopics();
        String url=buildTopicPageURL(topics, topic, page);
        System.out.println(url);
        Connection con = DomUtils.get(url);
        Document doc = con.get();
        Elements entries=doc.getElementsByClass("boxyPaddingBig");
        for(Element entry : entries){
            Elements anchors=entry.getElementsByTag("a");
            messages.add(new Message(
                    anchors.get(0).text(),
                    anchors.get(1).text()
            ));
        }
        return messages;
    }

    private String buildTopicPageURL(List<Topic> topics,String topic,int page){
        String url="";
        if(page==0){
            return getUrlFromTopic(topics,topic);
        }
        return getUrlFromTopic(topics,topic).split(".html")[0]+page+".html";
    }

    private String getUrlFromTopic(List<Topic> topics, String topic){
        for(Topic t : topics){
            if(t.getName().toLowerCase().equals(topic.toLowerCase())){
                return t.getUrl();
            }
        }
        return null;
    }

    public Message getMessage() throws Exception{
        List<Topic> topics=getTopics();
        Topic topic = Utils.pickRandom(topics);
        List<Message> messages =getQuotes(topic.getName(),(int)(Math.random()*40));
        return Utils.pickRandom(messages);
    }

    private List<Topic> getTopicsFromTables(Elements tables){
        List<Topic> topics=new ArrayList<>();
        Elements links;
        for(Element table : tables){
            links = table.getElementsByTag("a");
            for(Element link : links){
                topics.add(new Topic(
                        link.text(),
                        BRAINY_QUOTE_BASE+link.attr("href")
                ));
            }
        }
        return topics;
    }
}
