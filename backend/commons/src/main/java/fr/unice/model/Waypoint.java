package fr.unice.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Waypoint {

    private String city;

    public Waypoint(String city) {
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
