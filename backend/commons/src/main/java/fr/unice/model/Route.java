package fr.unice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Waypoint> waypoints = new ArrayList<>();
    private Dimension dimension;

    private User user;
    private boolean isAssigned;


    public Route(Date date, Collection<Waypoint> waypoints, Dimension dimension, User user) {
        this.date = date;
        this.waypoints = waypoints;
        this.dimension = dimension;
        this.user = user;
        this.isAssigned = false;
    }


    public Route() {
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Collection<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
