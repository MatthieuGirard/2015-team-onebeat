package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Observable;

/**
 * Created by hugo on 24.10.2015.
 *
 * encapsulate a user to an initial state
 * when the "DownloadedData" go in  Loaded State
 * the data notify observers.
 */
public final class UserData {

    private String pseudo;

    public UserData( String pseudo ){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }


    public static class Builder implements Buildable<UserData>{

        @Override
        public void copy(Buildable<UserData> that) {

        }
        @Override
        public boolean isLoaded() {
            return false;
        }

        @Override
        public UserData build() {
            return null;
        }


    }




}
