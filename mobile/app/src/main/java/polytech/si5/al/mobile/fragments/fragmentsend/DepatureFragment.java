package polytech.si5.al.mobile.fragments.fragmentsend;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.requests.JSONHelper;

public class DepatureFragment extends AbstractSendFragment {

    private EditText spaceAvailable;

    public DepatureFragment(){

    }

    public static DepatureFragment newInstance(){
        DepatureFragment fragment = new DepatureFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentId() {
        return R.layout.proposal_fragment;
    }

    @Override
    protected void setupArgsFromView(View rootView) {
        super.setupArgsFromView(rootView);

        this.spaceAvailable = rootView.findViewById(R.id.room_left);
    }

    @Override
    protected String getEndpoint() {
        return "routes";
    }

    @Override
    protected String getContentFromFragment() throws JSONException {
        return new JSONHelper().buildProposal(this.extractText(fromPointText), this.extractText(this.toPointText), this.departureCalendar, Integer.valueOf(this.extractText(this.spaceAvailable))).toString();
    }

    @Override
    public boolean isValidRequest() {
        return super.isValidRequest() && this.isTextSendable(spaceAvailable);
    }
}
