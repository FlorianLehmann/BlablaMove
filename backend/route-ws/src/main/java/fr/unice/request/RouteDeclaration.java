package fr.unice.request;

import fr.unice.model.Date;
import fr.unice.model.Dimension;
import fr.unice.model.Waypoint;

import java.util.List;

public class RouteDeclaration {

    private Date date;
    private List<Waypoint> waypoints;
    private Dimension dimension;

    public RouteDeclaration(Date date, List<Waypoint> waypoints, Dimension dimension) {
        this.date = date;
        this.waypoints = waypoints;
        this.dimension = dimension;
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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
