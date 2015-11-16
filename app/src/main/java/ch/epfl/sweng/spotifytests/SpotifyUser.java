package ch.epfl.sweng.spotifytests;

/**
 * Created by Matthieu on 13.11.2015.
 */
public class SpotifyUser {

    private final String pseudo;

    public SpotifyUser(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getString() { return this.pseudo; }
}
