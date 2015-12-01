package ch.epfl.sweng.onebeat.RetrievedData;

import android.os.Parcel;
import android.os.Parcelable;

public class Song {

    private String spotifyRef;
    public String title;
    public String artist;
    public String duration;
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
}