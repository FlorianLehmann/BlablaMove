package polytech.si5.al.mobile.business;

import java.util.Calendar;

public class Relocation {

    private String from;
    private String to;

    private int volume;

    private Calendar departure;
    private Calendar arrival;

    public Relocation(String from, String to, int volume, Calendar departure, Calendar arrival) {
        this.from = from;
        this.to = to;
        this.volume = volume;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getVolume() {
        return volume;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public Calendar getArrival() {
        return arrival;
    }
}
