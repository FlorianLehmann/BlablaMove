package polytech.si5.al.mobile.requests;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

import polytech.si5.al.mobile.CsteStringApp;
import polytech.si5.al.mobile.fragments.CallableFragment;

public class AsyncTaskVideo extends AsyncTask<String, Void, String> {

    private CallableFragment fragment;

    public AsyncTaskVideo (CallableFragment fragment){
        this.fragment = fragment;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(CsteStringApp.SERVER_BASE_HTTP + CsteStringApp.SERVER_ESTIMATE_ADDRESS_VIDEO);

        String boundary = "-------------" + System.currentTimeMillis();

        httpPost.setHeader("Content-type", "multipart/form-data; boundary="+boundary);

        FileBody video = new FileBody(new File(strings[0]));

        HttpEntity entity = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setBoundary(boundary)
                .addPart("video", video)
                .build();

        httpPost.setEntity(entity);

        System.out.println("xdddd");
        System.out.println("xdddd");
        System.out.println("xdddd");
        System.out.println("xdddd");
        System.out.println("xdddd");

        return sendRequest(httpclient, httpPost);
    }

    private String sendRequest(HttpClient httpclient, HttpPost httpPost) {
        try {
            HttpResponse response = httpclient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        fragment.callbackSetter(result);
    }
}


