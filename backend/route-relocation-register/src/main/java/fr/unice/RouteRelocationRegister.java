package fr.unice;

import fr.unice.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class RouteRelocationRegister implements RegistryModifier, Tracker{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRelocation(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, List<Deliverable> deliverables, User user) {
        Relocation relocation = new Relocation(addressArrival, addressDeparture, startDate, endDate, deliverables, user);
        entityManager.merge(relocation);
    }

    @Override
    public void addRoute(Date date, List<Waypoint> waypoints, Volume volume, User user) {
        Route route = new Route(date, waypoints, volume, user);
        entityManager.merge(route);
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
}
