package ch.epfl.sweng.onebeat;

/**
 * Created by Matthieu on 13.11.2015.
 */
public class SpotifyUser {

    private static final SpotifyUser instance = new SpotifyUser();

    private String pseudo = null;
    private String spotifyID = null;

    private SpotifyUser() {
        if (instance != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public final static SpotifyUser getInstance() { return instance; }

    public String getString() { return this.pseudo; }
    public String getSpotifyID() { return this.spotifyID; }

    public void setInfos(String pseudo, String spotifyID) {
        if (this.pseudo == null) {
            this.pseudo = pseudo;
        }
        if (this.spotifyID == null) {
            this.spotifyID = spotifyID;
        }
    }
}
