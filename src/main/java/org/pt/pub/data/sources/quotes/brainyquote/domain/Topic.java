package org.pt.pub.data.sources.quotes.brainyquote.domain;

import org.pt.pub.data.sources.domain.AbstractData;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Created by vitorfernandes on 10/24/15.
 */
public class Topic{
    private final String name;
    private final String url;

    public Topic(String name,String url){
        this.name=name;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
