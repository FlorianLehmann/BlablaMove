package fr.unice.request;

import fr.unice.model.Address;
import fr.unice.model.Date;
import fr.unice.model.Deliverable;

import java.util.List;

public class RelocationRequest {

    private Address addressArrival;
    private Address addressDeparture;
    private Date startDate;
    private Date endDate;
    private List<Deliverable> deliverables;


    public RelocationRequest(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, List<Deliverable> deliverables) {
        this.addressArrival = addressArrival;
        this.addressDeparture = addressDeparture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deliverables = deliverables;
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

    public List<Deliverable> getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(List<Deliverable> deliverables) {
        this.deliverables = deliverables;
    }

    @Override
    public String toString() {
        return "RelocationRequest{" +
                "addressArrival=" + addressArrival +
                ", addressDeparture=" + addressDeparture +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", deliverables=" + deliverables +
                '}';
    }
}
