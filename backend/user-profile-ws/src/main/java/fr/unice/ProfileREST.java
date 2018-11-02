package fr.unice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface ProfileREST {

    @Path("/relocations")
    @GET
    public Response getRelocations();

    @Path("/routes")
    @GET
    public Response getRoutes();
}
