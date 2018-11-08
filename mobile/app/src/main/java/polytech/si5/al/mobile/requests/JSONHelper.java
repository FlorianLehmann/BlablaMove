package polytech.si5.al.mobile.requests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import polytech.si5.al.mobile.business.Relocation;
import polytech.si5.al.mobile.business.Travel;

public class JSONHelper {

    public JSONObject buildRelocation(String from, String to, Calendar departure, Calendar arrival) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("startDate", this.exportCalendarToJSON(departure));
        res.accumulate("endDate", this.exportCalendarToJSON(arrival));
        res.accumulate("addressDeparture", this.exportAddressToJSON(from));
        res.accumulate("addressArrival", this.exportAddressToJSON(to));
        res.accumulate("dimension", this.exportVolumeToJSON(5));

        return res;
    }

    public JSONObject buildProposal(String from, String to, Calendar departure, int volume) throws JSONException {
        JSONObject res = new JSONObject();

        res.accumulate("date", this.exportCalendarToJSON(departure));
        res.accumulate("dimension", this.exportVolumeToJSON(volume));
        res.accumulate("waypoints", this.exportWaypointsToJSON(from, to));

        return res;
    }

    private JSONArray exportWaypointsToJSON(String from, String to) throws JSONException {
        JSONArray res = new JSONArray();

        res.put(this.exportAddressToJSON(from));
        res.put(this.exportAddressToJSON(to));

        return res;
    }

    private JSONObject exportVolumeToJSON(int volume) throws JSONException {
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

    private Calendar retrieveCalendarFromJSON(JSONObject date) throws JSONException {
        return new GregorianCalendar(date.getInt("year"), date.getInt("month"), date.getInt("day"));
    }

    private String retrieveLocationFromJSON(JSONObject addressDeparture) throws JSONException {
        return addressDeparture.getString("city");
    }

    public List<Relocation> convertRelocationsFromJSON(String rawResult) {
        List<Relocation> res = new ArrayList<>();

        try {
            JSONArray request = (JSONArray) new JSONTokener(rawResult).nextValue();

            for(int i = 0; i < request.length(); i++){
                JSONObject current = request.getJSONObject(i);

                res.add(new Relocation(
                        retrieveLocationFromJSON(current.getJSONObject("addressDeparture")),
                        retrieveLocationFromJSON(current.getJSONObject("addressArrival")),
                        5,
                        retrieveCalendarFromJSON(current.getJSONObject("startDate")),
                        retrieveCalendarFromJSON(current.getJSONObject("endDate"))
                ));
            }
        } catch (JSONException e) {
            res.add(new Relocation("Error", "Can't convert JSON request", 400, new GregorianCalendar(), new GregorianCalendar()));
        }

        return res;
    }

    public List<Travel> convertTravelFromJSON(String rawResult) {
        List<Travel> res = new ArrayList<>();

        try {
            JSONArray request = (JSONArray) new JSONTokener(rawResult).nextValue();

            for(int i = 0; i < request.length(); i++){
                JSONObject current = request.getJSONObject(i);



                res.add(new Travel(
                        retrieveLocationFromJSON(current.getJSONArray("waypoints").getJSONObject(0)),
                        retrieveLocationFromJSON(current.getJSONArray("waypoints").getJSONObject(1)),
                        retrieveVolumeFromJSON(current.getJSONObject("dimension")),
                        retrieveCalendarFromJSON(current.getJSONObject("date"))
                ));
            }
        } catch (JSONException e) {
            res.add(new Travel("Error", "Can't convert JSON request", 400, new GregorianCalendar()));
        }

        return res;
    }

    private int retrieveVolumeFromJSON(JSONObject volume) throws JSONException {
        return volume.getInt("depth") * volume.getInt("height") * volume.getInt("width");
    }

    public String getVolumeFromRequest(String rawResult) {
        try {
            JSONObject request = ((JSONObject) new JSONTokener(rawResult).nextValue()).getJSONObject("estimation");

            return request.getString("volume") + " " + request.getString("unit") + "3";
        } catch (JSONException e) {
            return "Couldn't retrieve the volume";
        }
    }
}
