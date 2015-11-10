package ch.epfl.sweng.team_onebeat.MiddleEnd.Network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

import ch.epfl.sweng.team_onebeat.Exceptions.BackendCommunicationException;
import ch.epfl.sweng.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.Exceptions.TimeExceededException;
import ch.epfl.sweng.team_onebeat.MiddleEnd.Parser.Parser;

/**
 * Created by hugo on 26.10.2015.
 *
 *  data in pending mode (currently coming from backend)
 *  serveurUrl + networkProvider + request => JSONMessage(the response)
 *  parser<T> : response => T
 *
 *  the class load in a backgorund thread the request and put
 *  the downloadState to LOADED when the "T instance" as been retrieve
 */
public class PendingData<T> {



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


    // real constructor

    public PendingData(URL url,
                       NetworkProvider networkProvider,
                       Message request,
                       Parser<T> parser
                       ){


        this.downloadState = DownloadState.PENDING;

        this.download = new Thread(new Download(url,networkProvider, request,parser));
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
        private Message request;
        private Parser<T> parser;


        public Download(URL url, NetworkProvider networkProvider, Message request, Parser parser){
            this.url = url;
            this.networkProvider = networkProvider;
            this.request = request;
            this.parser = parser;
        }


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
        public void run() {

            NetworkProvider networkProvider = new DefaultNetworkProvider();

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
                    downloadState = DownloadState.LOADED;
                    instance = parser.parse(new JSONObject(fetchContent(conn)));

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




}
