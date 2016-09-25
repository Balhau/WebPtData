package org.pub.data.sources.sports.football.soccerway.domain;

import org.pub.pt.data.utilities.Builder;

import java.util.Optional;

/**
 * Base class for extended person objects, like Coach and Player
 * Created by vitorfernandes on 9/22/16.
 */
public abstract class Person {
    private final String url;
    private final String firstName;
    private final String lastName;
    private final String nacionality;
    private final String dateOfBirth;
    private final int age;
    private final String birthCountry;
    private final String birthPlace;

    public static abstract class PersonBuilder{

        protected String url;
        protected String firstName;
        protected String lastName;
        protected String nacionality;
        protected String dateOfBirth;
        protected int age;
        protected String birthCountry;
        protected String birthPlace;

        public PersonBuilder(){

        }

        public PersonBuilder Url(String url){
            this.url=url;
            return this;
        }

        public PersonBuilder FirstName(String firstName){
            this.firstName=firstName;
            return this;
        }

        public PersonBuilder LastName(String lastName){
            this.lastName=lastName;
            return this;
        }

        public PersonBuilder Nacionality(String nacionality){
            this.nacionality=nacionality;
            return this;
        }

        public PersonBuilder DateOfBirth(String dateOfBirth){
            this.dateOfBirth=dateOfBirth;
            return this;
        }

        public PersonBuilder Age(int age){
            this.age=age;
            return this;
        }

        public PersonBuilder BirthCountry(String birdCountry){
            this.birthCountry=birdCountry;
            return this;
        }

        public PersonBuilder BirthPlace(String birthPlace){
            this.birthPlace=birthPlace;
            return this;
        }
    }

    public Person(String url,
            String firstName,String lastName,String nacionality,
            String dateOfBirth,int age,String birthCountry,String birthPlace){
        this.url=url;
        this.firstName=firstName;
        this.lastName=lastName;
        this.nacionality=nacionality;
        this.dateOfBirth=dateOfBirth;
        this.age=age;
        this.birthCountry=birthCountry;
        this.birthPlace=birthPlace;
    }

    public String getUrl() {
        return url;
    }

    public String getNacionality() {
        return nacionality;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}
