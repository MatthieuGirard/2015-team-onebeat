package ch.epfl.sweng.onebeat.Network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.epfl.sweng.onebeat.Exceptions.JSONParserException;

/**
 * Created by Matthieu on 13.11.2015.
 */
public class DownloadWebpageTask extends AsyncTask<String, Void, String> {

    private WebPageDownloader callingActivity;


    private String resultContent;

    public DownloadWebpageTask(WebPageDownloader activity) {
        this.callingActivity = activity;
    }


    @Override
    protected String doInBackground(String... params) {
        // params comes from the execute() call: params[0] is the url.
        // params[1] is the token
        try {
            return downloadUrl(params[0], params[1]);
        } catch (IOException e) {
            return "Error in downloading WebPage at given url: "+params[0];
        }
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        try {
            callingActivity.onPageDataRetrieved(result);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JSONParserException e) {
            e.printStackTrace();
        }
    }

    private String downloadUrl(String myurl, String token) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (token != "") {
                conn.setRequestProperty("Authorization", "Bearer " + token);
            }
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String downloadUserJSON(String myurl, String token) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("user JSON request", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = DownloadWebpageTask.readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String readIt(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}