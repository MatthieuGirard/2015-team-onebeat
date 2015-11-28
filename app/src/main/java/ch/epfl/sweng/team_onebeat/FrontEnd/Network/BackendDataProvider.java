package ch.epfl.sweng.team_onebeat.FrontEnd.Network;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.BooleanParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.MusicInformationParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.Parser;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.PlaylistParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.UserParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.MusicInformationData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.PlaylistData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.UserData;

/**
 * Created by hugo on 07.11.15.
 */

public class BackendDataProvider {



    private BackendDataProvider provider = new BackendDataProvider();

    private URL url;


    private BackendDataProvider(){

        if(provider != null){
            throw new IllegalStateException("singleton already instanciate");
        }

        // TODO : url
        try {
            url = new URL("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }



    public BackendDataProvider getInstance() {
        return provider;
    }





    public PendingData<UserData> getUser(String spotifyRef){


        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "getUser");
        namesValues.put("spotifyRef", "" + spotifyRef);


        return new PendingData<UserData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new UserParser());


    }



    public PendingData<BooleanData> existUser(String spotifyRef){

        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "existUser");
        namesValues.put("spotifyRef", "" + spotifyRef);


        return new PendingData<BooleanData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new BooleanParser());


    }





    public PendingData<BooleanData> addUser(String spotifyRef, String pseudo){


        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "addUser");
        namesValues.put("spotifyRef", "" + spotifyRef );
        namesValues.put("pseudo", pseudo);


        return new PendingData<BooleanData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new BooleanParser());



    }





    public PendingData<BooleanData> existMusic(int id){


        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "existSong");
        namesValues.put("id", "" + id);


        return new PendingData<BooleanData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new BooleanParser());


    }




    public PendingData<BooleanData> addMusic(int id, String spotifyRef){

        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "existSong");
        namesValues.put("id", "" + id);
        namesValues.put("id", spotifyRef);


        return new PendingData<BooleanData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new BooleanParser());


    }




    public PendingData<MusicInformationData> getMusicInfo(int id){


        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "getRoom");
        namesValues.put("id", "" + id);


        return new PendingData<MusicInformationData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new MusicInformationParser());


    }





}



