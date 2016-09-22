package org.pub.data.sources.sports.football.resultdb.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitorfernandes on 9/21/16.
 */
public class Season {
    private final int year;
    private final List<String> teams;
    private final List<Result> results;

    public Season(int year){
        this.year=year;
        this.teams=new ArrayList<>();
        this.results=new ArrayList<>();
    }

    public Season(int year,List<String> teams,List<Result> results){
        this.year=year;
        this.teams=teams;
        this.results=results;
    }

    public int getYear() {
        return year;
    }

    public List<String> getTeams() {
        return teams;
    }

    public List<Result> getResults() {
        return results;
    }
}
