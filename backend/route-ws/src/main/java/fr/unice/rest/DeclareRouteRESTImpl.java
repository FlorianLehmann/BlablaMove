package fr.unice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.RegistryModifier;
import fr.unice.model.User;
import fr.unice.request.RouteDeclaration;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/rest")
public class DeclareRouteRESTImpl implements DeclareRouteREST {

    @EJB
    private RegistryModifier registryModifier;

    @Override
    public Response createRoute(String body) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            RouteDeclaration routeDeclaration = objectMapper.readValue(body, RouteDeclaration.class);

            registryModifier.addRoute(routeDeclaration.getDate(),
                    routeDeclaration.getWaypoints(),
                    routeDeclaration.getDimension(),
                    new User("toto"));

        } catch (IOException e) {
            return Response.serverError().build();
        }

        return Response.ok().build();
    }

}
