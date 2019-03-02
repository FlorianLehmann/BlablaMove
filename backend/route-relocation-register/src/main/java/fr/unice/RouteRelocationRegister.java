package fr.unice;

import fr.unice.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RouteRelocationRegister implements RegistryModifier, Tracker, Finder{

    @PersistenceContext
    private EntityManager entityManager;

    public RouteRelocationRegister() {
    }

    @Override
    public void addRelocation(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, Dimension dimension, User user) {
        Relocation relocation = new Relocation(addressArrival, addressDeparture, startDate, endDate, dimension, user);
        entityManager.persist(relocation);
    }

    @Override
    public void addRoute(Date date, List<Waypoint> waypoints, Dimension dimension, User user) {
        Route route = new Route(date, waypoints, dimension, user);
        entityManager.persist(route);
    }

    @Override
    public List<Relocation> getRelocations(User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Relocation> criteria = builder.createQuery(Relocation.class);
        Root<Relocation> root = criteria.from(Relocation.class);

        criteria.select(root);
        TypedQuery<Relocation> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Route> getRoutes(User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> criteria = builder.createQuery(Route.class);
        Root<Route> root = criteria.from(Route.class);

        criteria.select(root);
        TypedQuery<Route> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Relocation> getUnassignedRelocation() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Relocation> criteria = builder.createQuery(Relocation.class);
        Root<Relocation> root = criteria.from(Relocation.class);

        criteria.select(root);
        TypedQuery<Relocation> query = entityManager.createQuery(criteria);

        List<Relocation> routes = new ArrayList<>();
        for (Relocation relocation : query.getResultList()) {
            if (relocation.getRoute() == null)
                routes.add(relocation);
        }

        return routes;
    }

    @Override
    public List<Route> getUnassignedRoute() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> criteria = builder.createQuery(Route.class);
        Root<Route> root = criteria.from(Route.class);

        criteria.select(root);
        TypedQuery<Route> query = entityManager.createQuery(criteria);

        List<Route> routes = new ArrayList<>();
        for (Route route : query.getResultList()) {
            if (!route.isAssigned())
                routes.add(route);
        }

        return routes;
    }
}
