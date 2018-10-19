package fr.unice.model;

import java.util.Date;
import java.util.List;

public class Relocation {

    private Address addressArrival;
    private Address addressDeparture;
    private Date startDate;
    private Date endDate;
    private List<Deliverable> deliverables;

    public Relocation(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, List<Deliverable> deliverables) {
        this.addressArrival = addressArrival;
        this.addressDeparture = addressDeparture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deliverables = deliverables;
    }

    public Relocation() {
    }

    public Address getAddressArrival() {
        return addressArrival;
    }

    public Address getAddressDeparture() {
        return addressDeparture;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Deliverable> getDeliverables() {
        return deliverables;
    }
}
