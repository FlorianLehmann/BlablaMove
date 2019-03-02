package fr.unice.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface DeclareRouteREST {

    @Path("/routes")
    @POST
    Response createRoute(String body);
}
