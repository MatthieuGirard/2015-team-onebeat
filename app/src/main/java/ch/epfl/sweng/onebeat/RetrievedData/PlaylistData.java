package ch.epfl.sweng.onebeat.RetrievedData;

import java.util.List;

import ch.epfl.sweng.onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.onebeat.Network.PendingData;

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
    private List<Integer> id_musics;
    private List<Integer> id_participants;
    private String name;
    private int addedBy;
    private int lastUpdateDate;


    public PlaylistData(
            int id,
            String name,
            List<Integer> id_musics,
            int addedBy,
            List<Integer> id_participants,
            int lastUpdateDate){


        this.id = id;
        this.name = name;
        this.id_musics = id_musics;
        this.id_participants = id_participants;
        this.addedBy = addedBy;
        this.lastUpdateDate = lastUpdateDate;

    }


    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }


    public PendingData<UserData> downloadAddedBy(){
        throw new NotImplementedException();
    }


    // need a buffer ? room1 goout room2 goout room1 ? [optional]

    public PendingData<BooleanData> isNewUpdateAvailable(){
        // TODO : send request
        throw new NotImplementedException();
    }


}
