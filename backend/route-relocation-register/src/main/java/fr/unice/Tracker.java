package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;
import fr.unice.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Tracker {

    public List<Relocation> getRelocations(User user);

    public List<Route> getRoutes(User user);

}
