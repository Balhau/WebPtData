package org.pub.data.sources.sports.football.soccerway;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pub.data.sources.sports.football.soccerway.domain.*;
import org.pub.global.utils.DomUtils;

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
    public Player getPlayer(String url) throws Exception{
        Document doc = DomUtils.get(url).get();
        Elements data=doc.getElementsByClass("content").get(0).getElementsByClass("clearfix").get(1).getElementsByTag("dd");
        String firstName = data.get(0).text();
        String lastName = data.get(1).text();
        String nacionality = data.get(2).text();
        String bornDate = data.get(3).text();
        int age = Integer.parseInt(data.get(4).text().trim());
        String countryBirth = data.get(5).text();
        String localBirth = data.get(6).text();
        String position = data.get(7).text();
        int height = Integer.parseInt(data.get(8).text().split("cm")[0].trim());
        int weight = Integer.parseInt(data.get(9).text().split("kg")[0].trim());
        PlayerFoot foot = PlayerFoot.fromDescription(data.get(10).text()).get();

        return ((Player.PlayerBuilder) new Player.PlayerBuilder()
                .Url(url)
                .FirstName(firstName)
                .LastName(lastName)
                .Nacionality(nacionality)
                .DateOfBirth(bornDate)
                .Age(age)
                .BirthCountry(countryBirth)
                .BirthPlace(localBirth)
        )
                .Position(position)
                .Height(height)
                .Weight(weight)
                .Foot(foot)
                .build();

    }

    /**
     * This will retrieve coach information from a provided url endpoint
     * @param url
     * @return
     */
    public Coach getCoach(String url) throws Exception{

        Document doc = DomUtils.get(url).get();
        Elements data=doc.getElementsByClass("content").get(0).getElementsByClass("clearfix").get(1).getElementsByTag("dd");
        String firstName = data.get(0).text();
        String lastName = data.get(1).text();
        String nacionality = data.get(2).text();
        String bornDate = data.get(3).text();
        int age = Integer.parseInt(data.get(4).text().trim());
        String countryBirth = data.get(5).text();
        String localBirth = data.get(6).text();

        return ((Coach.CoachBuilder) new Coach.CoachBuilder()
                .Url(url)
                .FirstName(firstName)
                .LastName(lastName)
                .Nacionality(nacionality)
                .DateOfBirth(bornDate)
                .Age(age)
                .BirthCountry(countryBirth)
                .BirthPlace(localBirth)
        ).build();
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

