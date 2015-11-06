package ch.epfl.sweng.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network.PendingData;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate music and participants related to a room from the backend
 *
 * information :
 * id : identifier of the room
 * lastUpdate : Date representing the last modification process executed by the endhost
 * when the instance was provided(end host : add/remove => update related date).
 * List<PendingData<MusicData>> related_musics : playlist of the room with a getBuildable()
 * than return only music with a buildable loaded instance.
 * Set<PendingData<User>> participants : related users
 *
 * PendingData :
 * decorate data and provide isLoaded() method to indicate when the data can be build.
 * see Network.PendingData class for more info
 *
 *
 */

public class RoomData implements RetrieveData{

    private int id;

    private Date lastUpdate;
    private List<PendingData<MusicData>> relatedMusics;
    private Set<PendingData<UserData>> participants;
    private String name;


    public RoomData(int id,
                    Date lastUpdate,
                    String name,
                    List<PendingData<MusicData>> relatedMusics,
                    Set<PendingData<UserData>> participants ){

        this.id = id;
        this.lastUpdate = lastUpdate;
        this.relatedMusics = relatedMusics;
        this.participants = participants;


    }






}
