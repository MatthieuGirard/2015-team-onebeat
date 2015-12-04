package ch.epfl.sweng.onebeat.RetrievedData;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.NotDefinedUserInfosException;

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

    public boolean equals(Song song) {
        return spotifyRef.contentEquals(song.getSpotifyRef());
    }

    public String toSendFormat(int roomID) {
        JSONObject json = new JSONObject();
        try {
            json.put("room", roomID);
            json.put("artist", artist);
            json.put("title", title);
            json.put("duration", duration);
            json.put("spotifyRef", spotifyRef);
            json.put("addedBy", SpotifyUser.getInstance().getSpotifyID());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NotDefinedUserInfosException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}