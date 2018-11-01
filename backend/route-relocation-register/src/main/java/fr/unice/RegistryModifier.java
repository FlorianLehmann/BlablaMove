package fr.unice;

import fr.unice.model.Address;
import fr.unice.model.Date;
import fr.unice.model.Deliverable;
import fr.unice.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistryModifier {

    public void addRoute(Address addressArrival, Address addressDeparture, Date startDate, Date endDate, List<Deliverable> deliverables, User user);
}
