package fr.unice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.RegistryModifier;
import fr.unice.model.Address;
import fr.unice.model.Date;
import fr.unice.model.Deliverable;
import fr.unice.model.User;
import fr.unice.request.RelocationRequest;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/rest")
public class RequestRelocationRESTImpl implements RequestRelocationREST {

    @EJB
    private RegistryModifier registryModifier;


    @Override
    public Response createRelocation(String body) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RelocationRequest relocationRequest = objectMapper.readValue(body, RelocationRequest.class);
            System.out.println(relocationRequest);

            registryModifier.addRoute(relocationRequest.getAddressArrival(),
                    relocationRequest.getAddressDeparture(),
                    relocationRequest.getStartDate(),
                    relocationRequest.getEndDate(),
                    relocationRequest.getDeliverables(),
                    new User("toto"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }
}
