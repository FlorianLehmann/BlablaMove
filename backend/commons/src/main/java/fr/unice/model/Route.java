package fr.unice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Waypoint> waypoints = new ArrayList<>();
    private Volume volume;

    private User user;

    public Route(Date date, Collection<Waypoint> waypoints, Volume volume, User user) {
        this.date = date;
        this.waypoints = waypoints;
        this.volume = volume;
        this.user = user;
    }

    public Route() {
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

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
