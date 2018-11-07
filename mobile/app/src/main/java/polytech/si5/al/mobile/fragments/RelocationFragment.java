package polytech.si5.al.mobile.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import org.json.JSONException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.requests.JSONHelper;

/**
 * Created by Enzo on 25/10/2018.
 *
 */

public class RelocationFragment extends AbstractReservationFragment {

    private Calendar arrivalCalendar;

    public RelocationFragment(){

    }

    public static RelocationFragment newInstance(){
        RelocationFragment fragment = new RelocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentId() {
        return R.layout.relocation_fragment;
    }

    @Override
    protected void setupArgsFromView(View rootView) {
        super.setupArgsFromView(rootView);

        CalendarView view = rootView.findViewById(R.id.arrival_date);

        view.setOnDateChangeListener((view1, year, month, day) -> this.arrivalCalendar = new GregorianCalendar( year, month, day));
    }

    @Override
    protected String getEndpoint() {
        return "relocations";
    }

    @Override
    protected String getContentFromFragment() throws JSONException {
        return new JSONHelper().buildRelocation(this.extractText(fromPointText), this.extractText(this.toPointText), this.departureCalendar, this.arrivalCalendar).toString();
    }

    @Override
    public boolean isValidRequest() {
        return super.isValidRequest() && arrivalCalendar.after(this.departureCalendar);
    }
}
