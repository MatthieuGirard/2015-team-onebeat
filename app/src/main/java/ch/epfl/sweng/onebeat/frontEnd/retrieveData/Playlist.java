package ch.epfl.sweng.onebeat.frontEnd.retrieveData;

import java.util.ArrayList;
import java.util.List;

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

public class Playlist {


    private String id;
    private List<String> musicId;
    private List<String> musicAddedBy;
    private List<String> participantId;
    private String name;
    private String playlistAddedBy;

    private int currentMusic = 0;

    public Playlist(
            String id,
            String name,
            List<String> musicId,
            List<String> musicAddedBy,
            String playlistAddedBy,
            List<String> participantId){


        if(musicId.size() != musicAddedBy.size()){
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.name = name;
        this.musicId = musicId;
        this.musicAddedBy = musicAddedBy;
        this.participantId = participantId;
        this.playlistAddedBy = playlistAddedBy;

    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }


    public List<String> getMusics(){
        return new ArrayList<>(musicId);
    }

    public List<String> getMusicsAddedBy(){
        return new ArrayList<>(musicAddedBy);
    }

    public String getPlaylistAddedBy(){
        return playlistAddedBy;
    }






    public void reset(){
        currentMusic = 0;
    }

    public void nextMusic() throws IllegalAccessException {

        if(currentMusic + 1 < musicId.size()){
        throw new IllegalAccessException();
        }

        currentMusic++;
    }

    public boolean hasNextMusic(){
        return currentMusic +1 < musicId.size();
    }

    public String currentMusic(){
        return musicId.get(currentMusic);
    }

    public String currentMusicAddedBy(){
        return musicAddedBy.get(currentMusic);
    }


}

