package fr.unice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Relocation {
    @Id
    @GeneratedValue
    private int id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="zipcode", column = @Column(name = "arrival_zipcode")),
            @AttributeOverride(name="street", column = @Column(name = "arrival_street")),
            @AttributeOverride(name="city", column = @Column(name = "arrival_city"))
    })
    private Address addressArrival;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="zipcode", column = @Column(name = "departure_zipcode")),
            @AttributeOverride(name="street", column = @Column(name = "departure_street")),
            @AttributeOverride(name="city", column = @Column(name = "departure_city"))
    })
    private Address addressDeparture;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="day", column = @Column(name = "start_day")),
            @AttributeOverride(name="month", column = @Column(name = "start_month")),
            @AttributeOverride(name="year", column = @Column(name = "start_year"))
    })
    private Date startDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="day", column = @Column(name = "end_day")),
            @AttributeOverride(name="month", column = @Column(name = "end_month")),
            @AttributeOverride(name="year", column = @Column(name = "end_year"))
    })
    private Date endDate;
    private Dimension dimension;

    @OneToOne
    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    private Route route;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Relocation(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, Dimension dimension, User user) {
        this.addressArrival = addressArrival;
        this.addressDeparture = addressDeparture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dimension = dimension;
        this.user = user;
    }

    public Relocation() {
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
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

    public Dimension getDimension() {
        return dimension;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddressArrival(Address addressArrival) {
        this.addressArrival = addressArrival;
    }

    public void setAddressDeparture(Address addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDeliverables(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relocation that = (Relocation) o;
        return id == that.id &&
                Objects.equals(addressArrival, that.addressArrival) &&
                Objects.equals(addressDeparture, that.addressDeparture) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(deliverables, that.deliverables) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressArrival, addressDeparture, startDate, endDate, deliverables, user);
    }*/
}
