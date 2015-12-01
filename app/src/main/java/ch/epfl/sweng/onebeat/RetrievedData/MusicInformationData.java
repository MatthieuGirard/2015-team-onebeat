package ch.epfl.sweng.onebeat.RetrievedData;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;

/**
 * Created by hugo on 20.11.15.
 */

public class MusicInformationData {

    private int id;
    private String artist;
    private String title;
    private float duration;
    private String spotifyRef;
    private int addedBy;


    public MusicInformationData(int id, String artist, String title,
                                float duration, String spotifyRef, int addedBy)
    {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.spotifyRef = spotifyRef;
        this.addedBy = addedBy;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public float getDuration(){
        return duration;
    }

    public String getSpotifyRef(){
        return spotifyRef;
    }

    public PendingData<UserData> getAddedBy(){
        throw new NotImplementedException();
    }


}
