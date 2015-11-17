package ch.epfl.sweng.onebeat;

/**
 * Created by Matthieu on 16.11.2015.
 */
public class Track {

    private String artist;
    private String spotifyRef;
    private String name;

    public Track(String artist, String name, String spotifyRef) {
        this.artist = artist;
        this.name = name;
        this.spotifyRef = spotifyRef;
    }
    public String getArtist() { return artist; }
    public String getSpotifyRef() { return spotifyRef; }
    public String getName() { return name; }
}
