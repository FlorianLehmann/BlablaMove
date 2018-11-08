package fr.unice.request;

import fr.unice.model.Address;
import fr.unice.model.Date;
import fr.unice.model.Deliverable;
import fr.unice.model.Dimension;

import java.util.List;

public class RelocationRequest {

    private Address addressArrival;
    private Address addressDeparture;
    private Date startDate;
    private Date endDate;
    private Dimension dimension;


    public RelocationRequest(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, Dimension dimension) {
        this.addressArrival = addressArrival;
        this.addressDeparture = addressDeparture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dimension = dimension;
    }

    public RelocationRequest() {
    }

    public Address getAddressArrival() {
        return addressArrival;
    }

    public void setAddressArrival(Address addressArrival) {
        this.addressArrival = addressArrival;
    }

    public Address getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(Address addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
