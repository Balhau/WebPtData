package org.pub.data.sources.sports.football.soccerway.domain;

/**
 * Created by vitorfernandes on 9/22/16.
 */
public class Coach extends Person{

    public class CoachBuilder extends PersonBuilder{
        public CoachBuilder(){
            super();
        }

        public Coach build(){
            return new Coach(this.url,this.firstName,this.lastName,this.nacionality,
                    this.dateOfBirth,this.age,this.birthCountry,this.birthPlace
                    );
        }
    }

    public Coach(String url,
            String firstName,String lastName,String nacionality,
            String dateOfBirth,int age,String birthCountry,String birthPlace){
        super(url,firstName,lastName,nacionality,dateOfBirth,age,birthCountry,birthPlace);

    }
}
