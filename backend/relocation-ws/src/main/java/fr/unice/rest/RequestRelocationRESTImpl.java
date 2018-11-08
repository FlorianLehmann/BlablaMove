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

            registryModifier.addRelocation(relocationRequest.getAddressArrival(),
                    relocationRequest.getAddressDeparture(),
                    relocationRequest.getStartDate(),
                    relocationRequest.getEndDate(),
                    relocationRequest.getDimension(),
                    new User("toto"));

        } catch (IOException e) {
            return Response.serverError().build();
        }

        return Response.ok().build();
    }
}
