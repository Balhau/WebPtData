package org.pub.data.sources.sports.football.soccerway.domain;

import java.util.Optional;

/**
 * Created by vitorfernandes on 9/22/16.
 */
public class Player extends Person{

    public class PlayerBuilder extends PersonBuilder{

        private String position;
        private int height;
        private int weight;

        public PlayerBuilder(){
            super();
        }

        public PlayerBuilder Position(String position){
            this.position=position;
            return this;
        }

        public PlayerBuilder Height(int height){
            this.height=height;
            return this;
        }

        public PlayerBuilder Weight(int weight){
            this.weight=weight;
            return this;
        }

        public Player build(){
            return new Player(this.url,this.firstName,this.lastName,this.nacionality,
                    this.dateOfBirth,this.age,this.birthCountry,this.birthPlace,this.position,
                    this.height,this.weight);
        }

    }

    private final Optional<PlayerPosition> position;
    private final int height;
    private final int weight;
    public Player(String url,
            String firstName,String lastName,String nacionality,
            String dateOfBirth,int age,String birthCountry,String birthPlace,
            String position,int height,int weight
            ){
        super(url,firstName,lastName,nacionality,dateOfBirth,age,birthCountry,birthPlace);
        this.position=PlayerPosition.fromDescription(position);
        this.height=height;
        this.weight=weight;
    }
}
