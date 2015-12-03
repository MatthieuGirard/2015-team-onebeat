package ch.epfl.sweng.onebeat.RetrievedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 28.11.2015.
 */
public class Room {

    private final String name;
    private final String creator;
    private List<Integer> songs;

    public Room(String name, String creator) {
        this.name = name;
        this.creator = creator;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public void setSongs(List<Integer> songs) {
        this.songs = songs;
    }
}