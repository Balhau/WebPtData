package org.pub.pt.data.sources.rodonorte.domain;


/**
 * Created by vitorfernandes on 11/5/16.
 */
public class Ride {

    public static class RideBuilder{
        private String origin;
        private String destination;
        private String dateOrigin;
        private String dateArrival;
        private float price;

        private RideBuilder(){
        }

        public RideBuilder Origin(String origin) {
            this.origin = origin;
            return this;
        }

        public RideBuilder Destination(String destination) {
            this.destination = destination;
            return this;
        }

        public RideBuilder DateOrigin(String dateOrigin) {
            this.dateOrigin = dateOrigin;
            return this;
        }

        public RideBuilder DateArrival(String dateArrival) {
            this.dateArrival = dateArrival;
            return this;
        }

        public RideBuilder Price(float price) {
            this.price = price;
            return this;
        }

        public Ride build(){
            return new Ride(origin,destination,dateOrigin,dateArrival,price);
        }

        public static RideBuilder newBuilder(){
            return new  RideBuilder();
        }
    }

    private final String origin;
    private final String destination;
    private final String dateOrigin;
    private final String dateArrival;
    private final float price;

    public Ride(String origin,String destination,String dateOrigin,String dateArrival,float price){
        this.origin=origin;this.destination=destination;
        this.dateOrigin=dateOrigin;this.dateArrival=dateArrival;
        this.price=price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOrigin() {
        return dateOrigin;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public float getPrice() {
        return price;
    }
}
