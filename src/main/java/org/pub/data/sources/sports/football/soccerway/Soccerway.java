package org.pub.data.sources.sports.football.soccerway;

import org.pub.data.sources.sports.football.soccerway.domain.Coach;
import org.pub.data.sources.sports.football.soccerway.domain.DCompetition;
import org.pub.data.sources.sports.football.soccerway.domain.Player;
import org.pub.data.sources.sports.football.soccerway.domain.TeamSeason;

import java.util.List;

/**
 * This class will implement a scrapper for the Soccerway website.
 * Created by vitorfernandes on 9/22/16.
 */
public class Soccerway {
    private static final String HOSTNAME="http://pt.soccerway.com/";
    private static final String URL_PATTERN_TEAM = HOSTNAME+"a/block_team_squad?block_id=page_team_1_block_team_squad_10&callback_params={\"team_id\":\"%s\"}&action=changeSimpleSquadSeason&params={\"season_id\":\"%s\"}";

    public Soccerway(){

    }

    /**
     * Return a pattern for team info url requests
     * @param teamId
     * @param seasonId
     * @return
     */
    private String getTeamURLPattern(int teamId,int seasonId){
        return String.format(URL_PATTERN_TEAM,teamId,seasonId);
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

    public TeamSeason getTeamSeason(int team, int season){
        return null;
    }

    /**
     * This will return a list of competition pointers by type of competition
     * @return
     */
    public List<DCompetition> getCompetitions(){
        return null;
    }
}

