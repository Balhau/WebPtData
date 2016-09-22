package org.pub.data.sources.sports.football.soccerway.domain;

/**
 * Created by vitorfernandes on 9/22/16.
 */
public abstract class Person {
    private final String firstName;
    private final String lastName;
    private final String nacionality;
    private final String dateOfBirth;
    private final int age;
    private final String birthCountry;
    private final String birthPlace;

    public Person(
            String firstName,String lastName,String nacionality,
            String dateOfBirth,int age,String birthCountry,String birthPlace){
        this.firstName=firstName;
        this.lastName=lastName;
        this.nacionality=nacionality;
        this.dateOfBirth=dateOfBirth;
        this.age=age;
        this.birthCountry=birthCountry;
        this.birthPlace=birthPlace;
    }



}
