package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.Optional;

/**
 * Created by vitorfernandes on 9/22/16.
 */
public class Player extends Person{
    private final Optional<PlayerPosition> position;
    private final int height;
    private final int weight;
    public Player(
            String firstName,String lastName,String nacionality,
            String dateOfBirth,int age,String birthCountry,String birthPlace,
            String position,int height,int weight
            ){
        super(firstName,lastName,nacionality,dateOfBirth,age,birthCountry,birthPlace);
        this.position=PlayerPosition.fromDescription(position);
        this.height=height;
        this.weight=weight;
    }
}
