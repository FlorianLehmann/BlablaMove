package fr.unice.request;

import fr.unice.model.Date;
import fr.unice.model.Volume;
import fr.unice.model.Waypoint;

import java.util.List;

public class RouteDeclaration {

    private Date date;
    private List<Waypoint> waypoints;
    private Volume volume;

    public RouteDeclaration(Date date, List<Waypoint> waypoints, Volume volume) {
        this.date = date;
        this.waypoints = waypoints;
        this.volume = volume;
    }

    public RouteDeclaration() {
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
}
