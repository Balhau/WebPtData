package org.pt.pub.data.sources.quotes.brainyquote.domain;

import org.pt.pub.data.sources.domain.AbstractData;

/**
 *
 * Created by vitorfernandes on 10/24/15.
 */
public class Topic extends AbstractData{
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
