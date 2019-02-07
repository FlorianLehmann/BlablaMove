package polytech.si5.al.mobile.fragments.fragmentsend;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.fragments.fragmentseek.CallableFragment;
import polytech.si5.al.mobile.requests.JSONHelper;

/**
 * Created by Enzo on 25/10/2018.
 *
 */

public class CaptureFragment extends Fragment implements CallableFragment {

    private SingleCaptureFragment singleCaptureFragment;

    private TextView volumeText;

    public CaptureFragment(){

    }

    public static CaptureFragment newInstance(){
        CaptureFragment fragment = new CaptureFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_capture_all, container, false);

        addVideoFragment();

        setupEstimateButton(rootView);

        this.volumeText = rootView.findViewById(R.id.textVolumeEstimation);

        return rootView;
    }

    private void setupEstimateButton(View rootView) {
        final Button button = rootView.findViewById(R.id.button_estimate);
    }

    private boolean isValidRequest() {
        return true;
    }

    private void addVideoFragment() {
        SingleCaptureFragment singleCaptureFragment = SingleCaptureFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_capture, singleCaptureFragment);
        transaction.commit();
    }

    @Override
    public void callbackSetter(String rawResult) {
        this.volumeText.setText(new JSONHelper().getVolumeFromRequest(rawResult));
        System.out.println(rawResult);
    }
}
