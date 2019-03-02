package polytech.si5.al.mobile.business;

import java.util.Calendar;

public class Travel {

    private String from;
    private String to;

    private int volume;

    private Calendar departure;

    public Travel(String from, String to, int volume, Calendar departure) {
        this.from = from;
        this.to = to;
        this.volume = volume;
        this.departure = departure;
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

}
