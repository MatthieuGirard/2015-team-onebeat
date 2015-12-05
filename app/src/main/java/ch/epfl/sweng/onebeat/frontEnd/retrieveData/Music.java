package ch.epfl.sweng.onebeat.frontEnd.retrieveData;

/**
 * Created by hugo on 20.11.15.
 */

public class Music {



    private String title;
    private String artist;
    private float duration;
    private String spotifyRef;


    public Music(String title, String artist, float duration, String spotifyRef) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.spotifyRef = spotifyRef;
    }


    public String getArtist() {
        return artist;
    }

    public String getSpotifyRef() {
        return spotifyRef;
    }

    public String getTitle() {
        return title;
    }

    public float getDuration() {
        return duration;
    }


    @Override
    public boolean equals( Object that) {
        return (that instanceof Music) &&
                ((Music)that).spotifyRef == this.spotifyRef;
    }

    @Override
    public int hashCode(){
        return spotifyRef.hashCode();
    }


}
