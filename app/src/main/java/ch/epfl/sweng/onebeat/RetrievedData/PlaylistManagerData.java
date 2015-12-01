package ch.epfl.sweng.onebeat.RetrievedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate all playlist (room on gui) subscribed by the user.
 *
 * information :
 * List<PlaylistData> playlist;
 *
 *
 */


public class PlaylistManagerData  {

    List<PlaylistData> playlists = new ArrayList<>();


    public PlaylistManagerData(List<PlaylistData> playlist){

        this.playlists = playlist;
    }


    public List<PlaylistData> getPlayLists(){
        return new ArrayList<>(playlists);
    }


}
