package org.pt.pub.data.sources.domain;

/**
 * Created by vitorfernandes on 10/25/15.
 */
public class Quote {
    private final String quote;
    private final String author;

    public Quote(String quote,String author){
        this.quote=quote;this.author=author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
