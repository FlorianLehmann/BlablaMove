package fr.unice.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

import fr.unice.model.*;

@Produces({"application/json"})
public interface declareREST {

    @Path("/routes")
    @POST
    void declareRoute(@QueryParam("date") Date date, List<Waypoint> waypoints, Volume volume);

}
