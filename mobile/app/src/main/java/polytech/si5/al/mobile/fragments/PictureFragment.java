package polytech.si5.al.mobile.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import polytech.si5.al.mobile.R;

/**
 * Created by Enzo on 25/10/2018.
 *
 */

public class PictureFragment extends Fragment {

    private List<SinglePictureFragment> fragmentList;

    public PictureFragment(){
        fragmentList = new ArrayList<>();
    }

    public static PictureFragment newInstance(){
        PictureFragment fragment = new PictureFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_picture_all, container, false);

        //The 2 picture fragments
        addPictureFragments();

        setupEstimateButton(rootView);

        return rootView;
    }

    private void setupEstimateButton(View rootView) {
        final Button button = rootView.findViewById(R.id.button_estimate);

        button.setOnClickListener((view) -> {
            if(this.isValidRequest()){

            } else {
                button.setBackgroundColor(getResources().getColor(R.color.wrongParams));
            }
        });
    }

    private boolean isValidRequest() {
        for (SinglePictureFragment picture :
                this.fragmentList) {
            if (!picture.containsImage()) {
                return false;
            }
        }

        return true;
    }

    private void addPictureFragments() {
        SinglePictureFragment singlePictureFragmentOne = SinglePictureFragment.newInstance();
        SinglePictureFragment singlePictureFragmentTwo = SinglePictureFragment.newInstance();

        this.fragmentList.add(singlePictureFragmentOne);
        this.fragmentList.add(singlePictureFragmentTwo);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_picture_one, singlePictureFragmentOne);
        transaction.replace(R.id.fragment_picture_two, singlePictureFragmentTwo);
        transaction.commit();
    }
}
