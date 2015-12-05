package ch.epfl.sweng.onebeat.frontEnd.network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;

/**
 * Created by hugo on 01.12.15.
 */
public class BackendDownloader<T> extends AsyncTask<PendingData<T>, Void, Void>{



        private final static int HTTP_SUCCESS_START = 200;
        private final static int HTTP_SUCCESS_END = 299;


        private HttpUriRequest request;
        private Parser<T> parser;


        public BackendDownloader(HttpUriRequest request, Parser<T> parser) {
            this.request = request;
            this.parser = parser;
        }


        // TODO: disconect

        private String fetchContent(HttpURLConnection conn) throws IOException {
            StringBuilder out = new StringBuilder();
            BufferedReader reader = null;

            try {

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }

                return out.toString();

            } finally {
                if (reader != null) {
                    reader.close();
                }
            }

        }


    @Override
    protected Void doInBackground(PendingData<T>... params) {

        try {

            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            Log.d("#backendDownloader", "status code : " + statusCode);

            if ( statusCode < HTTP_SUCCESS_START || statusCode > HTTP_SUCCESS_END) {

                for(PendingData pData : params){
                    pData.setDownloadState(PendingData.DownloadState.ERROR);
                }

            }
            else {


                BufferedReader rd =
                        new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                StringBuilder data = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    data.append(line);
                }


                // instance is ready


                for(PendingData pData : params){
                    Log.d("#BackendDownloader", "json on : " + data.toString());
                    JSONObject obj = new JSONObject(data.toString());
                    Log.d("#BackendDownloader", "parsingData");
                    T inst = parser.parse(obj);
                    Log.d("#BackendDownloader", "load instance");
                    pData.setData(inst);
                    Log.d("#BackendDownloader", "set state");
                    pData.setDownloadState(PendingData.DownloadState.LOADED);
                    Log.d("#BackendDownloader", "set state ok");
                }


            }


        } catch (IOException | JSONException | ParseException | BackendCommunicationException e) {

            for(PendingData pData : params){
             pData.setDownloadState(PendingData.DownloadState.ERROR);
            }

        }


        return null;
    }






}
