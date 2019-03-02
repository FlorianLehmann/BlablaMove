package polytech.si5.al.mobile.requests;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import polytech.si5.al.mobile.CsteStringApp;

public class AsyncSendHelper extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        // params comes from the execute() call: params[0] is the url.
        try {
            return executePost(params[0], params[1]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    private String executePost(String endPoint, String contentToSend) throws IOException {
        URL url = new URL(CsteStringApp.SERVER_BASE_HTTP + CsteStringApp.SERVER_BASE_ADDRESS + CsteStringApp.SERVER_BASE_EXTENSION + endPoint);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        setPostRequestContent(conn, contentToSend);

        conn.connect();

        return conn.getResponseMessage()+"";
    }

    private void setPostRequestContent(HttpURLConnection conn,
                                       String content) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

        writer.write(content);
        writer.flush();

        writer.close();
        os.close();
    }

}