package ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;

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




}
