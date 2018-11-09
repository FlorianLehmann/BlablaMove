package polytech.si5.al.mobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import polytech.si5.al.mobile.CsteStringApp;
import polytech.si5.al.mobile.R;

public class FragmentSettings extends Fragment {

    public FragmentSettings(){

    }

    public static FragmentSettings newInstance(){
        FragmentSettings fragment = new FragmentSettings();
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
        View rootView = inflater.inflate(R.layout.fragment_server_settings, container, false);

        EditText textBase = rootView.findViewById(R.id.serverBase);
        EditText textEstimation = rootView.findViewById(R.id.serverEstimation);

        textBase.setText(CsteStringApp.SERVER_BASE_ADDRESS);
        textEstimation.setText(CsteStringApp.SERVER_BASE_ADDRESS);

        Button buttonBase = rootView.findViewById(R.id.server_base_save);
        Button buttonEstimation = rootView.findViewById(R.id.server_estimation_save);

        buttonBase.setOnClickListener((view) -> {
            if(!extractText(textBase).isEmpty()){
                CsteStringApp.SERVER_BASE_ADDRESS = extractText(textBase);
            }
        });


        return rootView;
    }

    protected String extractText(EditText fromPointText) {
        return fromPointText.getText().toString();
    }

}
