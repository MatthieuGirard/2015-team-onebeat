package ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;

/**
 * Created by hugo on 25.10.2015.
 *
 * encapsulate music and participants related to a room from the backend
 * (room in gui)
 *
 * information :
 * id : identifier of the room
 * lastUpdate : Date representing the last modification process executed by the endhost
 * when the instance was provided(end host : add/remove => update related date).
 * List<Integer> id_musics : id
 * Set<Intger> id_participants :  id
 *
 * PendingData :
 * decorate data and provide isLoaded() method to indicate when the data can be build.
 * see Network.PendingData class for more info
 *
 *
 */

public class PlaylistData {

    private int id;

    private Date lastUpdate;
    private List<Integer> id_musics;
    private Set<Integer> id_participants;
    private String name;


    public PlaylistData(int id,
                    Date lastUpdate,
                    String name,
                    List<Integer> id_musics,
                    Set<Integer> id_participants ){

        this.id = id;
        this.lastUpdate = lastUpdate;
        this.name = name;
        this.id_musics = id_musics;
        this.id_participants = id_participants;


    }

    public PendingData<MusicData> downloadsMusics(){
        // TODO use id_musics to retrieve data
        throw new NotImplementedException();
    }

    public PendingData<UserData> downloadsUsers(){
        // TODO use id_musics to retrieve data
        throw new NotImplementedException();
    }

    // need a buffer ? room1 goout room2 goout room1 ? [optional]

    public PendingData<BooleanData> isNewUpdateAvailable(){
        // TODO : send request
        throw new NotImplementedException();
    }


}
