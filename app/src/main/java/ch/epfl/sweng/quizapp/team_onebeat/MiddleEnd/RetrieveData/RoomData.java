package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Date;
import java.util.List;
import java.util.Set;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network.DownloadData;

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
 * List<DownloadData<MusicData>> related_musics : playlist of the room with a getBuildable()
 * than return only music with a buildable loaded instance.
 * Set<DownloadData<User>> participants : related users
 *
 * DownloadData :
 * decorate data and provide isLoaded(); method to indicate when the data can be provide.
 * see Network.DownloadData class
 *
 *
 */

public class RoomData implements RetrieveData{

    private int id;

    private Date lastUpdate;
    private List<DownloadData<MusicData>> relatedMusics;
    private Set<DownloadData<UserData>> participants;
    private String name;


    public RoomData(int id,
                    Date lastUpdate,
                    String name,
                    List<DownloadData<MusicData>> relatedMusics,
                    Set<DownloadData<UserData>> participants ){

        this.id = id;
        this.lastUpdate = lastUpdate;
        this.relatedMusics = relatedMusics;
        this.participants = participants;


    }






}
