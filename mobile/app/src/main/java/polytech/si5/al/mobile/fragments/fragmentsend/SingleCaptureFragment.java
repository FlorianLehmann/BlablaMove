package polytech.si5.al.mobile.fragments.fragmentsend;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import polytech.si5.al.mobile.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Enzo on 25/10/2018.
 *
 */

public class SingleCaptureFragment extends Fragment {

    private VideoView videoView;

    private Uri videoUri = null;

    public SingleCaptureFragment(){

    }

    public static SingleCaptureFragment newInstance(){
        SingleCaptureFragment fragment = new SingleCaptureFragment();
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
        View rootView = inflater.inflate(R.layout.capture_fragment, container, false);

        this.videoView = rootView.findViewById(R.id.capture_view);

        defineButtonAction(rootView);

        return rootView;
    }

    private void defineButtonAction(View rootView) {
        Button button = rootView.findViewById(R.id.takePicture);

        button.setOnClickListener((view) -> {
            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

            startActivityForResult(takeVideoIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();

            this.videoView.setVideoURI(videoUri);
            this.videoUri = videoUri;

            //this.videoView.start();
        }
    }

    public boolean containsVideo() {
        return this.videoUri != null;
    }

    private String getRealPathFromUri(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        try (Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null)) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
    }

    public String getVideo() {
        return getRealPathFromUri(this.getContext(), this.videoUri);
    }
}
