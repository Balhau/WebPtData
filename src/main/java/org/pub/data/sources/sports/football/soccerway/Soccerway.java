package org.pub.data.sources.sports.football.soccerway;

import org.pub.data.sources.sports.football.soccerway.domain.Coach;
import org.pub.data.sources.sports.football.soccerway.domain.Player;

/**
 * This class will implement a scrapper for the Soccerway website.
 * Created by vitorfernandes on 9/22/16.
 */
public class Soccerway {
    private static final String HOSTNAME="http://pt.soccerway.com/";

    public Soccerway(){

    }

    /**
     * This will retrieve player information from a provided url endpoint
     * @param url
     * @return
     */
    public Player getPlayer(String url){
        return null;
    }

    /**
     * This will retrieve coach information from a provided url endpoint
     * @param url
     * @return
     */
    public Coach getCoach(String url){
        return null;
    }
}

