package ch.epfl.sweng.onebeat.RetrievedData;

public class Song {

    private String title;
    private String artist;
    private double duration;
    private String spotifyRef;
    private int localID;
    //TODO: add all sorts of separate fields such as song picture, mp3 file, etc.

    public Song(String title, String artist, double duration, String spotifyRef) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.spotifyRef = spotifyRef;
    }

    public Song(int localID) {
        this.localID = localID;
    }

    public String getArtist() { return artist; }
    public String getSpotifyRef() { return spotifyRef; }
    public String getTitle() { return title; }
    public double getDuration() { return duration; }

    public boolean isEqual(Song song) {
        return spotifyRef.contentEquals(song.getSpotifyRef());
    }
}