package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;
import fr.unice.model.User;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rest")
public class ProfileRESTImpl implements ProfileREST {

    @EJB
    private Tracker tracker;

    @Override
    public Response getRelocations() {
        List<Relocation> relocations;
        relocations = tracker.getRelocations(new User("toto"));
        return Response.ok(relocations, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Override
    public Response getRoutes() {
        List<Route> routes;
        routes = tracker.getRoutes(new User("toto"));
        return Response.ok(routes, MediaType.APPLICATION_JSON_TYPE).build();
    }

}
