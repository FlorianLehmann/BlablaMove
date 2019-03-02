package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Stateless
public class Schedule implements Scheduler {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void schedule(List<Route> routes, List<Relocation> relocations) {
        for (Relocation relocation : relocations) {
            for (Route route : routes) {

                relocation = entityManager.merge(relocation);

                route = entityManager.merge(route);
                if ( route.getDimension().getVolume() >= relocation.getDimension().getVolume() ){
                    relocation.setRoute(route);
                    route.setAssigned(true);
                    entityManager.merge(relocation);
                    entityManager.merge(route);
                }
            }
        }
    }
}
