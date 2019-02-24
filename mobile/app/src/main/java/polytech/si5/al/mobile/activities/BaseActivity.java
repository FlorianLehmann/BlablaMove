package polytech.si5.al.mobile.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import polytech.si5.al.mobile.R;
import polytech.si5.al.mobile.fragments.FragmentSettings;
import polytech.si5.al.mobile.fragments.fragmentseek.ListRelocationFragment;
import polytech.si5.al.mobile.fragments.fragmentseek.ListTravelFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.CaptureFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.DepatureFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.PictureFragment;
import polytech.si5.al.mobile.fragments.fragmentsend.RelocationFragment;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        this.loadEstimateFragment();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_announce_relocation:
                loadListRelocationFragment();
                break;
            case R.id.nav_announce_departure:
                loadListTravelFragment();
                break;
            case R.id.nav_estimate_picture:
                loadEstimateFragment();
                break;
            case R.id.nav_estimate_camera:
                loadCameraFragment();
                break;
            case R.id.nav_proposal_relocation:
                loadRelocationFragment();
                break;
            case R.id.nav_proposal_departure:
                loadDepartureFragment();
                break;
            case R.id.nav_settings:
                loadSettingsFragment();
                break;
            default:
                return false;
        }

        mDrawerLayout.closeDrawers();
        item.setChecked(true);
        setTitle(item.getTitle());

        return true;
    }

    private void loadCameraFragment() {
        CaptureFragment captureFragment = CaptureFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, captureFragment);
        fragmentTransaction.commit();
    }

    private void loadSettingsFragment() {
        FragmentSettings fragmentSettings = FragmentSettings.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragmentSettings);
        fragmentTransaction.commit();
    }

    private void loadListTravelFragment() {
        ListTravelFragment listTravelFragment = ListTravelFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, listTravelFragment);
        fragmentTransaction.commit();
    }

    private void loadListRelocationFragment(){
        ListRelocationFragment depatureFragment = ListRelocationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, depatureFragment);
        fragmentTransaction.commit();
    }

    private void loadDepartureFragment() {
        DepatureFragment depatureFragment = DepatureFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, depatureFragment);
        fragmentTransaction.commit();
    }

    private void loadEstimateFragment() {
        PictureFragment pictureFragment = PictureFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, pictureFragment);
        fragmentTransaction.commit();
    }

    private void loadRelocationFragment() {
        RelocationFragment relocationFragment = RelocationFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, relocationFragment);
        fragmentTransaction.commit();
    }
}