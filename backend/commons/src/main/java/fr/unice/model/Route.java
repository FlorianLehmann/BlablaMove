package fr.unice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private int id;

    private Date date;
    private List<Waypoint> waypoints;
    private Volume volume;
    private User user;

    public Route(Date date, List<Waypoint> waypoints, Volume volume, User user) {
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

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
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
