package polytech.si5.al.mobile.requests;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import polytech.si5.al.mobile.CsteStringApp;
import polytech.si5.al.mobile.fragments.CallableFragment;

public class AsyncTaskImage extends AsyncTask<Bitmap, Void, String> {

    private CallableFragment fragment;

    public AsyncTaskImage(CallableFragment fragment){
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(CsteStringApp.SERVER_BASE_HTTP + CsteStringApp.SERVER_ESTIMATE_ADDRESS);

        String boundary = "-------------" + System.currentTimeMillis();

        httpPost.setHeader("Content-type", "multipart/form-data; boundary="+boundary);

        ByteArrayBody bab = new ByteArrayBody(extractByteFromBitmat(bitmaps[0]), "pic.png");
        ByteArrayBody bab2 = new ByteArrayBody(extractByteFromBitmat(bitmaps[1]), "pic2.png");

        HttpEntity entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setBoundary(boundary)
                .addPart("image1", bab)
                .addPart("image2", bab2)
                .build();

        httpPost.setEntity(entity);

        try {
            HttpResponse response = httpclient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private byte[] extractByteFromBitmat(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();
    }

    @Override
    protected void onPostExecute(String result) {
        fragment.callbackSetter(result);
    }
}


