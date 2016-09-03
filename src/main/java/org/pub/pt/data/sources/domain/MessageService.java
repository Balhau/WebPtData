package org.pub.pt.data.sources.domain;

/**
 * Interface for all the scrappers that are related with the quote concept. This is mainly to enable services to
 * build sets of different services and at the same time being able to query any service for a random quote.
 * Created by vitorfernandes on 10/25/15.
 */
public interface MessageService {
    /**
     * When called the service will return a random message with the data being associated of the domain
     * of the service
     * @return
     * @throws Exception
     */
    Message getMessage() throws Exception;
}
