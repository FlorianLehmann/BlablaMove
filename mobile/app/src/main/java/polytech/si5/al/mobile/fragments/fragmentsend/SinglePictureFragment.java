package polytech.si5.al.mobile.fragments.fragmentsend;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import polytech.si5.al.mobile.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Enzo on 25/10/2018.
 *
 */

public class SinglePictureFragment extends Fragment {

    private ImageView imageView;

    private Bitmap imageBitmap;

    public SinglePictureFragment(){

    }

    public static SinglePictureFragment newInstance(){
        SinglePictureFragment fragment = new SinglePictureFragment();
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
        View rootView = inflater.inflate(R.layout.picture_fragment, container, false);

        this.imageView = rootView.findViewById(R.id.image);

        defineButtonAction(rootView);

        return rootView;
    }

    private void defineButtonAction(View rootView) {
        Button button = rootView.findViewById(R.id.takePicture);

        button.setOnClickListener((view) -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(takePictureIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public boolean containsImage() {
        return imageBitmap != null;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}
