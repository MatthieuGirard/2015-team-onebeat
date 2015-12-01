package ch.epfl.sweng.onebeat.Network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matthieu on 01.12.2015.
 */
public class SendDataTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {

        return uploadUrl(params[0], params[1]);
    }

    private String uploadUrl(String targetURL, String dataToSend) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            //Create urlConnection
            url = new URL(targetURL);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            //Send request
            DataOutputStream wr = new DataOutputStream (urlConnection.getOutputStream ());
            wr.writeBytes (dataToSend);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
