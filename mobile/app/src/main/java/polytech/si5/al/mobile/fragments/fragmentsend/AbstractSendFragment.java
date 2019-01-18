package polytech.si5.al.mobile.fragments.fragmentsend;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import org.json.JSONException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.requests.AsyncSendHelper;

public abstract class AbstractSendFragment extends Fragment {

    protected EditText fromPointText;
    protected EditText toPointText;
    protected Calendar departureCalendar;

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentId(), container, false);

        setupArgsFromView(rootView);

        setupSubmitButton(rootView);

        return rootView;
    }

    protected abstract int getFragmentId();

    protected void setupArgsFromView(View rootView){
        fromPointText = rootView.findViewById(R.id.fromAddress);
        toPointText = rootView.findViewById(R.id.toAddress);
        CalendarView view = rootView.findViewById(R.id.departure_date);

        view.setOnDateChangeListener((view1, year, month, day) -> this.departureCalendar = new GregorianCalendar( year, month, day));
    }

    private void setupSubmitButton(View rootView) {
        final Button button = rootView.findViewById(R.id.relocation_button);

        button.setOnClickListener((view) -> {
            if(this.isValidRequest()){
                button.setBackgroundColor(getResources().getColor(R.color.sentParams));

                this.sendToAPI();

            } else {
                button.setBackgroundColor(getResources().getColor(R.color.wrongParams));
            }
        });
    }

    private void sendToAPI() {
        try {
            String content = getContentFromFragment();

            System.out.println(content);

            new AsyncSendHelper().execute(getEndpoint(), content);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getEndpoint();

    protected abstract String getContentFromFragment() throws JSONException;

    public boolean isValidRequest(){
        return isTextSendable(this.toPointText) && isTextSendable(this.fromPointText);
    }

    public boolean isTextSendable(EditText toTest){
        return !toTest.getText().toString().isEmpty();
    }

    protected String extractText(EditText fromPointText) {
        return fromPointText.getText().toString();
    }
}
