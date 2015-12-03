package ch.epfl.sweng.onebeat.RetrievedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthieu on 28.11.2015.
 */
public class Room {

    private final String name;
    private final String creator;
    private Map<Song, User> songs;
    private String password;

    public Room(String name, String creator, Map<Song, User> songs, String password ) {
        this.name = name;
        this.creator = creator;
        this.songs = new HashMap<>(songs);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public Map<Song, User> getSongs() {
        return songs;
    }

    public String getPassword() {
        return password;
    }
}