package ch.epfl.sweng.team_onebeat.FrontEnd.Network;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;

import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.BooleanParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;

/**
 * Created by hugo on 07.11.15.
 */

public class BackendDataProvider {


    // TODO : make the url

    public PendingData<BooleanData> existUser(int signature) throws JSONException, MalformedURLException {

        URL url = new URL("");
            return new PendingData<BooleanData>(
                    url,
                    new DefaultNetworkProvider(),
                    new BooleanParser());

        }


    public PendingData<BooleanData> subscribe(int signature, String pseudo) throws JSONException, MalformedURLException {

        URL url = new URL("");
        return new PendingData<BooleanData>(
                url,
                new DefaultNetworkProvider(),
                new BooleanParser());

    }



    public PendingData<BooleanData> connect(String signature) throws JSONException, MalformedURLException {

        URL url = new URL("");
        return new PendingData<BooleanData>(
                url,
                new DefaultNetworkProvider(),
                new BooleanParser());

    }



}



