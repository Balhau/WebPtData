package org.pt.pub.data.sources.quotes.brainyquote;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pt.pub.data.sources.domain.Quote;
import org.pt.pub.data.sources.quotes.brainyquote.domain.Topic;
import org.pt.pub.global.configs.GlobalConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will fetch information from brainyquote site
 * Created by vitorfernandes on 10/24/15.
 */
public class BrainyQuote {
    public static String BRAINY_QUOTE_BASE="http://www.brainyquote.com";
    public static String BRAINY_TOPICS=BRAINY_QUOTE_BASE+"/quotes/topics.html";

    public List<Topic> getTopics() throws Exception{
        Connection con= Jsoup.connect(BRAINY_TOPICS).userAgent(GlobalConfigs.USER_AGENT);
        Document doc=con.get();

        Elements tables = doc.getElementsByTag("table");

        tables.remove(0);tables.remove(0);

        return getTopicsFromTables(tables);
    }

    public List<Quote> getQuotes(String topic,int page) throws Exception{
        List<Quote> quotes=new ArrayList<>();
        List<Topic> topics=getTopics();
        String url=buildTopicPageURL(topics,topic,page);
        System.out.println(url);
        Connection con = Jsoup.connect(url).userAgent(GlobalConfigs.USER_AGENT);
        Document doc = con.get();
        Elements entries=doc.getElementsByClass("boxyPaddingBig");
        for(Element entry : entries){
            Elements anchors=entry.getElementsByTag("a");
            quotes.add(new Quote(
                    anchors.get(0).text(),
                    anchors.get(1).text()
            ));
        }
        return quotes;
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

    public static void main(String[] args) throws Exception{
        BrainyQuote bq=new BrainyQuote();
        List<Topic> topics=bq.getTopics();
        bq.getQuotes(topics.get(0).getName(),2);
        topics.size();
    }
}
