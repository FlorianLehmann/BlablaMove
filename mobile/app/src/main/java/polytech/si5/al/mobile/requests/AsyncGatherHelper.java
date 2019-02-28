package polytech.si5.al.mobile.requests;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import polytech.si5.al.mobile.CsteStringApp;
import polytech.si5.al.mobile.fragments.CallableFragment;

public class AsyncGatherHelper extends AsyncTask<String, Void, String> {

    private CallableFragment callback;

    public AsyncGatherHelper(CallableFragment callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        // params comes from the execute() call: params[0] is the url.
        try {
            return getFromAPI(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    private String getFromAPI(String extension) throws IOException{
        HttpURLConnection urlConnection = null;
        URL url;
        String res;
        try {
            url = new URL(CsteStringApp.SERVER_BASE_HTTP + CsteStringApp.SERVER_BASE_ADDRESS + CsteStringApp.SERVER_BASE_EXTENSION + extension);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            res = inputStreamToString(in);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        callback.callbackSetter(result);
    }

    private String inputStreamToString(InputStream is) {
        String rLine;
        StringBuilder answer = new StringBuilder();

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader rd = new BufferedReader(isr);

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

}