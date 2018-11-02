package fr.unice;

import fr.unice.model.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistryModifier {

    public void addRelocation(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, List<Deliverable> deliverables, User user);

    public void addRoute(Date date, List<Waypoint> waypoints, Volume volume, User user);

}
