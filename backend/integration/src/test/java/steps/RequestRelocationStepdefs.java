package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.unice.model.*;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestRelocationStepdefs {

    private Address departureAddress;
    private Address arrivalAddress;
    private Date startDate;
    private Date endDate;
    private List<Deliverable> deliverables = new ArrayList<>();


    @Given("^a relocation from a client named (.*)$")
    public void aClientNamed(String name) {
    }

    @And("^an address of departure : (.*), (.*), (.*)$")
    public void anAddressOfDeparture(String zipcode, String street, String city) {
        this.departureAddress = new Address(zipcode, street, city);
    }

    @And("^an address of arrival : (.*), (.*), (.*)$")
    public void anAddressOfArrival(String zipcode, String street, String city) {
        this.arrivalAddress = new Address(zipcode, street, city);
    }

    @And("^(.*)/(.*)/(.*) as the start date$")
    public void aStartDate(int day, int month, int year) {
        this.startDate = new Date(day, month, year);
    }

    @And("^(.*)/(.*)/(.*) as the end date$")
    public void aEndDate(int day, int month, int year) {
        this.endDate = new Date(day, month, year);
    }

    @When("^he confirms his relocation$")
    public void sendRequest() {
        JSONObject request = new JSONObject();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            request.put("addressArrival", objectMapper.writeValueAsString(arrivalAddress))
                    .put("addressDeparture", objectMapper.writeValueAsString(departureAddress))
                    .put("startDate", objectMapper.writeValueAsString(startDate))
                    .put("endDate", objectMapper.writeValueAsString(endDate))
                    .put("deliverables", objectMapper.writeValueAsString(deliverables));

            System.out.println("test : " + objectMapper.writeValueAsString(arrivalAddress));

            String raw = WebClient.create("http://localhost:8080/blablamove/rest/relocations")
                            .accept(MediaType.APPLICATION_JSON_TYPE)
                            .header("Content-Type", MediaType.APPLICATION_JSON)
                            .post(request.toString(), String.class);

        } catch (Exception e) {

        }

    }

    @Then("^he get (.*) relocation in his profile$")
    public void getHisRelocation(int numberOfRelocations) {
        String raw = WebClient.create("http://localhost:8080/blablamove/rest/relocations")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println(raw);

        JSONObject ans = new JSONArray(raw).getJSONObject(0);
        //assertEquals(arrivalAddress.getStreet(), ans.getJSONObject("relocation").getJSONObject("addressArrival").getString("street"));
    }

}

/**
 *
 {
 "endDate":
 {
 "day":11,
 "month":11,
 "year":2018
 },
 "addressDeparture":
 {
 "zipcode":"06410",
 "street":"930 Route des Colles",
 "city":"Biot"
 },
 "addressArrival":
 {
 "zipcode":"13009",
 "street":"163 Avenue de Luminy",
 "city":"Marseille"
 },
 "deliverables":[],
 "startDate":
 {"day":11,"month":11,"year":2018}
 }
 */
