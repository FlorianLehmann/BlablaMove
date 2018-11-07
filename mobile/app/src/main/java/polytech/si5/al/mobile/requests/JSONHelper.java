package polytech.si5.al.mobile.requests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class JSONHelper {

    public JSONObject buildRelocation(String from, String to, Calendar departure, Calendar arrival) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("startDate", this.exportCalendarToJSON(departure));
        res.accumulate("endDate", this.exportCalendarToJSON(arrival));
        res.accumulate("addressDeparture", this.exportAddressToJSON(from));
        res.accumulate("addressArrival", this.exportAddressToJSON(to));
        res.accumulate("deliverables", this.exportArrayToDeliverables());

        return res;
    }

    public JSONObject buildProposal(String from, String to, Calendar departure, int volume) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("date", this.exportCalendarToJSON(departure));
        res.accumulate("volume", this.exportVolumeToCalendar(volume));
        res.accumulate("waypoints", this.exportWaypointsToJSON(from, to));

        return res;
    }

    private JSONArray exportWaypointsToJSON(String from, String to) throws JSONException {
        JSONArray res = new JSONArray();

        res.put(this.exportAddressToJSON(from));
        res.put(this.exportAddressToJSON(to));

        return res;
    }

    private JSONObject exportVolumeToCalendar(int volume) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("height", volume);
        res.accumulate("width", volume);
        res.accumulate("depth", volume);

        return res;
    }

    private JSONArray exportArrayToDeliverables() {
        return new JSONArray();
    }

    private JSONObject exportAddressToJSON(String from) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("city", from);

        return res;
    }

    private JSONObject exportCalendarToJSON(Calendar arrival) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("day", arrival.get(Calendar.DAY_OF_MONTH));
        res.accumulate("month", arrival.get(Calendar.MONTH));
        res.accumulate("year", arrival.get(Calendar.YEAR));

        return res;
    }
}
