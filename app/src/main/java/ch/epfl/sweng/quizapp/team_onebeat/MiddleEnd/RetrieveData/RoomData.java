package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate music and participants related to a room from the backend
 * how : create 2 new backendTask for relatedMusics and participants
 * and return the RetrieveBuildable[s].
 *
 * information :
 * id : identifier of the room
 * lastUpdate : Date representing the last modification process executed by the endhost
 * when the instance was provided(end host : add/remove => update related date).
 * List<RetrieveBuildable<MusicData>> related_musics : playlist of the room with a getBuildable()
 * than return only music with a buildable loaded instance.
 * Set<User> participants : related users
 *
 *
 */
public class RoomData {

    private int id;

    private Date lastUpdate;
    private List<RetrieveBuildableData<MusicData>> relatedMusics;
    private Set<RetrieveBuildableData<UserData>> participants;
    private String name;


    public RoomData(int id,
                    Date lastUpdate,
                    String name,
                    List<RetrieveBuildableData<MusicData>> relatedMusics,
                    Set<RetrieveBuildableData<UserData>> participants ){

        this.id = id;
        this.lastUpdate = lastUpdate;
        this.relatedMusics = relatedMusics;
        this.participants = participants;


    }


    public static class Builder implements RetrieveBuildableData<RoomData>{

        private int id = -1;
        private Date lastUpdate = null;
        private List<RetrieveBuildableData<MusicData>> relatedMusics = null;
        private Set<RetrieveBuildableData<UserData>> participants = null;
        private boolean isLoaded = false;
        private String name = null;

        @Override
        public void copy(RoomData that) {
            id = that.id;
            lastUpdate = that.lastUpdate;
            relatedMusics = new ArrayList<>(that.relatedMusics);
            participants = new HashSet<>(that.participants);
            isLoaded = true;
            name = that.name;
        }

        @Override
        public boolean isLoaded() {
            return isLoaded;
        }

        @Override
        public boolean isBuildable() {
            // verify null
            return false;
        }

        @Override
        public RoomData build() throws BuildableException {
            if(!isBuildable()) throw new BuildableException();
            return null;
        }

        @Override
        public String toString(){
            return "RoomData";
        }

    }



}
