package fr.unice.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

import fr.unice.model.*;

public interface RequestRelocationREST {

    @Path("/relocations")
    @POST
    Response createRelocation(String body);

}