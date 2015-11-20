package ch.epfl.sweng.team_onebeat.FrontEnd.Network;

import java.util.List;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.MusicData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.MusicInformationData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.SpotifyUser;

/**
 * Created by hugo on 20.11.15.
 */
public class SpotifyDataProvider {


    /*
     TODO
      implements methods using the pending data
      you may have to modify the method's signatures to be compatible with spotify
      run the SpotifyDataProviderTest to verify the implementation (already implemented,
      you just have to complete the : String pseudo, String pass with your spotify account).
      you can watch pendingDataTest that fetch a JSON from a server and retrieve the information (1min).
      */

    public SpotifyDataProvider() {
    }



    public PendingData<BooleanData> existUser( String pseudo, String pass ){
        throw new NotImplementedException();
    }


    public PendingData<SpotifyUser> connect( String pseudo, String pass ){
        throw new NotImplementedException();
    }


    // music information without the music itself
    public PendingData<MusicInformationData> musicInformation(String key){
        throw new NotImplementedException();
    }

    // list of song on a search
    public PendingData<List<MusicInformationData>> searchMusics(String name){
        throw new NotImplementedException();
    }

    // music data
    public PendingData<MusicData> music(String key){
        throw new NotImplementedException();
    }


}
