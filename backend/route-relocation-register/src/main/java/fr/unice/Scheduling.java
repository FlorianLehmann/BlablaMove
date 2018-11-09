package fr.unice;

import fr.unice.model.Relocation;
import fr.unice.model.Route;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
@Startup
public class Scheduling {

    @EJB
    private Finder finder;

    @EJB
    private Scheduler scheduler;

    public Scheduling() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scheduleDeliveries();
            }
        }, 5000, 5000);
    }

    public void scheduleDeliveries() {
        scheduler.schedule(finder.getUnassignedRoute(), finder.getUnassignedRelocation());
    }

}
