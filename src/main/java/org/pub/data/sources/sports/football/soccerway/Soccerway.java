package org.pub.data.sources.sports.football.soccerway;


import org.pub.data.sources.sports.football.soccerway.domain.HostDomain;

/**
 * This class will implement a scrapper for the Soccerway website.
 * Created by balhau on 9/22/16.
 */
public class Soccerway {
    /**
     * Soccerway subdomain
     */
    private final HostDomain domain;

    /**
     * To use Soccerway scraper you need to provide the subdomain which you want target
     *
     * @param domain {@link HostDomain} enumeration
     */
    public Soccerway(final HostDomain domain) {
        this.domain = domain;
    }
}

