package ch.epfl.sweng.onebeat.Network;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.onebeat.Parsers.AddedUserParser;
import ch.epfl.sweng.onebeat.Parsers.Parser;
import ch.epfl.sweng.onebeat.RetrievedData.BooleanData;
import ch.epfl.sweng.onebeat.RetrievedData.IntegerData;
import ch.epfl.sweng.onebeat.RetrievedData.MusicInformationData;
import ch.epfl.sweng.onebeat.RetrievedData.UserData;

/**
 * Created by hugo on 07.11.15.
 */

public class BackendDataProvider {



    private static final BackendDataProvider provider = new BackendDataProvider();

    private final String URL = "http://onebeat.pythonanywhere.com/";


    private BackendDataProvider(){

        if(provider != null){
            throw new IllegalStateException("singleton already instanciate");
        }

    }



    public static BackendDataProvider getInstance() {
        return provider;
    }




/*
    public PendingData<UserData> getUser(String spotifyRef) {

        // TODO : by get : url/getUser
        Map<String, String> namesValues = new HashMap<>();
        namesValues.put("id", "" + spotifyRef);


        return new PendingData<UserData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new UserParser());


    }

*/

    public PendingData<IntegerData> addUser(String spotifyRef, String pseudo) throws MalformedURLException {

        URL url = new URL(URL + "addUser");
        String req = "{ \n" +
                "id : " + spotifyRef + "\n " +
                "name : " + pseudo + "\n" +
                "}";

        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", req);

        return new PendingData<IntegerData>(
                url,
                namesValues,
                new DefaultNetworkProvider(),
                new AddedUserParser());


    }


/*


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




    public PendingData<BooleanData> addMusic(String spotifyRef){

        Map<String,String> namesValues = new HashMap<>();
        namesValues.put("request", "existSong");
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


*/


}



