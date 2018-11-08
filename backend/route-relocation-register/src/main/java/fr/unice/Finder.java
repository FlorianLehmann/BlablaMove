package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;

import javax.ejb.Local;
import java.util.List;
import javax.ejb.Local;

@Local
public interface Finder {

    public List<Relocation> getUnassignedRelocation();

    public List<Route> getUnassignedRoute();

}
