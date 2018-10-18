package fr.unice.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

import fr.unice.model.*;

public interface RequestRelocationREST {

    @Path("/relocations")
    @POST
    void upload(@QueryParam("addressArrival") Address addressArrival ,
                @QueryParam("addressDeparture") Address addressDeparture,
                @QueryParam("startDate") Date startDate,
                @QueryParam("endDate") Date endDate,
                @QueryParam("deliverables") List<Deliverable> deliverables);

}