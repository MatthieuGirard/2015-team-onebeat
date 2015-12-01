package ch.epfl.sweng.onebeat.Network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Observable;

import ch.epfl.sweng.onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Parsers.Parser;

/**
 * Created by hugo on 26.10.2015.
 *
 *  data in pending mode (currently coming from backend)
 *  serveurUrl + networkProvider + request => JSONMessage(the response)
 *  parser<T> : response => T
 *
 *  the class load in a background thread the request and put
 *  the downloadState to LOADED when the "T instance" as been retrieve
 */
public class PendingData<T> extends Observable {



    private final float TIME_REFRESH_WHEN_BLOCKING = 30;
    private final static int HTTP_SUCCESS_START = 200;
    private final static int HTTP_SUCCESS_END = 299;

    public enum DownloadState {PENDING, LOADED, ERROR }


    private DownloadState downloadState;

    private T instance = null;

    private Thread download;



    // to simulate downloads in test

    public PendingData(T instance, final int threshold){

    this.instance = instance;
    this.downloadState = DownloadState.PENDING;

    download = new Thread(new Runnable() {

        @Override
        public void run() {

            try {
                wait(threshold);
                downloadState = DownloadState.LOADED;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    });


    }


    // constructor for get

    public PendingData(URL url,
                       NetworkProvider networkProvider,
                       Parser<T> parser
                       ){


        this.downloadState = DownloadState.PENDING;

        this.download = new Thread(new Download(url,networkProvider,parser));
        this.download.run();

    }


    // constructor for post

    public PendingData(URL url,
                       Map<String,String> namesValues,
                       NetworkProvider networkProvider,
                       Parser<T> parser
    ){
        Log.d("#PendingData", "instiate");

        this.downloadState = DownloadState.PENDING;
        this.download = new Thread(new Download(url,namesValues,networkProvider,parser));
        this.download.run();

    }





    public DownloadState downloadState() {
        return downloadState;
    }



    public void blockAtMost(int threshold){

        float totalTime = 0;


        while(downloadState() == DownloadState.PENDING){


            if(totalTime > threshold){
                download.interrupt();
                downloadState = DownloadState.ERROR;
            }

            try {
                wait((long) TIME_REFRESH_WHEN_BLOCKING);
            } catch (InterruptedException e)         {
            }


            totalTime += TIME_REFRESH_WHEN_BLOCKING;

        }


    }


    public T get() throws BuildableException {
        if(instance == null) throw new BuildableException();
        return instance;
    }



    @Override
    public String toString(){
        return "download data";
    }












    private class Download implements Runnable {


        private URL url;
        private NetworkProvider networkProvider;
        private Parser<T> parser;
        private Map<String, String> namesValues;


        public Download(URL url,
                        Map<String, String> namesValues,
                        NetworkProvider networkProvider,
                        Parser parser) {

            this.url = url;
            this.networkProvider = networkProvider;
            this.parser = parser;
            this.namesValues = namesValues;
        }


        public Download(URL url, NetworkProvider networkProvider, Parser parser) {
            this.url = url;
            this.networkProvider = networkProvider;
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


        public void run() {

            try {

                HttpURLConnection conn = networkProvider.getConnection(url);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                int response = conn.getResponseCode();
                if (response < HTTP_SUCCESS_START || response > HTTP_SUCCESS_END) {
                    downloadState = DownloadState.ERROR;
                }
                else {

                    // instance is ready
                    instance = parser.parse(new JSONObject(fetchContent(conn)));
                    downloadState = DownloadState.LOADED;
                    PendingData.this.setChanged();

                }


            } catch (IOException e) {
                downloadState = DownloadState.ERROR;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                downloadState = DownloadState.ERROR;
            }
        }

    }


        private String postRequest(Map<String, String> namesValues) throws UnsupportedEncodingException {


            StringBuilder strB = new StringBuilder();
            boolean first = true;

            for (Map.Entry<String, String> entry : namesValues.entrySet()) {

                if (first == true) {
                    first = false;
                } else {
                    strB.append("&");
                }
                strB.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                strB.append("=");
                strB.append(URLEncoder.encode(entry.getValue(), "UTF-8"));


            }

            return strB.toString();
        }




    }









