package polytech.si5.al.mobile.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.fragments.FragmentSettings;
import polytech.si5.al.mobile.fragments.fragmentsend.DepatureFragment;
import polytech.si5.al.mobile.fragments.fragmentseek.ListRelocationFragment;
import polytech.si5.al.mobile.fragments.fragmentseek.ListTravelFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.PictureFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.RelocationFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                loadRelocationFragment();
                return true;
            case R.id.navigation_dashboard:
                loadEstimateFragment();
                return true;
            case R.id.navigation_notifications:
                loadDepartureFragment();
                return true;
            case R.id.navigation_list_relocation:
                loadListRelocationFragment();
                return true;
            case R.id.navigation_list_travel:
                loadListTravelFragment();
                return true;
            default:
                return false;
        }
    }

    private void loadSettingsFragment() {
        FragmentSettings fragmentSettings = FragmentSettings.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentSettings);
        fragmentTransaction.commit();
    }

    private void loadListTravelFragment() {
        ListTravelFragment listTravelFragment = ListTravelFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, listTravelFragment);
        fragmentTransaction.commit();
    }

    private void loadListRelocationFragment(){
        ListRelocationFragment depatureFragment = ListRelocationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, depatureFragment);
        fragmentTransaction.commit();
    }

    private void loadDepartureFragment() {
        DepatureFragment depatureFragment = DepatureFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, depatureFragment);
        fragmentTransaction.commit();
    }

    private void loadEstimateFragment() {
        PictureFragment pictureFragment = PictureFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, pictureFragment);
        fragmentTransaction.commit();
    }

    private void loadRelocationFragment() {
        RelocationFragment relocationFragment = RelocationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, relocationFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadEstimateFragment();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

}
