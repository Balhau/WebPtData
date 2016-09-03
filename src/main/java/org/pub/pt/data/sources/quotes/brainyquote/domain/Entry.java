package org.pub.pt.data.sources.quotes.brainyquote.domain;

/**
 * Created by vitorfernandes on 10/25/15.
 */
public abstract class Entry {
    protected final String name;
    protected final String url;

    public Entry(String name,String url){
        this.name=name;this.url=url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
