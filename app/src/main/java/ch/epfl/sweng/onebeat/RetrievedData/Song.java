package ch.epfl.sweng.onebeat.RetrievedData;

public class Song {

    private String title;
    private String artist;
    private String duration;
    private String spotifyRef;
    //TODO: add all sorts of separate fields such as song picture, mp3 file, etc.

    public Song(String title, String artist, String duration, String spotifyRef) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.spotifyRef = spotifyRef;
    }

    public String getArtist() { return artist; }
    public String getSpotifyRef() { return spotifyRef; }
    public String getTitle() { return title; }
    public String getDuration() { return duration; }

    public boolean equals(Song song) {
        return spotifyRef.contentEquals(song.getSpotifyRef());
    }
}