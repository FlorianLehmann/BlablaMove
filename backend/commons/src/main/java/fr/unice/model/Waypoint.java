package fr.unice.model;

import javax.persistence.*;

@Embeddable
public class Waypoint {


    private String city;

    public Waypoint(String city, Route route) {
        this.city = city;
    }

    public Waypoint() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
