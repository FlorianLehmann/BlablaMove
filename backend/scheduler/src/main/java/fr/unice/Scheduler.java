package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Scheduler {

    public void schedule(List<Route> routes, List<Relocation> relocations);

}
