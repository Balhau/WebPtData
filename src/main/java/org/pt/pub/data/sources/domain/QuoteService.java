package org.pt.pub.data.sources.domain;

/**
 * Interface for all the scrappers that are related with the quote concept. This is mainly to enable services to
 * build sets of different services and at the same time being able to query any service for a random quote.
 * Created by vitorfernandes on 10/25/15.
 */
public interface QuoteService {
    Quote getQuote();
}
