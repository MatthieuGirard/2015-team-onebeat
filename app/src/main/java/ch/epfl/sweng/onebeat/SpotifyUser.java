package ch.epfl.sweng.onebeat;

/**
 * Created by Matthieu on 13.11.2015.
 */
public class SpotifyUser {

    private final String pseudo;
    private final String spotifyID;

    public SpotifyUser(String pseudo, String spotifyID) {

        this.pseudo = pseudo;
        this.spotifyID = spotifyID;
    }

    public String getString() { return this.pseudo; }
    public String getSpotifyID() { return this.spotifyID; }
}
