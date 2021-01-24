package org.pub.pt.data.sources.quotes.brainyquote;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.pt.data.sources.domain.Message;
import org.pub.pt.data.sources.domain.MessageService;
import org.pub.pt.data.sources.quotes.brainyquote.domain.Author;
import org.pub.pt.data.sources.quotes.brainyquote.domain.Topic;
import org.pub.pt.data.utilities.Utils;
import org.pub.global.utils.DomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will fetch information from brainyquote site
 * Created by vitorfernandes on 10/24/15.
 */
public class BrainyMessage implements MessageService {
    public static String BRAINY_QUOTE_BASE = "http://www.brainyquote.com";
    public static String BRAINY_TOPICS = BRAINY_QUOTE_BASE + "/quotes/topics.html";
    public static String BRAINY_AUTHORS = BRAINY_QUOTE_BASE + "/quotes/favorites.html";

    public List<Topic> getTopics() throws Exception {
        Connection con = DomUtils.getHTML(BRAINY_TOPICS);
        Document doc = con.get();

        Elements tables = doc.getElementsByClass("indexContent");

        return getTopicsFromDiv(tables.get(0));
    }

    public List<Author> getAuthors() throws Exception {
        List<Author> authors = new ArrayList<>();
        Connection con = DomUtils.getHTML(BRAINY_AUTHORS);
        Document doc = con.get();
        Elements eAuthors = doc.getElementsByClass("bqLn");
        for (Element eauth : eAuthors) {
            Element anchor = eauth.getAllElements().get(0);
            authors.add(new Author(anchor.text(), anchor.attr("href")));
        }
        return authors;
    }

    /**
     * This will retrieve a list of quotes based on the topic and a page number you provide
     *
     * @param topic This is the topic by which you want your quotes
     * @param page  The number of the page
     * @return A List of Message objects with all the quotes that are stored in the page
     * @throws Exception
     */
    public List<Message> getQuotes(Topic topic, int page) throws Exception {
        List<Message> messages = new ArrayList<>();
        Connection con = DomUtils.getHTML(buildTopicPageURL(topic.getUrl(),page));
        Document doc = con.get();
        Elements entries = doc.getElementsByClass("clearfix");
        for (Element entry : entries) {
            Elements anchorsMessage=entry.getElementsByClass("oncl_q");
            Elements anchorsAuthor=entry.getElementsByClass("oncl_a");
            messages.add(new Message(
                    anchorsMessage.get(anchorsMessage.size()-1).text(),
                    anchorsAuthor.get(anchorsAuthor.size()-1).text()
            ));
        }
        return messages;
    }

    private String buildTopicPageURL(String topic, int page) {
        return topic+"_"+page;
    }

    public Message getMessage() throws Exception {
        List<Topic> topics = getTopics();
        Topic topic = Utils.pickRandom(topics);
        List<Message> messages = getQuotes(topic, (int) (Math.random() * 40));
        return Utils.pickRandom(messages);
    }

    private List<Topic> getTopicsFromDiv(Element div) {
        List<Topic> topics = new ArrayList<>();
        Elements links;
        links = div.getElementsByTag("a");
        for (Element link : links) {
            topics.add(new Topic(
                    link.text(),
                    BRAINY_QUOTE_BASE + link.attr("href")
            ));
        }
        return topics;
    }
}
